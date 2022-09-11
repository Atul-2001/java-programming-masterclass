package com.signature.DataModel;

import java.time.LocalDate;

public class TodoItem {
    private String name;
    private String description;
    private LocalDate deadline;

    public TodoItem(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.deadline = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

//    @Override
//    public String toString() {
//        return this.name;
//    }
}
