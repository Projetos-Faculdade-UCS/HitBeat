package hitbeat.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hitbeat.model.Genre;
import hitbeat.util.HibernateUtil;

public class GenreDAO extends BaseDAO<Genre> {

    public GenreDAO(){
        super(Genre.class);
    }

    public static void mockData() {
        Genre genre = new Genre("Rock");
        GenreDAO genreDAO = new GenreDAO();

        genreDAO.save(genre);
        genre = new Genre("Pop");
        genreDAO.save(genre);
        genre = new Genre("Jazz");
        genreDAO.save(genre);
    }

    public List<Genre> getGenresByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Genre g WHERE g.name = :name";
            Query<Genre> query = session.createQuery(hql, Genre.class);
            query.setParameter("name", name);
            return query.list();
        }
    }


    // ... Other CRUD operations ...
}