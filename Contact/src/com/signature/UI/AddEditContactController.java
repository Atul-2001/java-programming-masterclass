package com.signature.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.signature.DataModel.Contact;
import com.signature.DataModel.ContactData;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddEditContactController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ScrollPane scrollView;
    @FXML
    private VBox contactForm;
    @FXML
    private Label heading;
    @FXML
    private SVGPath headingIcon;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    @FXML
    private Circle profile;
    @FXML
    private SVGPath addProfile;
    @FXML
    private JFXButton removeProfile;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXComboBox<String> phoneType;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXComboBox<String> emailType;
    @FXML
    private JFXTextField address;
    @FXML
    private Label moreFields;
    @FXML
    private JFXTextField company;
    @FXML
    private JFXTextField website;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> dateType;
    @FXML
    private JFXTextField relation;
    @FXML
    private JFXComboBox<String> relationType;
    @FXML
    private JFXTextField sip;
    @FXML
    private JFXTextField notes;
    @FXML
    private HBox companyHB, websiteHB, dateHB, relationHB, sipHB, notesHB;
    private final List<Node> extraFields = new ArrayList<>();

    private final FadeTransition fadeTransition = new FadeTransition(Duration.millis(100));
    private String file = null;
    protected static Contact contactToEdit = null;
    protected static boolean fromShowContact = false;

    public void initialize() {
        rootPane.setOpacity(0.0);
        makeFadeIn();
        profile.setFill(new ImagePattern(new Image(getClass().getResource("/resources/ic_account_circle_black_48dp.png").toString())));

        extraFields.add(companyHB);
        extraFields.add(websiteHB);
        extraFields.add(dateHB);
        extraFields.add(dateType);
        extraFields.add(relationHB);
        extraFields.add(relationType);
        extraFields.add(sipHB);
        extraFields.add(notesHB);

        contactForm.getChildren().removeAll(extraFields);

        if (contactToEdit != null) {
            loadContact();
        }

        firstName.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                lastName.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        lastName.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                phoneNumber.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                firstName.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        phoneNumber.setOnKeyPressed(keyEvent -> {
            phoneNumber.setEditable(enableNumericField(keyEvent));
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                phoneType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                lastName.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        phoneType.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                email.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        email.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                emailType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                phoneType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        emailType.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (emailType.isFocused()) {
                    address.requestFocus();
                    if (contactForm.getChildren().lastIndexOf(moreFields) == 7) {
                        Platform.runLater(() -> {scrollView.setVvalue(0.546875);});
                    } else {
                        Platform.runLater(() -> {scrollView.setVvalue(0.09275);});
                    }
                }
            }
        });

        address.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (contactForm.getChildren().lastIndexOf(moreFields) == 7) {
                    if (save.isDisable()) {
                        cancel.requestFocus();
                    } else {
                        save.requestFocus();
                    }
                    Platform.runLater(() -> {scrollView.setVvalue(1.0);});
                } else {
                    company.requestFocus();
                    Platform.runLater(() -> {scrollView.setVvalue(0.2);});
                }
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                emailType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.0);});
            }
        });

        company.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                website.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.3);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                address.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.2);});
            }
        });

        website.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                date.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.41285);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                company.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.4);});
            }
        });

        date.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                dateType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.5);});
            }
        });

        dateType.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                relation.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.62);});
            }
        });

        relation.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                relationType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.7145);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                dateType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.6);});
            }
        });

        relationType.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                sip.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.825);});
            }
        });

        sip.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                notes.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(0.94);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                relationType.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(1.0);});
            }
        });

        notes.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                if (save.isDisable()) {
                    cancel.requestFocus();
                } else {
                    save.requestFocus();
                }
                Platform.runLater(() -> {scrollView.setVvalue(1.0);});
            } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                sip.requestFocus();
                Platform.runLater(() -> {scrollView.setVvalue(1.0);});
            }
        });

        save.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (contactForm.getChildren().lastIndexOf(extraFields) == 7) {
                    address.requestFocus();
                } else {
                    notes.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                cancel.requestFocus();
            }
        });

        cancel.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                if (contactForm.getChildren().lastIndexOf(moreFields) == 7) {
                    address.requestFocus();
                } else {
                    notes.requestFocus();
                }
            } else if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                if (!save.isDisable()) {
                    save.requestFocus();
                }
            }
        });

        contactForm.setOnKeyReleased(keyEvent -> {
            BooleanBinding buttonStatus = firstName.textProperty().isEmpty()
                    .or(phoneNumber.textProperty().isEmpty());
            save.setDisable(buttonStatus.get());
        });

    }

    public void makeFadeIn() {
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void makeFadeOut(String screen) {
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadScreen(screen);
            }
        });
        fadeTransition.play();
    }

    public void loadScreen(String screen) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(screen));
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(root, 640, 480);
            curStage.setTitle("Contact");
            curStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void showAddProfile(MouseEvent mouseEvent) {
        profile.setOpacity(0.5);
        addProfile.setOpacity(1.0);
    }

    @FXML
    public void hideAddProfile(MouseEvent mouseEvent) {
        addProfile.setOpacity(0.0);
        profile.setOpacity(1.0);
    }

    @FXML
    public void handleAddProfile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png", "*.gif", "*.tif"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Pictures"));
        File imageFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (imageFile != null) {
            file = imageFile.toURI().toString();
            Image image = new Image(file);
            profile.setFill(new ImagePattern(image));
            profile.setStroke(Color.rgb(60, 186, 84));
            removeProfile.setOpacity(1.0);
        }

        if (contactToEdit != null) {
            save.setDisable(false);
        }
    }

    @FXML
    public void handleRemoveProfile(ActionEvent actionEvent) {
        profile.setFill(new ImagePattern(new Image(getClass().getResource("/resources/ic_account_circle_black_48dp.png").toString())));
        profile.setStroke(Color.rgb(242, 193, 15));
        removeProfile.setOpacity(0.0);
        file = null;
        if (contactToEdit != null) {
            save.setDisable(false);
        }
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

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        if (fromShowContact) {
            AddEditContactController.fromShowContact = false;
            contactToEdit = null;
            makeFadeOut("ShowContact.fxml");
        } else {
            contactToEdit = null;
            makeFadeOut("HomeScreen.fxml");
        }
    }

    @FXML
    public void handleShowFields(MouseEvent mouseEvent) {
        if (moreFields.getText().equals("More fields")) {
            contactForm.getChildren().remove(moreFields);
            contactForm.getChildren().addAll(extraFields);
            contactForm.getChildren().add(moreFields);
            moreFields.setText("Less fields");
        } else if (moreFields.getText().equals("Less fields")) {
            contactForm.getChildren().removeAll(extraFields);
            moreFields.setText("More fields");
        }
    }

    @FXML
    public void handleSaveContact(ActionEvent actionEvent) {
        if (contactToEdit == null) {
            Contact contact = getData();
            ContactData.getInstance().addContact(contact);
            HomeController.contactToSelect = contact;
        } else {
            updateContact();
            HomeController.contactToSelect = contactToEdit;
        }

        if (fromShowContact) {
            AddEditContactController.fromShowContact = false;
            contactToEdit = null;
            makeFadeOut("ShowContact.fxml");
        } else {
            contactToEdit = null;
            makeFadeOut("HomeScreen.fxml");
        }
    }

    private void updateContact() {
        Contact newContact = getData();

        contactToEdit.setProfile(newContact.getProfile());
        contactToEdit.setFirstName(newContact.getFirstName());
        contactToEdit.setLastName(newContact.getLastName());
        contactToEdit.setAddress(newContact.getAddress());
        contactToEdit.setPhoneNumber(newContact.getPhoneNumber());
        contactToEdit.setPhoneType(newContact.getPhoneType());
        contactToEdit.setEmail(newContact.getEmail());
        contactToEdit.setEmailType(newContact.getEmailType());
        contactToEdit.setCompany(newContact.getCompany());
        contactToEdit.setWebsite(newContact.getWebsite());
        contactToEdit.setDate(newContact.getDate());
        contactToEdit.setDateType(newContact.getDateType());
        contactToEdit.setRelation(newContact.getRelation());
        contactToEdit.setRelationType(newContact.getRelationType());
        contactToEdit.setSip(newContact.getSip());
        contactToEdit.setNotes(newContact.getNotes());
        contactToEdit = null;
    }

    private Contact getData() {
        String profile = (file == null ? " " : file);
        String FirstName = firstName.getText().trim();
        String LastName = (lastName.getText().trim().isEmpty() ? " " : lastName.getText().trim());
        String PhoneNumber = phoneNumber.getText().trim();
        String PhoneLabel = phoneType.getSelectionModel().getSelectedItem();
        String Email = (email.getText().trim().isEmpty() ? " " : email.getText().trim());
        String EmailLabel = emailType.getSelectionModel().getSelectedItem();
        String Address = (address.getText().trim().isEmpty() ? " " : address.getText().trim());
        String Company = (company.getText().trim().isEmpty() ? " " : company.getText().trim());
        String Website = (website.getText().trim().isEmpty() ? " " : website.getText().trim());
        String dateField;
        try {
            dateField = date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (NullPointerException e) {
            dateField = " ";
        }
        String dateLabel = dateType.getSelectionModel().getSelectedItem();
        String Relation = (relation.getText().trim().isEmpty() ? " " : relation.getText().trim());
        String relationLabel = relationType.getSelectionModel().getSelectedItem();
        String SIP = (sip.getText().trim().isEmpty() ? " " : sip.getText().trim());
        String Notes = (notes.getText().trim().isEmpty() ? " " : notes.getText().trim());

        Contact contact = new Contact(profile,
                FirstName,
                LastName,
                PhoneNumber,
                PhoneLabel,
                Email,
                EmailLabel,
                Address,
                Company,
                Website,
                dateField,
                dateLabel,
                Relation,
                relationLabel,
                SIP,
                Notes);

        return contact;
    }

    private void loadContact() {
        heading.setText("Edit Contact");
        headingIcon.setContent("M6 34.5V42h7.5l22.13-22.13-7.5-7.5L6 34.5zm35.41-20.41c.78-.78.78-2.05 0-2.83l-4.67-4.67c-.78-.78-2.05-.78-2.83 0l-3.66 3.66 7.5 7.5 3.66-3.66z");
        if (!contactToEdit.getProfile().equals(" ")) {
            file = contactToEdit.getProfile();
            profile.setFill(new ImagePattern(new Image(file)));
            removeProfile.setOpacity(1.0);
        }

        String LastName, Email, Address, Company, Website, dateField, Relation, SIP, Notes;

        LastName = contactToEdit.getLastName();
        Email = contactToEdit.getEmail();
        Address = contactToEdit.getAddress();
        Company = contactToEdit.getCompany();
        Website = contactToEdit.getWebsite();
        dateField = contactToEdit.getDate();
        Relation = contactToEdit.getRelation();
        SIP = contactToEdit.getSip();
        Notes = contactToEdit.getNotes();

        firstName.setText(contactToEdit.getFirstName());
        lastName.setText(LastName.equals(" ") ? "" : LastName);
        phoneNumber.setText(contactToEdit.getPhoneNumber());
        phoneType.getSelectionModel().select(contactToEdit.getPhoneType());
        email.setText(Email.equals(" ") ? "" : Email);
        emailType.getSelectionModel().select(contactToEdit.getEmailType());
        address.setText(Address.equals(" ") ? "" : Address);

        company.setText(Company.equals(" ") ? "" : Company);
        website.setText(Website.equals(" ") ? "" : Website);
        if (!contactToEdit.getDate().equals(" ")) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
            LocalDate localDate = LocalDate.parse(contactToEdit.getDate(), df);
            date.setValue(localDate);
        }
        dateType.getSelectionModel().select(contactToEdit.getDateType());
        relation.setText(Relation.equals(" ") ? "" : Relation);
        relationType.getSelectionModel().select(contactToEdit.getRelationType());
        sip.setText(SIP.equals(" ") ? "" : SIP);
        notes.setText(Notes.equals(" ") ? "" : Notes);

        if (!Company.equals(" ") || !Website.equals(" ")
                || !dateField.equals(" ") || !Relation.equals(" ")
                || !SIP.equals(" ") || !Notes.equals(" ")) {
            contactForm.getChildren().remove(moreFields);
            contactForm.getChildren().addAll(extraFields);
            contactForm.getChildren().add(moreFields);
            moreFields.setText("Less fields");
        }
    }
}