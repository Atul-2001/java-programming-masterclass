package com.signature.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMain extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.setMinWidth(460);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
