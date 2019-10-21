package org.openjfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
/*
    public TextArea chat;

    public TextField mensaje;

    public void initialize() throws IOException {
        chat.setEditable(true);
        run();
    }

    private String direccionServidor() {
        return "localhost";
    }

    public String nombre() {
        return "raul";
    }

    private void run() throws IOException {
        System.out.println("Empezo el run");
        String ds = direccionServidor();
        Socket sk = new Socket(ds, 9001);
        MainApp.in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        MainApp.out = new PrintWriter(sk.getOutputStream(), true);
    }

    public void Send(ActionEvent actionEvent) {
        MainApp.out.println(mensaje.getText());
        mensaje.setText("");
        System.out.println(mensaje);
        //chat.setText("NO MAMES PA XD");
    }*/
}
