module com.notepad.notepad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.notepad.application to javafx.fxml;
    exports com.notepad.application;
}