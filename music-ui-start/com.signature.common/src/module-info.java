module com.signature.common {
    requires javafx.base;

    exports com.signature.common;
    opens com.signature.common to javafx.base;
}