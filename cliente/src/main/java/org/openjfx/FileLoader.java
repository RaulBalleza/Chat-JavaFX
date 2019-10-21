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
                        Listener.sendFileMessage(out.toByteArray());
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
        //TODO
    }
}
