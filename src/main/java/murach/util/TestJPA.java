package murach.util;

import jakarta.persistence.EntityManagerFactory;

import murach.business.User;

public class TestJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                JPAUtil.getEntityManagerFactory();

        try {

            User user = new User(
                    "newuser@gmail.com",
                    "New",
                    "User"
            );

            UserDAO.insert(user);

            System.out.println(
                    "User inserted successfully!"
            );

            System.out.println(
                    "Generated ID: " + user.getId()
            );

        } finally {

            emf.close();
        }
    }
}