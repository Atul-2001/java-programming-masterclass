module Contact {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires com.jfoenix;
    requires java.xml;

    opens com.signature.DataModel;
    opens com.signature.UI;
    opens com.signature.test;
}