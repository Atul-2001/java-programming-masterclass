package com.signature;

import com.signature.DataModel.TodoData;
import com.signature.DataModel.TodoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    @FXML private ListView<TodoItem> todoListView;
    @FXML private TextArea todoDetailView;
    @FXML private Label deadLineLabel;
    @FXML private BorderPane mainWindow;
    @FXML private ContextMenu contextMenu;
    @FXML private ToggleButton filterButton;
    private FilteredList<TodoItem> filteredList;
    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodayItems;

    public void initialize() {
        contextMenu = new ContextMenu();
        MenuItem editMenu = new MenuItem("Edit");
        MenuItem deleteMenu = new MenuItem("Delete");

        editMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                editItem(item);
            }
        });

        deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        contextMenu.getItems().addAll(editMenu);
        contextMenu.getItems().addAll(deleteMenu);
        todoListView.getItems().addAll(TodoData.getInstance().getTodoList());

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem task = todoListView.getSelectionModel().getSelectedItem();
                    todoDetailView.setText(task.getDescription());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadLineLabel.setText(df.format(task.getDeadline()));
                }
            }
        });

        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        wantTodayItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return item.getDeadline().equals(LocalDate.now());
            }
        };

        filteredList = new FilteredList<>(TodoData.getInstance().getTodoList(), wantAllItems);

        SortedList sortedList = new SortedList(filteredList,
                new Comparator<TodoItem>() {
                    @Override
                    public int compare(TodoItem o1, TodoItem o2) {
                        return (o1.getDeadline().compareTo(o2.getDeadline()));
                    }
                });

//        todoListView.setItems(TodoData.getInstance().getTodoList());
        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoListView) {
                ListCell<TodoItem> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(TodoItem todo, boolean b) {
                        super.updateItem(todo, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(todo.getName());
                            if (todo.getDeadline().isBefore(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.RED);
                            } else if (todo.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.DARKGREEN);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(contextMenu);
                            }
                        }
                );

                return cell;
            }
        });
    }

//    public void handleClickListView() {
//        TodoItem task = todoListView.getSelectionModel().getSelectedItem();
//        todoDetailView.setText(task.getDescription());
//        deadLineLabel.setText(task.getDeadline().toString());
//    }

    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            dialog.setTitle("Add New Todo Item");
        } catch (IOException e){
            System.out.println("Can't create Dialog!");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processResult();
//            todoListView.getItems().setAll(TodoData.getInstance().getTodoList());
            todoListView.getSelectionModel().selectLast();
        }
    }

    public void handleExit() {
        Platform.exit();
//        System.exit(0);
    }

    public void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setWidth(410.0);
//        alert.setHeight(200.0);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete Item : " + item.getName());
        alert.setContentText("Are you sure ? Press OK to confirm, or Cancel to back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            TodoData.getInstance().deleteTodoItem(item);
        }
    }

    public void editItem(TodoItem item) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            dialog.setTitle("Edit Todo Item");
        } catch (IOException e){
            System.out.println("Can't create Dialog!");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController controller = fxmlLoader.getController();
        controller.uploadData(item);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.APPLY) {
            TodoItem res = controller.updateData(item);
            todoListView.getSelectionModel().selectFirst();
//            todoListView.getSelectionModel().select(res);
        }
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();

        if ((selectedItem != null) && (keyEvent.getCode().equals(KeyCode.DELETE))) {
            deleteItem(selectedItem);
        }
    }

    public void handleEditItem(ActionEvent event) {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        editItem(item);
    }


    public void handleDeleteItem(ActionEvent event) {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        deleteItem(item);
    }

    public void handleFilter(ActionEvent event) {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        if (filterButton.isSelected()) {
            filteredList.setPredicate(wantTodayItems);
            if (filteredList.isEmpty()) {
                deadLineLabel.setText("");
                todoDetailView.setText("");
            } else if (filteredList.contains(item)) {
                todoListView.getSelectionModel().select(item);
            } else {
                todoListView.getSelectionModel().selectFirst();
            }
        } else {
            filteredList.setPredicate(wantAllItems);
            if (item!=null) {
                todoListView.getSelectionModel().select(item);
            } else {
                todoListView.getSelectionModel().selectFirst();
            }
        }
    }
}
