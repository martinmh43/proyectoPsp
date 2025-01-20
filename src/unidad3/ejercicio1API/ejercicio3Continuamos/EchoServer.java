package unidad3.ejercicio1API.ejercicio3Continuamos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            ExecutorService executorService = Executors.newFixedThreadPool(100);
            System.out.println("Servidor ECO escuchando en puerto 9000");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nueva conexión aceptada desde " + socket.getInetAddress() + ":" + socket.getPort());
                socket.setSoTimeout(5000);
                executorService.submit(new RequestTask(socket));
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
