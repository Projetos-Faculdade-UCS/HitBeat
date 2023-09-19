package hitbeat;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import hitbeat.model.Track;
import hitbeat.view.IndexView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                    .build();     
    try {
        sessionFactory =
                new MetadataSources(registry)             
                        .addAnnotatedClass(Track.class)   
                        .buildMetadata()                  
                        .buildSessionFactory();           
    }
    catch (Exception e) {
        // The registry would be destroyed by the SessionFactory, but we
        // had trouble building the SessionFactory so destroy it manually.
        StandardServiceRegistryBuilder.destroy(registry);
    }
        IndexView.main(args);
    }
}
