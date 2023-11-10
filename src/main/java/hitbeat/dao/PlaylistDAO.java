package hitbeat.dao;

import hitbeat.model.Playlist;

public class PlaylistDAO extends BaseDAO<Playlist>{
    public PlaylistDAO(){
        super(Playlist.class);
    }
    
    @Override
    protected void updateProperties(Playlist existingEntity, Playlist newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setFilePath(newEntity.getFilePath());
    }
}
