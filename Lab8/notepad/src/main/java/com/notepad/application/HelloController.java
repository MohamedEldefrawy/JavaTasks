package com.notepad.application;

import com.notepad.utilities.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // Fields
    public MenuItem btnNew;
    public TextArea txtNotePadArea;
    public MenuItem btnOpen;
    public MenuItem btnNewWindow;
    public MenuItem btnSave;
    public MenuItem btnSaveAs;
    public MenuItem btnPageSetup;
    public MenuItem btnPrint;
    public MenuItem btnExit;
    public MenuItem btnUndo;
    public MenuItem btnCut;
    public MenuItem btnPast;
    public MenuItem btnCopy;
    public MenuItem btnDelete;
    public MenuItem btnSelectAll;
    public MenuItem btnGetTimeDate;
    public MenuItem btnAbout;
    private Stage stage;
    private File currentFile;
    private String selectedText;


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
        btnUndo.setOnAction(actionEvent -> btnUndoClicked());
        btnCut.setOnAction(actionEvent -> btnCutClicked());
        btnPast.setOnAction(actionEvent -> btnPastClicked());
        btnCopy.setOnAction(actionEvent -> btnCopyClicked());
        btnDelete.setOnAction(actionEvent -> btnDeleteClicked());
        btnSelectAll.setOnAction(actionEvent -> btnSelectAllClicked());
        btnGetTimeDate.setOnAction(actionEvent -> btnGetTimeDateClicked());
        btnAbout.setOnAction(actionEvent -> btnAboutClicked());
        HelloApplication.getStage().setOnCloseRequest(windowEvent -> closeSceneClicked());


        btnNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        btnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        btnSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        btnPrint.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
        btnNewWindow.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        btnSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        btnGetTimeDate.setAccelerator(new KeyCodeCombination(KeyCode.F5));

        btnCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        btnPast.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        btnCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        btnSelectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        btnDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));


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
        ExitApplication();

    }
    public void btnUndoClicked() {
        txtNotePadArea.undo();
    }
    public void btnCutClicked() {
        selectedText = txtNotePadArea.getSelectedText();
        txtNotePadArea.replaceSelection("");
    }
    public void btnPastClicked() {
        txtNotePadArea.appendText(selectedText);
    }
    public void btnCopyClicked() {
        selectedText = txtNotePadArea.getSelectedText();
    }
    public void btnDeleteClicked() {
        if (!txtNotePadArea.getSelectedText().equals("")) {
            txtNotePadArea.replaceSelection("");
        } else {
            txtNotePadArea.deleteNextChar();
        }
    }

    public void btnSelectAllClicked() {
        txtNotePadArea.selectAll();
    }

    public void btnGetTimeDateClicked() {
        txtNotePadArea.appendText(LocalDateTime.now().toString());
    }

    public void btnAboutClicked() {
        AlertsGenerator.createInfoDialog().show();
    }

    public void closeSceneClicked() {
        ExitApplication();
    }

    // Helpers
    private void readFromTxtFile() {
        File file = FileDialogsGenerator.createFileDialog(Dialogs.OPEN);
        if (file != null) {
            TextFileReader reader = new TextFileReader(file);
            txtNotePadArea.setText(reader.fileContent());
        }
    }

    private void ExitApplication() {
        Alert alertDialog;

        if (txtNotePadArea.getText().isEmpty()) {
            Platform.exit();
        } else {
            alertDialog = AlertsGenerator.createConfirmationDialog();
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

    private void saveAsFile() {
        currentFile = FileDialogsGenerator.createFileDialog(Dialogs.SAVE);
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
        Alert alertDialog;
        alertDialog = AlertsGenerator.createConfirmationDialog();

        if (!(txtNotePadArea.getText().isEmpty())) {

            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                // Save text file
                TextFileWriter writer = new TextFileWriter(FileDialogsGenerator.createFileDialog(Dialogs.SAVE));
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
        Alert alertDialog;
        alertDialog = AlertsGenerator.createConfirmationDialog();

        if (!(txtNotePadArea.getText().isEmpty())) {
            Optional<ButtonType> result = alertDialog.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Save text file
                TextFileWriter writer = new TextFileWriter(FileDialogsGenerator.createFileDialog(Dialogs.SAVE));
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