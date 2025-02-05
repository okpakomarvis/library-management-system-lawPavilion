module com.marvisx.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens com.marvisx.frontend to javafx.fxml;
    exports com.marvisx.frontend;
}