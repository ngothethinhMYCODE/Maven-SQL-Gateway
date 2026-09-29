package murach.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.eclipse.persistence.jpa.PersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

    private static final EntityManagerFactory emf = createEntityManagerFactory();

    private static EntityManagerFactory createEntityManagerFactory() {

        Map<String, Object> properties = new HashMap<>();

        properties.put(
            "jakarta.persistence.jdbc.driver",
            "org.postgresql.Driver"
        );

        properties.put(
            "jakarta.persistence.jdbc.url",
            "jdbc:postgresql://"
                + System.getenv("DB_HOST")
                + ":"
                + System.getenv("DB_PORT")
                + "/"
                + System.getenv("DB_NAME")
        );

        properties.put(
            "jakarta.persistence.jdbc.user",
            System.getenv("DB_USER")
        );

        properties.put(
            "jakarta.persistence.jdbc.password",
            System.getenv("DB_PASSWORD")
        );

        properties.put(
            "eclipselink.logging.level",
            "FINE"
        );

        properties.put(
            "eclipselink.ddl-generation",
            "none"
        );

        return new PersistenceProvider()
                .createEntityManagerFactory(
                    "sqlGatewayPU",
                    properties
                );
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}