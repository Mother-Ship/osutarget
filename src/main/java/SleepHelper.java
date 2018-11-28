import java.util.concurrent.TimeUnit;

/**
 * @author Mother Ship
 * @date 2018/11/27 12:00
 */
public class SleepHelper {
    public static void  sleep(TimeUnit unit,long timeout){
        try {
            unit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void  sleep(long timeout){
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
