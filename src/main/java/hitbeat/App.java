package hitbeat;

import hitbeat.util.HibernateUtil;
import hitbeat.view.IndexView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            HibernateUtil.shutdown();
        }));

        // GenreDAO.mockData();
        // TrackDAO.mockData();
        IndexView.main(args);

        HibernateUtil.shutdown();
    }
}
