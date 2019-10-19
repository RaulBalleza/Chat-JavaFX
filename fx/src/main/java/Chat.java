import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class Chat  implements Initializable,ScreenControlable  {
    private Main mainApp;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainApp(Main mainApp){
        this.mainApp=mainApp;
    }

}
