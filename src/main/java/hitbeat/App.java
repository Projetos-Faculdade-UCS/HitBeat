package hitbeat;

import hitbeat.util.HibernateUtil;
import hitbeat.view.MainApp;

public class App {
    public static void main(String[] args) {

        // Add shutdown hook to gracefully shutdown Hibernate
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            HibernateUtil.shutdown();
        }));
        MainApp.main(args);
    }

}
