import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage mainStage;
    private Group mainScene;
    public  String home ="Chat.fxml";
    public String selectorFoto = "SelectorDeImagenes.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        Group root= new Group();
        this.mainStage=stage;
        this.mainScene=root;
        this.cargarVentana(selectorFoto);
        System.out.println("Algo1");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public boolean cargarVentana(String archivoFXML) throws IOException {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(archivoFXML));
            System.out.println("Algo1");
            AnchorPane screen = (AnchorPane) myLoader.load();
            //VBox screen = (VBox) myLoader.load();
            if (mainScene.getChildren().isEmpty()) {
                mainScene.getChildren().add(screen);
            }
            else{
                mainScene.getChildren().remove(0);
                mainScene.getChildren().add(0,screen);
            }
            ScreenControlable myScreenControler = ((ScreenControlable) myLoader.getController());
            myScreenControler.setMainApp(this);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
