package hitbeat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hitbeat.model.Artist;

public class ArtistDAO extends BaseDAO<Artist> {
    public ArtistDAO() {
        super(Artist.class);
    }

	@Override
	protected void updateProperties(Artist existingEntity, Artist newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setImage(newEntity.getImage());
    }

    public Artist findByName(String artistName) {
        return executeMethod(session -> {
            return this.findByName(artistName, session);
        });
    }

    private Artist findByName(String artistName, Session session) {
        String hql = "FROM Artist a WHERE a.name = :name";
        Query<Artist> query = session.createQuery(hql, Artist.class);
        query.setParameter("name", artistName);
        return query.uniqueResult();
	}

    public List<Artist> findByName(List<String> artistNames) {
        return executeMethod(session -> {
            String hql = "FROM Artist a WHERE a.name IN (:names)";
            Query<Artist> query = session.createQuery(hql, Artist.class);
            query.setParameterList("names", artistNames);
            return query.list();
        });
    }
    
}
