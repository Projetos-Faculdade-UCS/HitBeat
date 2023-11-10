package hitbeat.dao;

import java.util.List;

import hitbeat.model.Playlist;
import hitbeat.model.Track;

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

    public List<Track> getAllTracks(Playlist playlist) {
        return playlist.getTracks();
    }
}
