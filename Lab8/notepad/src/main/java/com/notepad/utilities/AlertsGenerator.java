package com.notepad.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertsGenerator {

    public static Alert createConfirmationDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("Confirmation");
        alertDialog.setHeaderText("Do you want to save changes ?");

        ButtonType buttonTypeYes = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        return alertDialog;
    }

    public static Alert createInfoDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("About");
        alertDialog.setHeaderText("Note Justsu Developed my Mohamed Eldefrawy");
        return alertDialog;
    }

}
