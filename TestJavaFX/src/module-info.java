module TestJavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires java.base;

    opens scene;
}