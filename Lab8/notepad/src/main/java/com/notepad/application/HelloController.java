package com.notepad.application;

import com.notepad.utilities.Dialogs;
import com.notepad.utilities.TextFileReader;
import com.notepad.utilities.TextFileWriter;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // Fields
    public MenuItem btnNew;
    public TextArea txtNotePadArea;
    public Alert alertDialog;
    public FileChooser openFileDialog;
    public FileChooser saveFileDialog;
    public MenuItem btnOpen;
    private Stage stage;

    // Listeners
    public void btnNewClicked() {

        btnNew.setOnAction(event -> btnNewHandler());
    }

    public void btnOpenClicked() {
        btnOpen.setOnAction(event -> btnOpenHandler());
    }


    // Utilities

    private FileChooser createFileDialog(String dialogType) {
        FileChooser FileDialog = new FileChooser();

        if (dialogType.toUpperCase().equals(Dialogs.SAVE)) {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file");

        } else {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file");
        }

        return FileDialog;

    }


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
        btnOpen.setOnAction(event -> btnOpenHandler());
    }

    // Handlers
    private void btnNewHandler() {

        alertDialog = createConfirmationDialog();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

        if (!(txtNotePadArea.getText().isEmpty())) {

            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                // Save text file
                saveFileDialog = createFileDialog(Dialogs.SAVE.toString());

                TextFileWriter writer = new TextFileWriter(saveFileDialog.showSaveDialog(stage));
                writer.setContent(txtNotePadArea.getText());
                writer.saveFile();
                txtNotePadArea.clear();

                System.out.println("Ok selected");

            } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
                txtNotePadArea.clear();
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        } else
            this.txtNotePadArea.clear();
    }


    private void btnOpenHandler() {

        alertDialog = createConfirmationDialog();

        if (!(txtNotePadArea.getText().isEmpty())) {
            Optional<ButtonType> result = alertDialog.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                // Save text file
                saveFileDialog = createFileDialog(Dialogs.SAVE.toString());
                openFileDialog = createFileDialog(Dialogs.OPEN.toString());

                TextFileWriter writer = new TextFileWriter(saveFileDialog.showSaveDialog(stage));
                writer.setContent(txtNotePadArea.getText());
                writer.saveFile();

            } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
                TextFileReader reader = new TextFileReader(openFileDialog.showOpenDialog(stage));
                txtNotePadArea.setText(reader.fileContent());
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        } else {
            openFileDialog = createFileDialog(Dialogs.OPEN.toString());
            TextFileReader reader = new TextFileReader(openFileDialog.showOpenDialog(stage));
            txtNotePadArea.setText(reader.fileContent());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}