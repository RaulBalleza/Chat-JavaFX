package org.openjfx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatController {
    public TextArea messageBox;
    public ImageView userImageView;
    public Label usernameLabel;

    public void sendButtonAction() throws IOException {
        String msg = messageBox.getText();
        System.out.println(msg +"PEDOS");
        if (!messageBox.getText().isEmpty()) {
            Listener.send(msg);
            messageBox.clear();
        }
    }

    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    public void closeApplication(MouseEvent mouseEvent) {
    }

    public void recordVoiceMessage(MouseEvent mouseEvent) {
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }

    public void setImageLabel() throws IOException {
        this.userImageView.setImage(new Image(getClass().getClassLoader().getResource("images/dominic.png").toString()));
    }

    public void setImageLabel(String selectedPicture) {
        switch (selectedPicture) {
            case "Goku":
                this.userImageView.setImage(new Image(getClass().getClassLoader().getResource("images/goku.png").toString()));
                break;
            case "BB":
                this.userImageView.setImage(new Image(getClass().getClassLoader().getResource("images/sarah.png").toString()));
                break;
            case "Default":
                this.userImageView.setImage(new Image(getClass().getClassLoader().getResource("images/default.png").toString()));
                break;
        }
    }
}
