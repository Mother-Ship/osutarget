import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Mother Ship
 * @date 2018/11/27 16:25
 */
public class ConsoleOutputStream extends OutputStream {
    private TextArea output;

    ConsoleOutputStream(TextArea ta) {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException {
        Platform.runLater(()->output.appendText(String.valueOf((char) i)));
        output.setScrollTop(Double.MAX_VALUE);
    }
}
