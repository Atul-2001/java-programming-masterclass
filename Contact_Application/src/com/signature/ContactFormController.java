package com.signature;

import com.signature.DataModel.Contact;
import com.signature.DataModel.ContactData;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContactFormController {

    @FXML private DialogPane formDialog;
    @FXML private TitledPane moreInfoPane;
    @FXML private TextField firstNameField, lastNameField, companyField, phoneField, emailField;
    @FXML private ComboBox<String> phoneTypeCB, emailTypeCB;
    @FXML private TextField addressField, websiteField, relationshipField, sipField, notesField;
    @FXML private DatePicker dateField;
    @FXML private ComboBox<String> dateTypeCB, relationTypeCB;
    @FXML private Circle icon;
    @FXML private SVGPath addIcon;
    @FXML private Hyperlink removeProfile;
    private String file = null;

    public void initialize(Dialog<ButtonType> dialog) {
        firstNameField.requestFocus();

        firstNameField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (firstNameField.isFocused()) {
                    lastNameField.requestFocus();
                }
            }
        });

        lastNameField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (lastNameField.isFocused()) {
                    companyField.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (lastNameField.isFocused()) {
                    firstNameField.requestFocus();
                }
            }
        });

        companyField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (companyField.isFocused()) {
                    phoneField.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (companyField.isFocused()) {
                    lastNameField.requestFocus();
                }
            }
        });

        phoneField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (phoneField.isFocused()) {
                    phoneTypeCB.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (phoneField.isFocused()) {
                    companyField.requestFocus();
                }
            }

            phoneField.setEditable(enableNumericField(keyEvent));
        });

        phoneTypeCB.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (phoneTypeCB.isFocused()) {
                    emailField.requestFocus();
                }
            }
        });

        emailField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (emailField.isFocused()) {
                    emailTypeCB.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (emailField.isFocused()) {
                    phoneTypeCB.requestFocus();
                }
            }
        });

        emailTypeCB.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (emailTypeCB.isFocused()) {
                    if (moreInfoPane.isExpanded()) {
                        addressField.requestFocus();
                    } else {
                        if (dialog.getDialogPane().lookupButton(ButtonType.OK).isDisabled()) {
                            dialog.getDialogPane().lookupButton(ButtonType.CANCEL).requestFocus();
                        } else {
                            dialog.getDialogPane().lookupButton(ButtonType.OK).requestFocus();
                        }
                    }
                }
            }
        });

        moreInfoPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (moreInfoPane.isFocused()) {
                    if (dialog.getDialogPane().lookupButton(ButtonType.OK).isDisabled()) {
                        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).requestFocus();
                    } else {
                        dialog.getDialogPane().lookupButton(ButtonType.OK).requestFocus();
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (moreInfoPane.isFocused()) {
                    emailTypeCB.requestFocus();
                }
            }
        });

        addressField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (addressField.isFocused()) {
                    websiteField.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (addressField.isFocused()) {
                    emailTypeCB.requestFocus();
                }
            }
        });

        websiteField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (websiteField.isFocused()) {
                    dateField.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (websiteField.isFocused()) {
                    addressField.requestFocus();
                }
            }
        });

        dateField.setOnKeyPressed(keyEvent -> {
            if (/*keyEvent.getCode().equals(KeyCode.ENTER) || */keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (dateField.isFocused()) {
                    dateTypeCB.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (dateField.isFocused()) {
                    websiteField.requestFocus();
                }
            }
        });

        dateTypeCB.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (dateTypeCB.isFocused()) {
                    relationshipField.requestFocus();
                }
            }
        });

        relationshipField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (relationshipField.isFocused()) {
                    relationTypeCB.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (relationshipField.isFocused()) {
                    dateTypeCB.requestFocus();
                }
            }
        });

        relationTypeCB.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (relationTypeCB.isFocused()) {
                    sipField.requestFocus();
                }
            }
        });

        sipField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (sipField.isFocused()) {
                    notesField.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (sipField.isFocused()) {
                    relationTypeCB.requestFocus();
                }
            }

            sipField.setEditable(enableNumericField(keyEvent));
        });

        notesField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (notesField.isFocused()) {
                    if (dialog.getDialogPane().lookupButton(ButtonType.OK).isDisabled()) {
                        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).requestFocus();
                    } else {
                        dialog.getDialogPane().lookupButton(ButtonType.OK).requestFocus();
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (notesField.isFocused()) {
                    sipField.requestFocus();
                }
            }
        });

        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.CANCEL).isFocused()) {
                    if (moreInfoPane.isExpanded()) {
                        notesField.requestFocus();
                    } else {
                        emailTypeCB.requestFocus();
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.CANCEL).isFocused()) {
                    if (!dialog.getDialogPane().lookupButton(ButtonType.OK).isDisabled()) {
                        dialog.getDialogPane().lookupButton(ButtonType.OK).requestFocus();
                    }
                }
            }
        });

        dialog.getDialogPane().lookupButton(ButtonType.OK).setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.OK).isFocused()) {
                    if (moreInfoPane.isExpanded()) {
                        notesField.requestFocus();
                    } else {
                        emailTypeCB.requestFocus();
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                if (dialog.getDialogPane().lookupButton(ButtonType.OK).isFocused()) {
                    dialog.getDialogPane().lookupButton(ButtonType.CANCEL).requestFocus();
                }
            }
        });

        formDialog.setOnKeyReleased(keyEvent -> {
            BooleanBinding buttonStatus = firstNameField.textProperty().isEmpty()
                    .or(phoneField.textProperty().isEmpty());
            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(buttonStatus.get());
        });
    }

    private boolean enableNumericField(KeyEvent keyEvent) {
        return keyEvent.getCode().isDigitKey()
                || keyEvent.getCode().equals(KeyCode.BACK_SPACE)
                || keyEvent.getCode().equals(KeyCode.RIGHT)
                || keyEvent.getCode().equals(KeyCode.LEFT)
                || keyEvent.getCode().equals(KeyCode.UP)
                || keyEvent.getCode().equals(KeyCode.DOWN)
                || keyEvent.getCode().equals(KeyCode.HOME)
                || keyEvent.getCode().equals(KeyCode.END);
    }

    public void handleEntered() {
        addIcon.setOpacity(1.0);
        icon.setOpacity(0.5);
    }

    public void handleExit() {
        addIcon.setOpacity(0.0);
        icon.setOpacity(1);
    }

    private Contact getData() {
        String profile = (file == null ? " " : file);
        String firstName = firstNameField.getText().trim();
        String lastName = (lastNameField.getText().trim().isEmpty() ? " " : lastNameField.getText().trim());
        String company = (companyField.getText().trim().isEmpty() ? " " : companyField.getText().trim());
        String phoneNumber = phoneField.getText().trim();
        String phoneLabel = phoneTypeCB.getSelectionModel().getSelectedItem();
        String email = (emailField.getText().trim().isEmpty() ? " " : emailField.getText().trim());
        String emailLabel = (email.equals(" ") ? " " : emailTypeCB.getSelectionModel().getSelectedItem());
        String address = (addressField.getText().trim().isEmpty() ? " " : addressField.getText().trim());
        String website = (websiteField.getText().trim().isEmpty() ? " " : websiteField.getText().trim());
        String date;
        try {
            date = dateField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (NullPointerException e) {
            date = " ";
        }
        String dateLabel = (date.equals(" ") ? " " :dateTypeCB.getSelectionModel().getSelectedItem());
        String relationship = (relationshipField.getText().trim().isEmpty() ? " " : relationshipField.getText().trim());
        String relationLabel = (relationship.equals(" ") ? " " : relationTypeCB.getSelectionModel().getSelectedItem());
        String sip = (sipField.getText().trim().isEmpty() ? " " : sipField.getText().trim());
        String notes = (notesField.getText().trim().isEmpty() ? " " : notesField.getText().trim());

        Contact contact = new Contact(profile,
                firstName,
                lastName,
                company,
                phoneNumber,
                phoneLabel,
                email,
                emailLabel,
                address,
                website,
                date,
                dateLabel,
                relationship,
                relationLabel,
                sip,
                notes);

        ContactData.getInstance().addContact(contact);

        return contact;
    }

    public Contact processData() {
        return getData();
    }

    public void handleAddProfile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png", "*.gif", "*.tif"));

        file = fileChooser.showOpenDialog(formDialog.getScene().getWindow()).toURI().toString();
        ImagePattern profile = new ImagePattern(new Image(file));
        icon.setFill(profile);
        removeProfile.setOpacity(1.0);
        icon.requestFocus();
    }

    public void handleRemoveProfile() {
        ImagePattern defaultProfile = new ImagePattern(new Image(getClass().getResource("/resources/contactIcon.png").toString()));
        icon.setFill(defaultProfile);
        removeProfile.setOpacity(0.0);
        file = null;
    }

    public void loadContact(Contact contact) {
        if (!contact.getProfile().equals(" ")) {
            icon.setFill(new ImagePattern(new Image(contact.getProfile())));
        }

        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        companyField.setText(contact.getCompany());
        phoneField.setText(contact.getPhoneNumber());
        phoneTypeCB.getSelectionModel().select(contact.getPhoneType());
        emailField.setText(contact.getEmail());
        emailTypeCB.getSelectionModel().select(contact.getEmailType());

        addressField.setText(contact.getAddress());
        websiteField.setText(contact.getWebsite());
        if (!contact.getDate().equals(" ")) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
            LocalDate date = LocalDate.parse(contact.getDate(), df);
            dateField.setValue(date);
        }
        dateTypeCB.getSelectionModel().select(contact.getDateType());
        relationshipField.setText(contact.getRelation());
        relationTypeCB.getSelectionModel().select(contact.getRelationType());
        sipField.setText(contact.getSip());
        notesField.setText(contact.getNotes());

        if (!contact.getAddress().equals(" ") || !contact.getWebsite().equals(" ")
                || !contact.getDate().equals(" ") || !contact.getDateType().equals(" ")
                || !contact.getRelation().equals(" ") || !contact.getRelationType().equals(" ")
                || !contact.getSip().equals(" ") || !contact.getNotes().equals(" ")) {
            moreInfoPane.setExpanded(true);
            System.out.println(moreInfoPane.getId());
        }
    }

    public void updateContact(Contact contact) {
        Contact newContact = getData();

        contact.setProfile(newContact.getProfile());

        contact.setFirstName(newContact.getFirstName());
        contact.setLastName(newContact.getLastName());
        contact.setCompany(newContact.getCompany());
        contact.setPhoneNumber(newContact.getPhoneNumber());
        contact.setPhoneType(newContact.getPhoneType());
        contact.setEmail(newContact.getEmail());
        contact.setEmailType(newContact.getEmailType());

        contact.setAddress(newContact.getAddress());
        contact.setWebsite(newContact.getWebsite());
        contact.setDate(newContact.getDate());
        contact.setDateType(newContact.getDateType());
        contact.setRelation(newContact.getRelation());
        contact.setRelationType(newContact.getRelationType());
        contact.setSip(newContact.getSip());
        contact.setNotes(newContact.getNotes());

    }
}
