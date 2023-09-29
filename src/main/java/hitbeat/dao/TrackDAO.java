package hitbeat.dao;

import hitbeat.model.Track;

public class TrackDAO extends BaseDAO{
    public static void init(){
        BaseDAO.init(Track.class);
    }
}
