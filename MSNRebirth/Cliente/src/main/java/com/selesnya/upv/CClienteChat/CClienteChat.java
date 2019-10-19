/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selesnya.upv.CClienteChat;

import com.selesnya.upv.Javafx.CChatWindow;
import com.selesnya.upv.Javafx.CGetAddress;
import com.selesnya.upv.Javafx.CGetName;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author telecom
 */
public class CClienteChat {

    private BufferedReader in;
    private PrintWriter out;
    CChatWindow chat = new CChatWindow();

    private CClienteChat() {
        chat.main(null);
        System.out.println(chat.getMsj());
        out.println(chat.getMsj());
        out.println("d");
    }

    private String direccionServidor() throws Exception {
        return chat.getTextInputAdress();
    }

    private void run() throws Exception {

        String ds = direccionServidor();
        Socket sk = new Socket(ds, 9001);
        in = new BufferedReader(new InputStreamReader(
                sk.getInputStream()));
        out = new PrintWriter(sk.getOutputStream(), true);
        while (true) {
            String txt = in.readLine();
            if (txt.startsWith("NOMBREE")) {
                out.println("RAUL");
            } else if (txt.startsWith("NOMBREA")) {
                chat.mensaje.setEditable(true);
            } else if (txt.startsWith("Mensaje")) {
                chat.chatArea.setText("HOLAAAAAAAAAAAAAAAa");
            }
            System.out.println(txt);
        }
    }

    private String nombre() throws Exception {
        return chat.getTextInputName();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CClienteChat cliente = new CClienteChat();
        cliente.run();
    }

}
