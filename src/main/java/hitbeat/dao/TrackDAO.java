package hitbeat.dao;


import java.util.Date;

import hitbeat.model.Genre;
import hitbeat.model.Track;

public class TrackDAO extends BaseDAO<Track>{

    public TrackDAO(){
        super(Track.class);
    }
    
    public static void mockData(){
        Genre genre = new Genre("Rock");
        Date dataAtual = new Date();

        Track track = new Track("Track 1", dataAtual, 100, "picturePath", "filePath", false, false, genre);
        TrackDAO trackDAO = new TrackDAO();
        try {
            trackDAO.save(track);
        }
        catch (Exception e) {
        }
    }

}
