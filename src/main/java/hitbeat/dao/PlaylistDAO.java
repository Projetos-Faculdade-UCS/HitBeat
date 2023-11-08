package hitbeat.dao;

import hitbeat.model.Playlist;

public class PlaylistDAO extends BaseDAO<Playlist>{
    public PlaylistDAO(){
        super(Playlist.class);
    }
    https://www.baeldung.com/hibernate-one-to-many
    @Override
    protected void updateProperties(Playlist existingEntity, Playlist newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProperties'");
    }
}
