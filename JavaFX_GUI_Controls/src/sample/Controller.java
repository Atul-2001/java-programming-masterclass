package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private Button btn;

    public void clickAction(ActionEvent actionEvent) {
        btn.setTextFill(Color.GREEN);
    }
}
