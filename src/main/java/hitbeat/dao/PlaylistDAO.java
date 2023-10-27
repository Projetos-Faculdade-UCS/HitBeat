package hitbeat.dao;

import hitbeat.model.Playlist;

public class PlaylistDAO extends BaseDAO<Playlist>{
    public PlaylistDAO(){
        super(Playlist.class);
    }

    @Override
    protected void updateProperties(Playlist existingEntity, Playlist newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProperties'");
    }
}
