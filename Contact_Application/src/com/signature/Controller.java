package com.signature;

import com.signature.DataModel.Contact;
import com.signature.DataModel.ContactData;
import javafx.application.Platform;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class Controller {

    @FXML private VBox mainWindow;
    @FXML private TableView<Contact> dataTable;
    @FXML private ContextMenu contextMenu;
    protected static Contact selectContact = null;

    public void initialize() {
        contextMenu = new ContextMenu();
        MenuItem editMenu = new MenuItem("Edit");
        MenuItem deleteMenu = new MenuItem("Delete");

        editMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleEditContact(null);
            }
        });

        deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleDeleteContact(null);
            }
        });

        SortedList<Contact> contactSortedList = new SortedList<>(ContactData.getInstance().getContacts(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                if (o1.getFirstName().compareTo(o2.getFirstName()) == 0) {
                    return o1.getLastName().compareTo(o2.getLastName());
                } else {
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
            }
        });
        dataTable.setItems(contactSortedList);
        if (selectContact != null) {
            dataTable.getSelectionModel().select(selectContact);
        }

        contextMenu.getItems().add(editMenu);
        contextMenu.getItems().add(deleteMenu);

        dataTable.setRowFactory(new Callback<TableView<Contact>, TableRow<Contact>>() {
            @Override
            public TableRow<Contact> call(TableView<Contact> contactTableView) {
                TableRow<Contact> row = new TableRow<>() {
                    @Override
                    protected void updateItem(Contact contact, boolean b) {
                        super.updateItem(contact, b);
                        if (b) {
                            setItem(null);
                        } else {
                            setItem(contact);
                        }
                    }
                };

                row.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                row.setContextMenu(null);
                            } else {
                                row.setContextMenu(contextMenu);
                            }
                        }
                );
                return row;
            }
        });
    }

    public void handleAddContact(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactFormDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            dialog.setTitle("Add New Contact");
        } catch (IOException e){
            System.out.println("Can't create Dialog!");
            System.out.println(e.getMessage() + " " + e.getCause());
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactFormController controller = fxmlLoader.getController();
        controller.initialize(dialog);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            Contact contact = controller.processData();

            if (contact != null) {
                dataTable.getSelectionModel().select(contact);
            } else {
                dataTable.getSelectionModel().select(0);
            }
        }
    }

    public void handleEditContact(ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactFormDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            dialog.setTitle("Edit Contact");
        } catch (IOException e){
            System.out.println("Can't create Dialog!");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactFormController controller = fxmlLoader.getController();
        Contact contact = dataTable.getSelectionModel().getSelectedItem();
        if (contact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item not selected");
            alert.setContentText("Please select a contact to edit");
            alert.showAndWait();
        } else {
            controller.loadContact(contact);

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get().equals(ButtonType.OK)) {
                controller.updateContact(contact);
                dataTable.getSelectionModel().select(contact);
                dataTable.refresh();
            }
        }
    }

    public void handleDeleteContact(ActionEvent actionEvent) {
        Contact contact = dataTable.getSelectionModel().getSelectedItem();

        if (contact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("item not selected");
            alert.setContentText("Please select a contact to Delete");
            alert.showAndWait();
        } else {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Delete Contact");
           alert.setHeaderText("Contact : " + contact.getFirstName() + " " + contact.getLastName());
           alert.setContentText("Are you sure to delete the contact ?\n Press OK to Delete, Cancel to go back!");

           Optional<ButtonType> result = alert.showAndWait();
           if (result.isPresent() && result.get().equals(ButtonType.OK)) {
               ContactData.getInstance().deleteContact(contact);
           }
        }
    }

    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        Contact contact = dataTable.getSelectionModel().getSelectedItem();

        if ((contact != null) && (keyEvent.getCode().equals(KeyCode.DELETE))) {
            handleDeleteContact(null);
        } else if ((contact != null) && (keyEvent.getCode().equals(KeyCode.F2))) {
            handleEditContact(null);
        } else {
            final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
            if (keyCombination.match(keyEvent)) {
                handleAddContact(null);
            }
        }
    }

    public void handleShowDetail(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            Contact contact = dataTable.getSelectionModel().getSelectedItem();

            if (contact != null) {
                ContactDetailController.contact = contact;
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("contactDetail.fxml"));
                    Main.scene.setRoot(root);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
