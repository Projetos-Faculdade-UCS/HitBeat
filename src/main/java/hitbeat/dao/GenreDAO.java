package hitbeat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hitbeat.model.Genre;

public class GenreDAO extends BaseDAO<Genre> {

    public GenreDAO() {
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

    public List<Genre> findGenresByName(String name) {
        return executeMethod(session -> {
            String hql = "FROM Genre g WHERE g.name = :name";
            Query<Genre> query = session.createQuery(hql, Genre.class);
            query.setParameter("name", name);
            return query.list();
        });
    }

    public Genre findByName(String genreName) {
        return executeMethod(session -> {
            return this.findByName(genreName, session);
        });
    }

    private Genre findByName(String genreName, Session session) {
        String hql = "FROM Genre g WHERE g.name = :name";
        Query<Genre> query = session.createQuery(hql, Genre.class);
        query.setParameter("name", genreName);
        return query.uniqueResult();
    }

    public List<Genre> getGenresByNames(List<String> genreNames) {
        return executeMethod(session -> {
            String hql = "FROM Genre g WHERE g.name IN (:names)";
            Query<Genre> query = session.createQuery(hql, Genre.class);
            query.setParameterList("names", genreNames);
            return query.list();
        });
    }

    @Override
    protected void updateProperties(Genre existingEntity, Genre newEntity) {
        existingEntity.setName(newEntity.getName());
    }
}