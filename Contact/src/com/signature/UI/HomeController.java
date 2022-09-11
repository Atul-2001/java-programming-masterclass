package com.signature.UI;

import com.jfoenix.controls.*;
import com.signature.DataModel.Contact;
import com.signature.operation.SearchContact;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import com.signature.DataModel.ContactData;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.Comparator;
import java.util.Optional;

public class HomeController {

    @FXML
    private Pane rootNode;
    @FXML
    private StackPane viewPane;
    @FXML
    private TextField search;
    @FXML
    public ImageView searchButton;
    @FXML
    private JFXButton addContacts;
    @FXML
    private JFXButton filterContacts;
    @FXML
    private JFXButton settings;
    @FXML
    private Pane noContact;
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, Circle> profile;
    @FXML
    private TableColumn<Contact, String> name;
    private final FadeTransition fadeTransition = new FadeTransition(Duration.millis(100));
    protected static Contact contactToSelect = null;
    private SortedList<Contact> contacts = null;
    ObservableList<Contact> searchResult = null;


    public void initialize() {
        if (fadeTransition.getFromValue() == 0) {
            rootNode.setOpacity(0.0);
            makeFadeIn();
        }
        Platform.runLater(() -> rootNode.requestFocus());

        if (ContactData.getInstance().getContacts().size() > 0) {
            viewPane.getChildren().remove(noContact);
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editMenu = new MenuItem("Edit");
            MenuItem deleteMenu = new MenuItem("Delete");

            editMenu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleEditContact();
                }
            });

            deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    deleteItem();
                }
            });

            contextMenu.getItems().addAll(editMenu, deleteMenu);

            contacts = new SortedList<>(ContactData.getInstance().getContacts(), new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    if (o1.getFirstName().compareToIgnoreCase(o2.getFirstName()) == 0) {
                        return o1.getLastName().compareToIgnoreCase(o2.getLastName());
                    } else {
                        return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
                    }
                }
            });

            profile.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, Circle>, ObservableValue<Circle>>() {
                @Override
                public ObservableValue<Circle> call(TableColumn.CellDataFeatures<Contact, Circle> contact) {
                    Circle imageView = new Circle();
                    if (contact.getValue().getProfile().equals(" ")) {
                        imageView.setFill(new ImagePattern(new Image(getClass().getResource("/resources/ic_account_circle_black_48dp.png").toString())));
                    } else {
                        imageView.setFill(new ImagePattern(new Image(contact.getValue().getProfile())));
                    }

                    imageView.setRadius(18);
                    imageView.setSmooth(true);
                    return new SimpleObjectProperty<>(imageView);
                }
            });

            name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Contact, String> contact) {
                    return new SimpleStringProperty(contact.getValue().getFirstName() + " " + contact.getValue().getLastName());
                }
            });

            contactTable.setItems(contacts);

            contactTable.setRowFactory(new Callback<TableView<Contact>, TableRow<Contact>>() {
                @Override
                public TableRow<Contact> call(TableView<Contact> contactTableView) {
                    TableRow<Contact> row = new TableRow<>();
                    row.contextMenuProperty().setValue(contextMenu);
                    return row;
                }
            });


            if (contactToSelect != null) {
                contactTable.getSelectionModel().select(contactToSelect);
            }

            rootNode.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                        deleteItem();
                    } else if (keyEvent.getCode().equals(KeyCode.F2)) {
                        handleEditContact();
                    } else {
                        final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
                        if (keyCombination.match(keyEvent)) {
                            handleAddContact(null);
                        }
                    }
                }
            });
        }
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

    public void makeFadeIn() {
        fadeTransition.setNode(rootNode);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    public void handleAddContact(ActionEvent actionEvent) {
        makeFadeOut("AddEditContact.fxml", "Contact - New Contact");
    }

    private void handleEditContact() {
        Contact contactToEdit = contactTable.getSelectionModel().getSelectedItem();
        if (contactToEdit != null) {
            AddEditContactController.contactToEdit = contactTable.getSelectionModel().getSelectedItem();
            makeFadeOut("AddEditContact.fxml", "Contact - Edit Contact");
        }
    }

    private void deleteItem() {
        Contact contactToDelete = contactTable.getSelectionModel().getSelectedItem();

        if (contactToDelete != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Delete Contact");
            deleteAlert.setHeaderText("Contact : " + contactToDelete.getFirstName() + " " + contactToDelete.getLastName());
            deleteAlert.setContentText("Are you sure to delete the contact ?\nPress OK to Delete, Cancel to go back!");

            Optional<ButtonType> response = deleteAlert.showAndWait();
            if (response.isPresent() && response.get().equals(ButtonType.OK)) {
                ContactData.getInstance().deleteContact(contactToDelete);
            }
        }

        if (ContactData.getInstance().getContacts().size() == 0) {
            viewPane.getChildren().set(0, noContact);
        }
    }

    @FXML
    public void clearSearch() {
        if (!search.textProperty().isEmpty().get()) {
            search.clear();
            contactTable.setItems(contacts);
            searchResult = null;
            searchButton.setImage(new Image(getClass().getResource("/resources/ic_search_black_18dp.png").toString()));
        }
    }

    @FXML
    public void performLiveSearch(KeyEvent keyEvent) {
        if (search.textProperty().isEmpty().get()) {
            contactTable.setItems(contacts);
            searchResult = null;
            searchButton.setImage(new Image(getClass().getResource("/resources/ic_search_black_18dp.png").toString()));
        } else {
            searchResult = SearchContact.search(contacts, search.getText().trim());
            contactTable.setItems(searchResult);
            searchButton.setImage(new Image(getClass().getResource("/resources/ic_clear_black_18dp.png").toString()));
        }
    }

    @FXML
    public void handleShowContact() {
        ShowContactController.contactToView = contactTable.getSelectionModel().getSelectedItem();
        makeFadeOut("ShowContact.fxml", "Contact");
    }
}
