package org.openjfx;

import org.openjfx.messages.Message;
import org.openjfx.messages.MessageType;
import org.openjfx.messages.Status;

import java.io.*;
import java.net.Socket;

import static org.openjfx.messages.MessageType.CONNECTED;

class ClienteServidor implements Runnable {
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    public ChatController controller;
    private static String picture;
    private static ObjectOutputStream oos; //Para enviar mensajes
    private InputStream is;
    private ObjectInputStream input; //Para recibir mensajes
    private OutputStream outputStream;
    private static final String HASCONNECTED = "has connected";

    public ClienteServidor(String hostname, int port, String username, String picture, ChatController controller) {
        this.hostname = hostname;
        this.port = port;
        ClienteServidor.username = username;
        ClienteServidor.picture = picture;
        this.controller = controller;
    }

    public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setStatus(Status.AWAY);
        createMessage.setMsg(msg);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

    @Override
    public void run() {
        System.out.println("Listener.run()");
        try {
            socket = new Socket(hostname, port);
            LoginController.getInstance().showScene();
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
            System.out.println("Conexiones hechas");
        } catch (IOException e) {
            System.err.println("No se ah podido conectar al servidor");
        }

        try {
            connect();
            while (socket.isConnected()) {
                Message message = null;
                message = (Message) input.readObject();

                if (message != null) {
                    switch (message.getType()) {
                        case USER:
                            controller.addToChat(message);
                            break;
                        case FILE:
                            controller.addToChat(message);
                            break;
                        case IMAGE:
                            controller.addToChat(message);
                            break;
                        case CONNECTED:
                            controller.setUserList(message);
                            break;
                        case DISCONNECTED:
                            controller.setUserList(message);
                            break;
                        case STATUS:
                            controller.setUserList(message);
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //controller.logoutScene();
        }
    }

    public static void connect() throws IOException {
        System.out.println("Primer mensaje");
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(CONNECTED);
        createMessage.setMsg(HASCONNECTED);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
    }

    public static void sendStatusUpdate(Status status) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.STATUS);
        createMessage.setStatus(status);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

    public static void sendFileMessage(byte[] file, String filename) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setFilename(filename);
        createMessage.setType(MessageType.FILE);
        createMessage.setStatus(Status.AWAY);
        createMessage.setFile(file);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

    public static void sendImageMessage(byte[] file, String filename) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setFilename(filename);
        createMessage.setType(MessageType.IMAGE);
        createMessage.setStatus(Status.AWAY);
        createMessage.setFile(file);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

}
