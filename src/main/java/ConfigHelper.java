import java.io.*;
import java.time.Instant;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

/**
 * @author Mother Ship
 * @date 2018/11/27 11:54
 */
public class ConfigHelper {
    private static final String JAR_FILE_PATH = System.getProperty("user.dir");
    private static final Properties CONFIG = new Properties();
    private static final File CONFIG_FILE = new File(JAR_FILE_PATH + "\\config.ini");
    public static void init() {
        boolean init = false;
        if (!CONFIG_FILE.exists()) {
            try {
                CONFIG_FILE.createNewFile();
            } catch (IOException e) {
                System.err.println(Instant.now() + " 初始化配置文件时发生IO异常：" + e.getMessage() + "，程序即将退出；");
                SleepHelper.sleep(3);
                System.exit(0);
            }
            init = true;
        }
        try (InputStream in =
                      new FileInputStream(CONFIG_FILE)) {
            CONFIG.load(in);
        } catch (IOException e) {
            System.err.println(Instant.now() + " 读取配置文件时发生IO异常：" + e.getMessage() + "，程序即将退出；");
            SleepHelper.sleep(3);
            System.exit(0);
        }
        if (init){
            write("X","200");
            write("Y","200");
            write("FontSize","30");
            write("FontColor","#66CCFF");
        }
    }
    public static void  write(String key,String value){
        CONFIG.setProperty(key,value);
        try (OutputStream in =
                     new FileOutputStream(CONFIG_FILE)) {
            CONFIG.store(in,null);
        } catch (IOException e) {
            System.err.println(Instant.now() + " 写入配置文件时发生IO异常：" + e.getMessage() + "，程序即将退出；");
            SleepHelper.sleep(3);
            System.exit(0);
        }
    }
    public static String load(String key){
       return CONFIG.getProperty(key);
    }
}
