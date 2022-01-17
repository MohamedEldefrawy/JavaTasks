package com.notepad.application;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // Fields
    public MenuItem btnNew;
    public TextArea txtNotePadArea;
    public Alert alertDialog;

    // Listeners
    public void btnNewClicked() {

        btnNew.setOnAction(event -> btnNewHandler());
    }

    // Utilities
    private Alert createConfirmationDialog() {
        alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("Confirmation");
        alertDialog.setHeaderText("Do you want to save changes ?");

        ButtonType buttonTypeYes = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        return alertDialog;
    }


    // Initialize Events
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnNew.setOnAction(event -> btnNewHandler());
    }

    // Handlers
    private void btnNewHandler() {
        alertDialog = createConfirmationDialog();

        if (!(txtNotePadArea.getText().isEmpty())) {

            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Save text file
                System.out.println("Ok selected");

            } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
                txtNotePadArea.clear();
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        } else
            this.txtNotePadArea.clear();
    }
}