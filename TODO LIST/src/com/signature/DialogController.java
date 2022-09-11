package com.signature;

import com.signature.DataModel.TodoData;
import com.signature.DataModel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class DialogController {

    @FXML private TextField taskName;
    @FXML private TextArea taskDetail;
    @FXML private DatePicker taskDate;
    @FXML private HBox hBox;

    public void processResult() {
        String task = taskName.getText().trim();
        String description = taskDetail.getText().trim();
        LocalDate date = taskDate.getValue();

        TodoData.getInstance().addTodoItem(new TodoItem(task, description, date));
    }

    public void uploadData(TodoItem item) {
        taskName.setText(item.getName());
        taskDetail.setText(item.getDescription());
        Label prevDate = new Label("Previous Date : " + item.getDeadline().toString());
        hBox.getChildren().add(prevDate);
//        TodoData.getInstance().getTodoList().get(item)
    }

    public TodoItem updateData(TodoItem item) {
        String task = taskName.getText().trim();
        String description = taskDetail.getText().trim();
        LocalDate date = taskDate.getValue();
        item.setName(task);
        item.setDescription(description);
        item.setDeadline(date);
        return item;
    }
}
