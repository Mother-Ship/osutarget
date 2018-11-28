import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.Console;
import java.io.PrintStream;

/**
 * @author Mother Ship
 * @date 2018/11/26 17:13
 */
public class App extends Application {
    private static VBox box = new VBox();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text");
        primaryStage.setAlwaysOnTop(true);
        Text text = new Text("提示信息");
        text.setFont(new Font(Double.valueOf(ConfigHelper.load("FontSize"))));
        text.setFill(Color.valueOf(ConfigHelper.load("Color")));

        box.getChildren().add(text);
        box.setStyle("-fx-background:transparent;");

        new DragListener(primaryStage).enableDrag(box);

        final int width = 500;
        final int height = 50;
        final Scene scene = new Scene(box, width, height);
        scene.setFill(null);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);

        String x = ConfigHelper.load("X");
        String y = ConfigHelper.load("Y");
        if( x !=null &&y !=null) {
            primaryStage.setX(Double.valueOf(x));
            primaryStage.setY(Double.valueOf(y));
        }
        primaryStage.show();


//Create console textarea
        TextArea ta = new TextArea();
        ta.setPrefWidth(800);
        ta.setPrefHeight(400);
        ta.setWrapText(true);
        ta.setEditable(false);
        ta.setFont(new Font(15));
        //This Console class is A class extend the OutputStream, so it can receive the System.out.println() request,IT IS NOT java.io.Console!!!
        PrintStream ps = new PrintStream(new ConsoleOutputStream(ta));
        //Redirect system console IOStream
        System.setOut(ps);
        System.setErr(ps);

        //Create window to place console textarea
        Stage newWindow = new Stage();
        newWindow.setTitle("Console");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(ta);
        Scene scene2 = new Scene(vbox);
        newWindow.setScene(scene2);
        // Set position of console window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.show();
        // Set On Close Event
        newWindow.setOnCloseRequest(event -> System.exit(0));
        newWindow.setResizable(false);
        SleepHelper.sleep(2);
        System.out.println("test");
        setText("test");
    }
    public static void setText(String msg){
        Text text = new Text(msg);
        text.setFont(new Font(30));
        text.setFill(Color.GREY);
        box.getChildren().clear();
        box.getChildren().add(text);
    }
}
