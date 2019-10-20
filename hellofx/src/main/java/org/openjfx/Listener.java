package org.openjfx;

import java.io.*;
import java.net.Socket;

class Listener implements Runnable {
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    public ChatController controller;
    private static String picture;
    private static PrintWriter oos;
    private BufferedReader input;

    public Listener(String hostname, int port, String username, String picture, ChatController controller) {
        this.hostname = hostname;
        this.port = port;
        Listener.username = username;
        Listener.picture = picture;
        this.controller = controller;
    }

    public static void send(String msg) {
        oos.println(msg);
    }

    @Override
    public void run() {
        System.out.println("Listener.run()");
        try {
            socket = new Socket(hostname, port);
            LoginController.getInstance().showScene();
            input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            oos = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conexiones hechas");
        } catch (IOException e) {
            System.err.println("No se ah podido conectar al servidor");
            /*logger.error("Could not Connect");*/
        }
    }
}
