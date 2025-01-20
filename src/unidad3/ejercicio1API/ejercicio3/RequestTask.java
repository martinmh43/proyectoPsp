package unidad3.ejercicio1API.ejercicio3;

import java.io.*;
import java.net.Socket;

public class RequestTask implements Runnable{

    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try (socket) {
            String s = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println("Peticion: " + s + " : " + socket.getInetAddress() + " : " + socket.getPort());
            new DataOutputStream(socket.getOutputStream()).writeUTF(s);
            System.out.println("Respuesta: " + s + " : " + socket.getLocalAddress() + " : " + socket.getLocalPort());
        } catch (Exception e){

        }
    }
}
