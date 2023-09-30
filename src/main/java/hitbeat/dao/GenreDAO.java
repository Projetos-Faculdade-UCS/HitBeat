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

    public void mockData() {
        Genre genre = new Genre("Rock");

        this.save(genre);
        genre = new Genre("Pop");
        this.save(genre);
        genre = new Genre("Jazz");
        this.save(genre);
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