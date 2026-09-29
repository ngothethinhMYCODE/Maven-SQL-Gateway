package murach.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import murach.business.User;

public class UserDAO {

    public static User selectUserByEmail(String email) {

        EntityManager em =
                JPAUtil.getEntityManagerFactory()
                       .createEntityManager();

        try {

            String jpql =
                    "SELECT u FROM User u " +
                    "WHERE u.email = :email";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("email", email);

            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }

        } finally {
            em.close();
        }
    }

    public static void insert(User user) {

        EntityManager em =
                JPAUtil.getEntityManagerFactory()
                       .createEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {

            transaction.begin();

            em.persist(user);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }
}