package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Controller {
    @FXML private Label label;
    @FXML private Button file;
    @FXML private Button folder;
    @FXML private Button save;
    @FXML private GridPane gridPane;
    @FXML private WebView webPage;

    public void mouseEntered() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    public void mouseExited() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    public void handleFile(ActionEvent event) {
        File sFile = null;
       if (event.getSource().equals(file)) {
           FileChooser chooser = new FileChooser();
//           sFile = chooser.showOpenDialog(gridPane.getScene().getWindow());
           chooser.setTitle("Open File");
           chooser.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("Text", "*.txt"),
                   new FileChooser.ExtensionFilter("C", "*.c"),
                   new FileChooser.ExtensionFilter("C++", "*.cpp", "*.c++"),
                   new FileChooser.ExtensionFilter("Java", "*.java"),
                   new FileChooser.ExtensionFilter("All Files", "*.*")
           );

           List<File> files = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
           for (int i = 0; i < files.size(); i++) {
               System.out.println(files.get(i));
           }

           return;
        } else if (event.getSource().equals(folder)) {
           DirectoryChooser chooser = new DirectoryChooser();
           sFile = chooser.showDialog(gridPane.getScene().getWindow());

       } else if (event.getSource().equals(save)) {
           FileChooser chooser = new FileChooser();
           chooser.setTitle("Save File");
           chooser.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("Text", "*.txt"),
                   new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                   new FileChooser.ExtensionFilter("C", "*.c"),
                   new FileChooser.ExtensionFilter("Java", "*.java")
           );
           sFile = chooser.showSaveDialog(gridPane.getScene().getWindow());
       }

        if (sFile != null) {
            System.out.println(sFile);
        } else {
            System.out.println("Dialog was Closed!");
        }
    }

    public void handleLink() {
//        try {
//            Desktop.getDesktop().browse(new URI("https://www.youtube.com"));
//            System.out.println("Link is clicked");
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }

        WebEngine engine = webPage.getEngine();
        engine.load("https://www.youtube.com");
//        engine.load("/home/anonymous/Downloads/validationpart1.html");
    }
}
