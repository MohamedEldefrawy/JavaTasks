package com.notepad.application;

import com.notepad.utilities.Dialogs;
import com.notepad.utilities.TextFileReader;
import com.notepad.utilities.TextFileWriter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
    public MenuItem btnSaveAs;
    public MenuItem btnPageSetup;
    public MenuItem btnPrint;
    public MenuItem btnExit;
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
        btnSaveAs.setOnAction(event -> btnSaveAsClicked());
        btnPageSetup.setOnAction(event -> btnPageSetupClicked());
        btnPrint.setOnAction(actionEvent -> btnPrintClicked());
        btnExit.setOnAction(actionEvent -> btnExitClicked());
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
            saveAsFile();

        } else {
            TextFileWriter writer = new TextFileWriter(currentFile);
            writer.setContent(txtNotePadArea.getText());
            writer.saveFile();
        }

    }

    public void btnSaveAsClicked() {
        saveAsFile();
    }

    public void btnPageSetupClicked() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            return;
        }

        // Show the page setup dialog
        boolean proceed = job.showPageSetupDialog(stage);

        if (proceed) {
            print(job, txtNotePadArea);
        }
    }

    public void btnPrintClicked() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(HelloApplication.getStage().getScene().getWindow())) {
            boolean success = job.printPage(txtNotePadArea);
            if (success) {
                job.endJob();
            }
        }
    }

    public void btnExitClicked() {
        if (txtNotePadArea.getText().isEmpty()) {
            Platform.exit();
        } else {
            Alert alert = createConfirmationDialog();
            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                saveAsFile();
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
                Platform.exit();
            } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                alertDialog.close();
            }
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

    private void saveAsFile() {
        currentFile = createFileDialog(Dialogs.SAVE);
        if (currentFile != null) {
            TextFileWriter writer = new TextFileWriter(currentFile);
            writer.setContent(txtNotePadArea.getText());
            writer.saveFile();
            HelloApplication.getStage().setTitle(currentFile.getName().substring(0, currentFile.getName().length() - 4));
        }
    }

    private void print(PrinterJob job, Node node) {
        // Print the node
        boolean printed = job.printPage(node);

        if (printed) {
            job.endJob();
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