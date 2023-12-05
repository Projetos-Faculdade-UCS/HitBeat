package hitbeat.util;

import java.time.Duration;
import java.util.Date;

import hitbeat.view.LoadingPage;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class AsyncLoading {
    private static <T> void loadAsyncCommon(Node node, Supplier<T> asyncOperation, Consumer<T> onLoaded) {
        // now
        Date start = new Date();
        LoadingPage loadingPage = new LoadingPage(false);
        
        if (node instanceof Pane) {
            ((Pane) node).getChildren().setAll(loadingPage);
        } else if (node instanceof ScrollPane) {
            ((ScrollPane) node).setContent(loadingPage);
        }

        Thread thread = new Thread(() -> {
            try {
                T result = asyncOperation.get();
                Date end = new Date();
                long diff = Duration.between(start.toInstant(), end.toInstant()).toMillis();

                if (diff < 100) {
                    Thread.sleep(100 - diff);
                }

                Platform.runLater(() -> {
                    try {
                        onLoaded.accept(result);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    public static <T> void loadAsync(ScrollPane node, Supplier<T> asyncOperation, Consumer<T> onLoaded) {
        loadAsyncCommon(node, asyncOperation, onLoaded);
    }

    public static <T> void loadAsync(Pane node, Supplier<T> asyncOperation, Consumer<T> onLoaded) {
        loadAsyncCommon(node, asyncOperation, onLoaded);
    }
}
