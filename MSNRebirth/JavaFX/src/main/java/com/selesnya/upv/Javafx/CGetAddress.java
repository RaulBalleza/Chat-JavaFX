package com.selesnya.upv.Javafx;

import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CGetAddress extends Application {
    private static String textInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create a text input dialog
        TextInputDialog td = new TextInputDialog();
        // setHeaderText
        td.setHeaderText("Ingreese la direccion del servidor");
        Optional<String> result = td.showAndWait();
        result.ifPresent(direccion -> {
            textInput = direccion;
        });
    }

    public static void main(String args[]) {
        // launch the application
        launch(args);
    }

    public static String getTextInput() {
        return textInput;
    }
}
