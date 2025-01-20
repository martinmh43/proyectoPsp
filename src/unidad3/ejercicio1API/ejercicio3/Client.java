package unidad3.ejercicio1API.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("192.168.0.166", 9000);
        try (socket) {
            new DataOutputStream(socket.getOutputStream()).writeUTF("logaritmo neperiano");
            String s = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println(s);
        }
    }
}
// hacer un servidor que con una peticion acepte varioas cadenas y un cliente que le envie varias cadenas
// la respuesta del servidor tiene que ser lo mismo que envie el cliente