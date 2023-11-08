package hitbeat.dao;

import hitbeat.model.Album;

public class AlbumDAO extends BaseDAO<Album>{

    public AlbumDAO() {
        super(Album.class);
    }

    @Override
    protected void updateProperties(Album existingEntity, Album newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProperties'");
    }
    
}
