package org.openjfx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.openjfx.messages.Message;
import org.openjfx.messages.MessageType;
import org.openjfx.messages.Status;
import org.openjfx.messages.User;
import org.openjfx.messages.bubble.BubbleSpec;
import org.openjfx.messages.bubble.BubbledLabel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    public TextArea messageBox;
    public ImageView userImageView;
    public Label usernameLabel;
    public ListView chatPane;
    public ListView userList;
    public Label onlineCountLabel;
    public ComboBox statusComboBox;

    public void sendButtonAction() throws IOException {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            ClienteServidor.send(msg);
            messageBox.clear();
        }
    }

    synchronized void addToChat(Message msg) {
        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                System.out.println("CREO QUE AQUI HAY PEDO");
                Image image = new Image(getClass().getResource("images/" + msg.getPicture().toLowerCase() + ".png").toExternalForm());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getType() == MessageType.FILE) {
                    ImageView imageview = new ImageView(new Image(getClass().getResource("images/goku.png").toExternalForm()));
                    bl6.setGraphic(imageview);
                    bl6.setText("Envio un archivo");
                    FileLoader.downloadFile(msg.getFile());
                } else {
                    bl6.setText(msg.getName() + ": " + msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(profileImage, bl6);
                //logger.debug("ONLINE USERS: " + Integer.toString(msg.getUserlist().size()));
                setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            chatPane.getItems().add(othersMessages.getValue());
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = userImageView.getImage();
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);

                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getType() == MessageType.FILE) {
                    ImageView imageview = new ImageView(new Image(getClass().getResource("images/goku.png").toExternalForm()));
                    bl6.setGraphic(imageview);
                    bl6.setText("Envio un archivo");
                    FileLoader.downloadFile(msg.getFile());
                } else {
                    bl6.setText(msg.getName() + ": " + msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(chatPane.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);

                setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> chatPane.getItems().add(yourMessages.getValue()));

        if (msg.getName().equals(usernameLabel.getText())) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }
    }

    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }

    public void setOnlineLabel(String usercount) {
        Platform.runLater(() -> onlineCountLabel.setText(usercount));
    }

    public void setUserList(Message msg) {
        //logger.info("setUserList() method Enter");
        Platform.runLater(() -> {
            ObservableList<User> users = FXCollections.observableList(msg.getUsers());
            userList.setItems(users);
            userList.setCellFactory(new CeldaUsuario());
            setOnlineLabel(String.valueOf(msg.getUserlist().size()));
        });
        //logger.info("setUserList() method Exit");
    }

    public void setImageLabel() throws IOException {
        this.userImageView.setImage(new Image(getClass().getResource("images/default.png").toExternalForm()));
    }

    public void setImageLabel(String selectedPicture) {
        switch (selectedPicture) {
            case "Goku":
                this.userImageView.setImage(new Image(getClass().getResource("images/goku.png").toExternalForm()));
                break;
            case "BB":
                this.userImageView.setImage(new Image(getClass().getResource("images/badbunny.png").toExternalForm()));
                break;
            case "Default":
                this.userImageView.setImage(new Image(getClass().getResource("images/default.png").toExternalForm()));
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setImageLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        statusComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    ClienteServidor.sendStatusUpdate(Status.valueOf(newValue.toUpperCase()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void openFileChooser(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        FileLoader.loadFile(file);
    }
}
