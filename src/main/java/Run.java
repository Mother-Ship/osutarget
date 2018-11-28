import java.util.concurrent.TimeUnit;

import static javafx.application.Application.launch;

/**
 * @author Mother Ship
 * @date 2018/11/26 17:53
 */
public class Run {
    public static void main(String[] args) {
        ConfigHelper.init();
        launch(App.class,args);
    }
}
