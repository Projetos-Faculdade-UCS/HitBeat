package hitbeat.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import hitbeat.model.Genre;

public class GenreDAO {

    private static SessionFactory sessionFactory;

    public static void init(){
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();     
            sessionFactory =
                    new MetadataSources(registry)             
                            .addAnnotatedClass(Genre.class)   
                            .buildMetadata()                  
                            .buildSessionFactory();
    }

    public static void mockData() {
        Genre genre = new Genre("Rock");
        GenreDAO genreDAO = new GenreDAO();
        genreDAO.saveGenre(genre);
        genre = new Genre("Pop");
        genreDAO.saveGenre(genre);
        genre = new Genre("Jazz");
        genreDAO.saveGenre(genre);
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void saveGenre(Genre genre) {
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(genre);
            session.getTransaction().commit();
        }
    }

    public List<Genre> getGenresByName(String name) {
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Genre g WHERE g.name = :name";
            Query<Genre> query = session.createQuery(hql, Genre.class);
            query.setParameter("name", name);
            return query.list();
        }
    }

    public List<Genre> getAllGenres() {
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Genre";
            Query<Genre> query = session.createQuery(hql, Genre.class);
            return query.list();
        }
    }

    // ... Other CRUD operations ...
}