package com.signature;

import com.signature.DataModel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent parent;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        parent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        primaryStage.setTitle("Contact");
        primaryStage.getIcons().add(new Image(getClass().getResource("/resources/contactIcon.png").toString()));
        scene = new Scene(parent, 800, 375);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
//        Image image = new Image(new File("resources/contactIcon.png").toURI().toString());
//        System.out.println(getClass().getResource("/resources/contactIcon.png"));
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
}

