module Contact.Application {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.xml;
    requires controlsfx;

    opens com.signature;
    opens com.signature.DataModel;
}