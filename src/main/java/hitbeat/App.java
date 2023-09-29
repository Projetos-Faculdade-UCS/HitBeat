package hitbeat;

import hitbeat.dao.GenreDAO;
import hitbeat.dao.TrackDAO;
import hitbeat.view.IndexView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        GenreDAO.init();
        TrackDAO.init();
        // GenreDAO.mockData();
        IndexView.main(args);
        TrackDAO.close();
        GenreDAO.close();
    }
}
