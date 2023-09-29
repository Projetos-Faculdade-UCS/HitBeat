package hitbeat.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class BaseDAO {
    
    private static SessionFactory sessionFactory;

    protected static void init(Class<?> modelClass){
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();     
        sessionFactory =
                new MetadataSources(registry)             
                        .addAnnotatedClass(modelClass)   
                        .buildMetadata()                  
                        .buildSessionFactory();
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    
}
