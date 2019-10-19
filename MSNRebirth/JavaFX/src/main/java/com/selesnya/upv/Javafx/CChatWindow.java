package com.selesnya.upv.Javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App
 */
public class CChatWindow extends Application {

    private static Scene scene;
    @FXML
    public TextArea chatArea;

    public static Stage stage;
    @FXML
    public TextField mensaje;

    public String getMsj() {
        return msj;
    }

    private static String msj = "";

    public static String getTextInputAdress() {
        return textInputAdress;
    }

    public static String getTextInputName() {
        return textInputName;
    }

    public static String textInputAdress;
    public static String textInputName;

    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("chatWindow"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        CGetAddress ga = new CGetAddress();
        CGetName gn = new CGetName();
        stage.show();
    }

    @FXML
    public void onEnter(ActionEvent ae) {
        if (!mensaje.getText().equals("")) {
            msj = mensaje.getText();
        }
        mensaje.setText("");
        System.out.println("Jolaaaaaaaaaa");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CChatWindow.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class CGetAddress extends CChatWindow {

        public CGetAddress() {
            // create a text input dialog
            TextInputDialog td = new TextInputDialog();
            // setHeaderText
            td.setHeaderText("Ingreese la direccion del servidor");
            Optional<String> result = td.showAndWait();
            result.ifPresent(direccion -> {
                textInputAdress = direccion;
            });
        }
    }

    public class CGetName extends CChatWindow {
        public CGetName() {
            // create a text input dialog
            TextInputDialog td = new TextInputDialog();
            // setHeaderText
            td.setHeaderText("Ingreese su nombre de usuario");
            Optional<String> result = td.showAndWait();
            result.ifPresent(nombre -> {
                textInputName = nombre;
            });
        }

    }
}

