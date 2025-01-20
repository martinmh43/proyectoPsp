package unidad3.ejercicio1API.ejercicio3Continuamos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9000)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            for (int i = 0; i < 10; i++) {
                out.writeUTF("hola " + i);
            }
            socket.shutdownOutput(); // Finalizar flujo de salida

            System.out.println("Petición enviada : " +
                    socket.getInetAddress() + " : " + socket.getPort() +
                    " : " + LocalDateTime.now());
            try {
                while (true) {
                    String respuesta = in.readUTF();
                    System.out.println("Respuesta del servidor: " + respuesta);
                }
            } catch (EOFException e) {
                System.out.println("Comunicación finalizada por el servidor.");
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
