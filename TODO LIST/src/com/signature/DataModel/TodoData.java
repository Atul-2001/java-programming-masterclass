package com.signature.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TodoData {
    private static final TodoData instance = new TodoData();
    private static final String filename = "TodoListItem.txt";

    private ObservableList<TodoItem> todoList;
    private final DateTimeFormatter formatter;

    public TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static TodoData getInstance() {
        return instance;
    }

    public ObservableList<TodoItem> getTodoList() {
        return todoList;
    }

//    public void setTodoList(List<TODO> todoList) {
//        this.todoList = todoList;
//    }

    public void addTodoItem(TodoItem item) {
        this.todoList.add(item);
    }

    public void loadTodoItems() throws IOException {
        todoList = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String inputs;

        try {
            while ((inputs = br.readLine()) != null) {
                String[] item = inputs.split("\t");

                String task = item[0];
                String taskDetail = item[1];
                String dueDate = item[2];

                LocalDate date = LocalDate.parse(dueDate, formatter);

                todoList.add(new TodoItem(task, taskDetail, date));
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            Iterator<TodoItem> i = todoList.iterator();
            while (i.hasNext()) {
                TodoItem item = i.next();
                bw.write(String.format("%s\t%s\t%s",
                                        item.getName(),
                                        item.getDescription(),
                                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void deleteTodoItem(TodoItem item) {
        todoList.remove(item);
    }
}
