package org.openjfx;

import java.io.*;

public class FileLoader {
    static ByteArrayOutputStream out;
    static FileInputStream fis = null;
    static BufferedInputStream bis = null;
    static FileOutputStream fos = null;
    static BufferedOutputStream bos = null;

    static int bytesLeidos;
    static int actual = 0;

    public static void loadFile(File archivo) {
        Runnable runner = new Runnable() {
            int bufferSize = (int) archivo.length();
            byte[] buffer = new byte[bufferSize];

            public void run() {
                out = new ByteArrayOutputStream();
                try {

                    fis = new FileInputStream(archivo);

                    bis = new BufferedInputStream(fis);
                    int count = 0;
                    while (count < buffer.length) {
                        System.out.println("ESCRIBIENDOOOOO");
                        count = bis.read(buffer, 0, buffer.length);
                        if (count > 0) {
                            out.write(buffer, 0, count);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        out.flush();
                        if (archivo.getName().endsWith(".png")) {
                            ClienteServidor.sendImageMessage(out.toByteArray(), archivo.getName());
                            System.out.println("ES UNA IMAGEN ENVIADA");
                        } else {
                            ClienteServidor.sendFileMessage(out.toByteArray(), archivo.getName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        };
        Thread captureThread = new Thread(runner);
        captureThread.start();
    }

    public static void downloadFile(byte[] archivo) {
    }
}
