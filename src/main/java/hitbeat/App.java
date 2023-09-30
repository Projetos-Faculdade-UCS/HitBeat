package hitbeat;

import hitbeat.util.HibernateUtil;
import hitbeat.view.IndexView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        IndexView.main(args);

        HibernateUtil.shutdown();
    }
}
