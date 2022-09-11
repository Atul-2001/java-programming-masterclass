package com.signature;

import com.signature.DataModel.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ContactDetailController {

    protected static Contact contact;
    @FXML private Circle profile;
    @FXML private Label firstName;
    @FXML private Label lastName;
    @FXML private Label phoneNumber;
    @FXML private Label notes;

    public void initialize() {
        if (!contact.getProfile().equals(" ")) {
            profile.setFill(new ImagePattern(new Image(contact.getProfile())));
        }
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        phoneNumber.setText(contact.getPhoneNumber());
        notes.setText(contact.getNotes());
    }

    public void handleBack(ActionEvent actionEvent) {
        Controller.selectContact = contact;
        Main.scene.setRoot(Main.parent);
    }
}
