import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectorDeImagenes implements Initializable,ScreenControlable {
    private Main mainApp;

    @FXML
    private ImageView[] avatars = new ImageView[8];


    @FXML
    private void Avatar1(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }

    @FXML
    private void Avatar2(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar3(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar4(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar5(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar6(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar7(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @FXML
    private void Avatar8(ActionEvent Evento) throws IOException {
        mainApp.cargarVentana(mainApp.home);
    }
    @Override
    public void setMainApp(Main mainApp) {
        this.mainApp=mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
