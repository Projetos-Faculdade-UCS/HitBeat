package hitbeat.dao;

import java.util.List;

import hitbeat.model.Album;
import hitbeat.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AlbumDAO extends BaseDAO<Album>{

    public AlbumDAO() {
        super(Album.class);
    }

    @Override
    protected void updateProperties(Album existingEntity, Album newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setLaunchDate(newEntity.getLaunchDate());
    }

    public Album getAlbumByName(String name) {
        return this.getSingleResult(this.getEntityManager().createQuery("SELECT a FROM Album a WHERE a.name = :name", Album.class)
                .setParameter("name", name));
    }

    public List<Album> findByName(List<String> names) {
        TypedQuery<Album> query = this.getEntityManager().createQuery("SELECT a FROM Album a WHERE a.name IN :names", Album.class);
        query.setParameter("names", names);
        return query.getResultList();
    }

    private Album getSingleResult(TypedQuery<Album> query) {
        return query.setMaxResults(1).getSingleResult();
    }

    private EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    
}
