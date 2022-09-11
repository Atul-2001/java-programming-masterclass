package com.signature.UI;

import com.jfoenix.controls.JFXButton;
import com.signature.DataModel.Contact;
import com.signature.DataModel.ContactData;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class ShowContactController {

    @FXML
    private BorderPane rootNode;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton favourite;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton delete;
    @FXML
    private VBox detailBox;
    @FXML
    private Circle profile;
    @FXML
    private Label name;
    @FXML
    private HBox phoneHB;
    @FXML
    private Label phoneNumber;
    @FXML
    private HBox emailHB;
    @FXML
    private Label email;
    @FXML
    private HBox addressHB;
    @FXML
    private Label address;
    @FXML
    private HBox companyHB;
    @FXML
    private Label company;
    @FXML
    private HBox websiteHB;
    @FXML
    private Label website;
    @FXML
    private HBox dateHB;
    @FXML
    private Label date;
    @FXML
    private HBox relationHB;
    @FXML
    private Label relation;
    @FXML
    private HBox sipHB;
    @FXML
    private Label sip;
    @FXML
    private HBox notesHB;
    @FXML
    private Label notes;

    public static Contact contactToView = null;
    private final FadeTransition fadeTransition = new FadeTransition(Duration.millis(100));

    public void initialize() {
        makeFadeIn();
        loadData();
    }

    public void makeFadeIn() {
        fadeTransition.setNode(rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void makeFadeOut(String screen, String title) {
        fadeTransition.setNode(rootNode);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadScreen(screen, title);
            }
        });
        fadeTransition.play();
    }

    public void loadScreen(String screen, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(screen));
            Stage curStage = (Stage) rootNode.getScene().getWindow();
            Scene scene = new Scene(root, 640, 480);
            curStage.setTitle(title);
            curStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadData() {
        String Profile = contactToView.getProfile();
        String firstName = contactToView.getFirstName();
        String lastName = contactToView.getLastName();
        String phone = contactToView.getPhoneNumber();
        String phoneType = contactToView.getPhoneType();
        String e_mail = contactToView.getEmail();
        String emailType = contactToView.getEmailType();
        String Address = contactToView.getAddress();
        String Company = contactToView.getCompany();
        String Website = contactToView.getWebsite();
        String dateField = contactToView.getDate();
        String dateType = contactToView.getDateType();
        String Relation = contactToView.getRelation();
        String relationType = contactToView.getRelationType();
        String SIP = contactToView.getSip();
        String Notes = contactToView.getNotes();

        if (Profile.compareToIgnoreCase(" ") == 0) {
            profile.setFill(new ImagePattern(new Image(getClass().getResource("/resources/ic_account_circle_black_48dp.png").toString())));
        } else {
            profile.setFill(new ImagePattern(new Image(Profile)));
        }

        if (lastName.compareToIgnoreCase(" ") == 0) {
            name.setText(firstName);
        } else {
            name.setText(firstName + " " + lastName);
        }

        phoneNumber.setText(phoneType + " :: " + phone);

        if (e_mail.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(emailHB);
        } else {
            email.setText(emailType + " :: " + e_mail);
        }

        if (Address.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(addressHB);
        } else {
            address.setText(Address);
        }

        if (Company.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(companyHB);
        } else {
            company.setText(Company);
        }

        if (Website.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(websiteHB);
        } else {
            website.setText(Website);
        }

        if (dateField.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(dateHB);
        } else {
            date.setText(dateType + " :: " + dateField);
        }

        if (Relation.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(relationHB);
        } else {
            relation.setText(relationType + " :: " + Relation);
        }

        if (SIP.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(sipHB);
        } else {
            sip.setText(SIP);
        }

        if (Notes.compareToIgnoreCase(" ") == 0) {
            detailBox.getChildren().remove(notesHB);
        } else {
            notes.setText(Notes);
        }

        if (contactToView.isFavourite()) {
            SVGPath yFavourite = new SVGPath();
            yFavourite.setContent("M 18 32.023438 L 15.824219 30.050781 C 8.101562 23.039062 3 18.414062 3 12.75 C 3 8.121094 6.621094 4.5 11.25 4.5 C 13.859375 4.5 16.363281 5.714844 18 7.628906 C 19.636719 5.714844 22.140625 4.5 24.75 4.5 C 29.378906 4.5 33 8.121094 33 12.75 C 33 18.414062 27.898438 23.039062 20.175781 30.050781 Z M 18 32.023438 ");
            yFavourite.setFill(Color.rgb(219, 50, 54, 0.8));
            favourite.setGraphic(yFavourite);
        }
    }

    @FXML
    public void handleBackOperation(ActionEvent actionEvent) {
        contactToView = null;
        makeFadeOut("HomeScreen.fxml", "Contact");
    }

    @FXML
    public void handleSetFavourite(ActionEvent actionEvent) {
        if (contactToView.isFavourite()) {
            SVGPath nFavourite = new SVGPath();
            nFavourite.setContent("M 24.75 4.5 C 22.140625 4.5 19.636719 5.714844 18 7.628906 C 16.363281 5.714844 13.859375 4.5 11.25 4.5 C 6.621094 4.5 3 8.121094 3 12.75 C 3 18.414062 8.101562 23.039062 15.824219 30.050781 L 18 32.023438 L 20.175781 30.050781 C 27.898438 23.039062 33 18.414062 33 12.75 C 33 8.121094 29.378906 4.5 24.75 4.5 Z M 18.15625 27.832031 L 18 27.976562 L 17.84375 27.832031 C 10.710938 21.359375 6 17.085938 6 12.75 C 6 9.757812 8.257812 7.5 11.25 7.5 C 13.558594 7.5 15.808594 8.992188 16.597656 11.039062 L 19.394531 11.039062 C 20.191406 8.992188 22.441406 7.5 24.75 7.5 C 27.742188 7.5 30 9.757812 30 12.75 C 30 17.085938 25.289062 21.359375 18.15625 27.832031 Z M 18.15625 27.832031");
            nFavourite.setFill(Color.rgb(72, 133, 237));
            favourite.setGraphic(nFavourite);
            contactToView.setFavourite(false);
        } else {
            SVGPath yFavourite = new SVGPath();
            yFavourite.setContent("M 18 32.023438 L 15.824219 30.050781 C 8.101562 23.039062 3 18.414062 3 12.75 C 3 8.121094 6.621094 4.5 11.25 4.5 C 13.859375 4.5 16.363281 5.714844 18 7.628906 C 19.636719 5.714844 22.140625 4.5 24.75 4.5 C 29.378906 4.5 33 8.121094 33 12.75 C 33 18.414062 27.898438 23.039062 20.175781 30.050781 Z M 18 32.023438 ");
            yFavourite.setFill(Color.rgb(219, 50, 54, 0.8));
            favourite.setGraphic(yFavourite);
            contactToView.setFavourite(true);
        }
    }

    @FXML
    public void handleEditContact(ActionEvent actionEvent) {
        AddEditContactController.contactToEdit = contactToView;
        AddEditContactController.fromShowContact = true;
        makeFadeOut("AddEditContact.fxml", "Contact - Edit Contact");
    }

    @FXML
    public void handleDeleteContact(ActionEvent actionEvent) {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Delete Contact");
        deleteAlert.setHeaderText("Contact : " + contactToView.getFirstName() + " " + contactToView.getLastName());
        deleteAlert.setContentText("Are you sure to delete the contact ?\nPress OK to Delete, Cancel to go back!");

        Optional<ButtonType> response = deleteAlert.showAndWait();
        if (response.isPresent() && response.get().equals(ButtonType.OK)) {
            ContactData.getInstance().deleteContact(contactToView);
            makeFadeOut("HomeScreen.fxml", "Contact");
        }
    }
}
