package com.signature.UI;

import com.signature.DataModel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Contact");
//        primaryStage.getIcons().add(new Image(getClass().getResource("/resources/contactIcon.png").toString()));
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setTitle("Contact");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try {
            ContactData.getInstance().loadContacts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        try {
            ContactData.getInstance().saveContacts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Contact");
        launch(args);
    }
}
