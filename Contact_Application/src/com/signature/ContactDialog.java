package com.signature;

import com.signature.DataModel.Contact;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.util.Optional;

public class ContactDialog {
    private final Dialog<ButtonType> dialog;
    private final GridPane contactForm;
    private final TextField firstNameField;
    private final TextField lastNameField;
    private final TextField phoneNumberField;
    private final TextField notesField;

    public ContactDialog(Window window) {
        this.dialog = new Dialog<>();
        this.dialog.initOwner(window);

        this.contactForm = new GridPane();
        contactForm.setVgap(10);
        contactForm.setVgap(10);

        contactForm.add(new Label("First Name : "), 0, 0);
        this.firstNameField = new TextField();
        firstNameField.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                    if (firstNameField.isFocused()) {
                        lastNameField.requestFocus();
                    }
                }
            }
        });
        contactForm.add(firstNameField, 1, 0);

        contactForm.add(new Label("Last Name : "), 0, 1);
        this.lastNameField = new TextField();
        lastNameField.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                    if (lastNameField.isFocused()) {
                        phoneNumberField.requestFocus();
                    }
                } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                    if (lastNameField.isFocused()) {
                        firstNameField.requestFocus();
                    }
                }
            }
        });
        contactForm.add(lastNameField, 1, 1);

        contactForm.add(new Label("Phone Number : "), 0, 2);
        this.phoneNumberField = new TextField();
        phoneNumberField.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                    if (phoneNumberField.isFocused()) {
                        notesField.requestFocus();
                    }
                } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                    if (phoneNumberField.isFocused()) {
                        lastNameField.requestFocus();
                    }
                }

                phoneNumberField.setEditable(keyEvent.getCode().isDigitKey() //Character.isDigit(keyEvent.getCharacter().charAt(0))
                        || keyEvent.getCode().equals(KeyCode.BACK_SPACE));
            }
        });

        contactForm.add(phoneNumberField, 1, 2);

        contactForm.add(new Label("Notes : "), 0, 3);
        this.notesField = new TextField();
        notesField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (notesField.isFocused()) {
                    if (!dialog.getDialogPane().lookupButton(ButtonType.OK).isDisabled()) {
                        dialog.getDialogPane().lookupButton(ButtonType.OK).requestFocus();
                    } else {
                        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).requestFocus();
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (notesField.isFocused()) {
                    phoneNumberField.requestFocus();
                }
            }
        });
        contactForm.add(notesField, 1, 3);

        try {
            dialog.getDialogPane().setContent(contactForm);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dialog Error!");
            alert.setContentText("Failed to create Contact Dialog!");
            alert.showAndWait();
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.getDialogPane().lookupButton(ButtonType.OK).setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.OK).isFocused()) {
                    notesField.requestFocus();
                }
            }
        });

        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.CANCEL).isFocused()) {
                    notesField.requestFocus();
                }
            }
        });
    }

    public void setDialog(String title, String headerText) {
        dialog.setTitle(title);
        dialog.getDialogPane().setHeaderText(headerText);
    }

    public void loadData(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    public void processUpdate(Contact contact) {
        firstNameField.requestFocus();
        final String firstName = firstNameField.getText().trim();
        final String lastName = lastNameField.getText().trim();
        final String phoneNumber = phoneNumberField.getText().trim();
        final String notes = notesField.getText().trim();

        contactForm.setOnKeyReleased(keyEvent -> {
            boolean status = (firstNameField.getText().equals(firstName) &&
                    lastNameField.getText().equals(lastName) &&
                    phoneNumberField.getText().equals(phoneNumber) &&
                    notesField.getText().equals(notes));

            BooleanBinding buttonStatus = firstNameField.textProperty().isEmpty()
//                    .or(lastNameField.textProperty().isEmpty())
                    .or(phoneNumberField.textProperty().isEmpty());

            if (!status) {
                if (buttonStatus.get()) {
                    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(buttonStatus.get());
                } else {
                    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                }
            } else {
                dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
            }

        });

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            String note;
            if (notesField.getText().isEmpty()) {
                note = " ";
            } else {
                note = notesField.getText().trim();
            }

            contact.setFirstName(firstNameField.getText().trim());
            contact.setLastName(lastNameField.getText().trim());
            contact.setPhoneNumber(phoneNumberField.getText().trim());
            contact.setNotes(note);
        }
    }

    public Contact processAddNew() {
        firstNameField.requestFocus();
        contactForm.setOnKeyReleased(keyEvent -> {
            BooleanBinding buttonBinding = firstNameField.textProperty().isEmpty()
//                    .or(lastNameField.textProperty().isEmpty())
                    .or(phoneNumberField.textProperty().isEmpty());

            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(buttonBinding.get());
        });

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            String notes = notesField.getText().trim();

            if (notes.isEmpty()) {
                notes = " ";
            }

//            Contact contact = new Contact(firstName, lastName, phoneNumber, notes);
//            ContactData.getInstance().addContact(contact);

            return null;
        } else {
            return null;
        }
    }
}
