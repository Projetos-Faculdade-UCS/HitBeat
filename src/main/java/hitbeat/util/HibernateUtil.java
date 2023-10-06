package hitbeat.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the ServiceRegistry from hibernate.cfg.xml
            StandardServiceRegistry standardRegistry = 
                new StandardServiceRegistryBuilder().configure().build();

            // Create a metadata source using the specified service registry.
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            
            return metadata.getSessionFactoryBuilder().build();
        } 
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void init() {
        getSessionFactory();
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
