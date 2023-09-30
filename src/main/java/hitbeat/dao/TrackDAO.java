package hitbeat.dao;


import java.util.Date;

import hitbeat.model.Genre;
import hitbeat.model.Track;

public class TrackDAO extends BaseDAO<Track>{

    public TrackDAO(){
        super(Track.class);
    }
    
    public void mockData(){
        GenreDAO genreDAO = new GenreDAO();
        Genre genre = genreDAO.get(Long.valueOf(1));
        Date dataAtual = new Date();

        Track track = new Track("Track 1", dataAtual, 100, "picturePath", "filePath", false, false, genre);
        this.save(track);
        
    }

}
