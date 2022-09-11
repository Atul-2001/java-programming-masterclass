module com.signature.ui {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.signature.db;

    exports com.signature.ui to javafx.fxml, javafx.graphics;
    opens com.signature.ui to javafx.fxml;
}