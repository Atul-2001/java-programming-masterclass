package com.signature.test;

import com.signature.DataModel.Contact;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private BorderPane root;
    @FXML
    private TableView<Contact> dataTable;
    @FXML
    private TableColumn<Contact, ?> emailColumn;
    @FXML
    private TableColumn<Contact, ?> phoneColumn;
    @FXML
    private VBox mainPanel;
    @FXML
    private VBox messageNode;
    private boolean fullScreen = false;

    public void initialize() {
        Platform.runLater(() -> root.getScene().getWindow().widthProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue.doubleValue() <= 600.0 && root.getCenter() == mainPanel) {
                        if (oldValue.doubleValue() > newValue.doubleValue()) {
                            if (dataTable.getColumns().size() == 2) {
                                dataTable.getColumns().remove(phoneColumn);
                            }
                            if (fullScreen && root.getRight() == messageNode) {
                                root.setRight(null);
                                dataTable.getColumns().removeAll(emailColumn, phoneColumn);
                            } else if (fullScreen){
                                Node node = root.getRight();
                                root.setRight(null);
                                root.setCenter(node);
                                ((Stage) root.getScene().getWindow()).setMinWidth(500.0);
                            }
                        }
                        fullScreen = false;
                    } else if (newValue.doubleValue() <= 820.0 && root.getCenter() == mainPanel) {
                        if (oldValue.doubleValue() > newValue.doubleValue()) {
                            if (dataTable.getColumns().size() == 3) {
                                dataTable.getColumns().remove(emailColumn);
                            }
                            if (fullScreen && root.getRight() == messageNode) {
                                root.setRight(null);
                            } else if(fullScreen) {
                                Node node = root.getRight();
                                root.setRight(null);
                                root.setCenter(node);
                                ((Stage) root.getScene().getWindow()).setMinWidth(500.0);
                            }
                        } else if (oldValue.doubleValue() < newValue.doubleValue() && dataTable.getColumns().size() == 1) {
                            dataTable.getColumns().add(phoneColumn);
                        }
                        fullScreen = false;
                    } else if (newValue.doubleValue() <= 1000.0) {
                        if (oldValue.doubleValue() > newValue.doubleValue()) {
                            if (dataTable.getColumns().size() == 1 || fullScreen) {
                                if (root.getRight() != null && root.getRight().getId().equalsIgnoreCase("messageNode")) {
                                    root.setRight(null);
                                    if (!fullScreen) {
                                        dataTable.getColumns().addAll(emailColumn, phoneColumn);
                                    }
                                } else if (root.getRight() != null) {
                                    Node node = root.getRight();
                                    root.setRight(null);
                                    root.setCenter(node);
                                    ((Stage) root.getScene().getWindow()).setMinWidth(500.0);
                                }
                            }
                        } else if (oldValue.doubleValue() < newValue.doubleValue() && dataTable.getColumns().size() == 2) {
                            dataTable.getColumns().add(1, emailColumn);
                        }
                        fullScreen = false;
                    } else if (newValue.doubleValue() <= 1100.0) {
                        if (oldValue.doubleValue() > newValue.doubleValue()) {
                            if (dataTable.getColumns().size() == 2) {
                                dataTable.getColumns().remove(phoneColumn);
                            }
                            if (fullScreen) {
                                dataTable.getColumns().removeAll(emailColumn, phoneColumn);
                            }
                        } else if (oldValue.doubleValue() < newValue.doubleValue() && dataTable.getColumns().size() == 3 && root.getCenter() == mainPanel) {
                            root.setRight(messageNode);
                            dataTable.getColumns().removeAll(emailColumn, phoneColumn);
                        } else if (oldValue.doubleValue() < newValue.doubleValue() && (dataTable.getColumns().size() == 1 || dataTable.getColumns().size() == 3) && root.getCenter() != mainPanel) {
                            Node node = root.getCenter();
                            root.setCenter(null);
                            root.setRight(node);
                            root.setCenter(mainPanel);
                            ((Stage) root.getScene().getWindow()).setMinWidth(460.0);
                            if (dataTable.getColumns().size() == 3) {
                                dataTable.getColumns().removeAll(emailColumn, phoneColumn);
                            }
                        }
                        fullScreen = false;
                    } else if (newValue.doubleValue() <= 1200.0) {
                        if (fullScreen || ((oldValue.doubleValue() > newValue.doubleValue()) && (dataTable.getColumns().size() == 3))) {
                            dataTable.getColumns().remove(emailColumn);
                        } else if ((oldValue.doubleValue() < newValue.doubleValue()) && (dataTable.getColumns().size() == 1)) {
                            dataTable.getColumns().add(phoneColumn);
                        }
                        fullScreen = false;
                    } else if ((newValue.doubleValue() > 1200.0) && oldValue.doubleValue() < newValue.doubleValue()) {
                        fullScreen = true;
                        if (root.getCenter() != mainPanel) {
                            Node node = root.getCenter();
                            root.setCenter(null);
                            root.setRight(node);
                            root.setCenter(mainPanel);
                        } else if (root.getRight() == null) {
                            root.setRight(messageNode);
                        }

                        if (dataTable.getColumns().size() == 1) {
                            dataTable.getColumns().addAll(emailColumn, phoneColumn);
                        } else if (dataTable.getColumns().size() == 2) {
                            dataTable.getColumns().add(1, emailColumn);
                        }
                    }
                })
        );
    }

    public void showAddContact(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("add.fxml"));
        try {
            if (root.getScene().getWindow().getWidth() <= 1000.0) {
                root.setCenter(loader.load());
                ((Stage) root.getScene().getWindow()).setMinWidth(500.0);
            } else {
                root.setRight(loader.load());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}