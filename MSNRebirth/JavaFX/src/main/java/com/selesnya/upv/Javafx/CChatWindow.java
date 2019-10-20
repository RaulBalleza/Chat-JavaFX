package com.selesnya.upv.Javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class CChatWindow extends Application {

    private static Scene scene;
    @FXML
    public TextArea chat;
    @FXML
    public TextField mensaje;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("chatWindow"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CChatWindow.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}