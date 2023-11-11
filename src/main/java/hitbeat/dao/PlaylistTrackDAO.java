package hitbeat.dao;

import hitbeat.model.PlaylistTrack;

public class PlaylistTrackDAO extends BaseDAO<PlaylistTrack>{
    public PlaylistTrackDAO(){
        super(PlaylistTrack.class);
    }
    
    @Override
    protected void updateProperties(PlaylistTrack existingEntity, PlaylistTrack newEntity) {
        existingEntity.setIndex(newEntity.getIndex());
        existingEntity.setPlaylist(newEntity.getPlaylist());
        existingEntity.setTrack(newEntity.getTrack());
    }
}
