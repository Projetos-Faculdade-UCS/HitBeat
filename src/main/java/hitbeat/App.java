package hitbeat;

import hitbeat.dao.GenreDAO;
import hitbeat.view.IndexView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        GenreDAO.init();
        GenreDAO.mockData();
        IndexView.main(args);
        GenreDAO.close();
    }
}
