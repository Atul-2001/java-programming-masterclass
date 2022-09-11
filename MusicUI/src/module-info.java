module MusicUI {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires sqlite.jdbc;
    requires java.sql;

    opens sample;
    opens sample.model;
}