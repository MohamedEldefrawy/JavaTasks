package com.notepad.utilities;

import com.notepad.application.HelloApplication;
import javafx.stage.FileChooser;

import java.io.File;

public class FileDialogsGenerator {

    public static File createFileDialog(Dialogs dialogType) {
        FileChooser FileDialog = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        if (dialogType == Dialogs.SAVE) {

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file to save");
            return FileDialog.showSaveDialog(HelloApplication.getStage());

        } else {

            FileDialog.getExtensionFilters().add(extFilter);
            FileDialog.setTitle("Select text file to open");
            return FileDialog.showOpenDialog(HelloApplication.getStage());
        }

    }

}
