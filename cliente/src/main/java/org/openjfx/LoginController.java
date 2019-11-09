package org.openjfx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField hostnameTextfield;
    public TextField portTextfield;
    public TextField usernameTextfield;
    public static ChatController con;
    public Label selectedPicture;
    public ChoiceBox imagePicker;
    public ImageView Defaultview;
    public ImageView BadBunnyview;
    public ImageView Gokuview;

    private Scene scene;

    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    static LoginController getInstance() {
        return instance;
    }

    public void loginButtonAction(ActionEvent actionEvent) throws IOException {
        String hostname = hostnameTextfield.getText();
        int port = 9001;
        String username = usernameTextfield.getText();
        String picture = selectedPicture.getText();
        FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("views/ChatView.fxml"));
        Parent window = (Pane) fmxlLoader.load();
        con = fmxlLoader.getController();
        ClienteServidor listener = new ClienteServidor(hostname, port, username, picture, con);
        Thread x = new Thread(listener);
        x.start();
        this.scene = new Scene(window);
    }

    void showScene() throws IOException {
        Platform.runLater(() -> {
            Stage stage = (Stage) hostnameTextfield.getScene().getWindow();
            stage.setResizable(true);
            stage.setWidth(1040);
            stage.setHeight(620);

            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
            stage.setMinWidth(800);
            stage.setMinHeight(300);
            //ResizeHelper.addResizeListener(stage);
            stage.centerOnScreen();
            con.setUsernameLabel(usernameTextfield.getText());
            con.setImageLabel(selectedPicture.getText());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagePicker.getSelectionModel().selectFirst();
        selectedPicture.textProperty().bind(imagePicker.getSelectionModel().selectedItemProperty());
        selectedPicture.setVisible(false);
        imagePicker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldPicture, String newPicture) {
                if (oldPicture != null) {
                    switch (oldPicture) {
                        case "Default":
                            Defaultview.setVisible(false);
                            break;
                        case "Goku":
                            Gokuview.setVisible(false);
                            break;
                        case "BadBunny":
                            BadBunnyview.setVisible(false);
                            break;
                    }
                }
                if (newPicture != null) {
                    switch (newPicture) {
                        case "Default":
                            Defaultview.setVisible(true);
                            break;
                        case "Goku":
                            Gokuview.setVisible(true);
                            break;
                        case "BadBunny":
                            BadBunnyview.setVisible(true);
                            break;
                    }
                }
            }
        });
    }
}
