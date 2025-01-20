package unidad3.ejercicio1API.ejercicio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidores {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("Servidor ECO escuchando en puerto 9000");
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new RequestTask(socket));
        }
    }
}
//Clase que implemente runnable en la que se defina la tarea que hay que hacer para ateder la peticion
// del cliente, se le pasa el socket , se usa un executor service para que la ejecute
