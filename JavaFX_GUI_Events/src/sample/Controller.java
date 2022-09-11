package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.EventListener;

public class Controller {

    @FXML private TextField nameField;
    @FXML private Button btnHello;
    @FXML private Button btnBye;
    @FXML private CheckBox clearTextField;
    @FXML private Label threadMessage;

    public void initialize() {
        btnHello.setDisable(true);
        btnBye.setDisable(true);
    }

    @FXML
    public void btnClickEvent(ActionEvent event) {
        if (event.getSource().equals(btnHello)) {
            System.out.println("Hello " + nameField.getText());
        } else if(event.getSource().equals(btnBye)) {
            System.out.println("Bye " + nameField.getText());
//            System.exit(0);
        }

        Runnable bTask = new Runnable() {
            @Override
            public void run() {
                String message = (Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread");
                System.out.println("I'm going to sleep on the: " + message);
                try {
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            threadMessage.setText("10s has completed. Something has happened!");
                            String message = (Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread");
                            System.out.println("I'm updating the label on the: " + message);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } ;

        new Thread(bTask).start();

        if (clearTextField.isSelected()) {
            nameField.clear();
            btnHello.setDisable(true);
            btnBye.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased() {
        String str = nameField.getText();
        boolean disable = str.isEmpty() || str.trim().isEmpty();
        btnHello.setDisable(disable);
        btnBye.setDisable(disable);
    }

    @FXML
    public void checkedEvent() {
        System.out.println("CheckBox is " + (clearTextField.isSelected() ? "checked" : "not checked"));
    }
}
