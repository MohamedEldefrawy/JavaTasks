package com.notepad.application;

import com.notepad.utilities.Dialogs;
import com.notepad.utilities.TextFileReader;
import com.notepad.utilities.TextFileWriter;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // Fields
    public MenuItem btnNew;
    public TextArea txtNotePadArea;
    public Alert alertDialog;
    public MenuItem btnOpen;
    public MenuItem btnNewWindow;
    public MenuItem btnSave;
    private Stage stage;
    private File currentFile;


    // Setters
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    // Initialize Events
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnNew.setOnAction(event -> btnNewHandler());
        btnOpen.setOnAction(event -> btnOpenHandler());
        btnNewWindow.setOnAction(event -> btnNewWindowClicked());
        btnSave.setOnAction(event -> btnSaveClicked());
    }


    // Listeners
    public void btnNewClicked() {
        btnNew.setOnAction(event -> btnNewHandler());
    }

    public void btnOpenClicked() {
        btnOpen.setOnAction(event -> btnOpenHandler());
    }

    public void btnNewWindowClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("New note");
            stage.setScene(scene);
            HelloController controller = new HelloController();
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveClicked() {

        if (currentFile == null || currentFile.getName().isEmpty()) {
            currentFile = createFileDialog(Dialogs.SAVE);
            if (currentFile != null) {
                TextFileWriter writer = new TextFileWriter(currentFile);
                writer.setContent(txtNotePadArea.getText());
                writer.saveFile();
                HelloApplication.getStage().setTitle(currentFile.getName().substring(0, currentFile.getName().length() - 4));
            }

        } else {
            TextFileWriter writer = new TextFileWriter(currentFile);
            writer.setContent(txtNotePadArea.getText());
            writer.saveFile();
        }

    }


    // Helpers
    private File createFileDialog(Dialogs dialogType) {
        FileChooser FileDialog = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        if (dialogType == Dialogs.SAVE) {

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file to save");
            return FileDialog.showSaveDialog(stage);

        } else {

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file to open");
            return FileDialog.showOpenDialog(stage);
        }

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
    private void readFromTxtFile() {
        File file = createFileDialog(Dialogs.OPEN);
        if (file != null) {
            TextFileReader reader = new TextFileReader(file);
            txtNotePadArea.setText(reader.fileContent());
        }
    }

    // Event Handlers
    private void btnNewHandler() {

        alertDialog = createConfirmationDialog();

        if (!(txtNotePadArea.getText().isEmpty())) {

            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                // Save text file
                TextFileWriter writer = new TextFileWriter(createFileDialog(Dialogs.SAVE));
                writer.setContent(txtNotePadArea.getText());
                writer.saveFile();
                txtNotePadArea.clear();
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
                TextFileWriter writer = new TextFileWriter(createFileDialog(Dialogs.SAVE));
                writer.setContent(txtNotePadArea.getText());
                writer.saveFile();

                readFromTxtFile();

            } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
                readFromTxtFile();
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
        } else {
            readFromTxtFile();
        }
    }

}