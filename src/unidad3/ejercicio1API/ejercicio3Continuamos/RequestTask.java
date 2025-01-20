package unidad3.ejercicio1API.ejercicio3Continuamos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class RequestTask implements Runnable {

    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Petición recibida : " +
                    socket.getInetAddress() + " : " + socket.getPort() +
                    " : " + LocalDateTime.now());

            try {
                while (true) {
                    String mensaje = in.readUTF();
                    System.out.println("Mensaje recibido: " + mensaje);
                    out.writeUTF("Eco: " + mensaje);
                }
            } catch (EOFException e) {
                System.out.println("Cliente desconectado : " +
                        socket.getInetAddress() + " : " + socket.getPort() +
                        " : " + LocalDateTime.now());
            }
        } catch (Exception e) {
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Error: " + e.getMessage());
            } catch (IOException ex) {
                System.err.println("Error al enviar mensaje de error al cliente: " + ex.getMessage());
            }
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
