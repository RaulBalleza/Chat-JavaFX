package com.selesnya.upv.Javafx;

import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class CGetName extends Application {
    private static String textInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create a text input dialog
        TextInputDialog td = new TextInputDialog();
        // setHeaderText
        td.setHeaderText("Ingreese su nombre de usuario");
        Optional<String> result = td.showAndWait();
        result.ifPresent(nombre -> {
            textInput = nombre;
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
