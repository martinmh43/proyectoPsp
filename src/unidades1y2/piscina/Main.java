package unidades1y2.piscina;

import java.util.ArrayList;
import java.util.List;
public class Main {

    public static void main(String[] args) {

        Piscina piscina = new Piscina();
        List<Thread> hilosUsuarios = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Usuarios usuario = new Usuarios();
            usuario.piscina = piscina;

            Thread hilo = new Thread(usuario);
            hilosUsuarios.add(hilo);
            hilo.start();
        }


        for (Thread hilo : hilosUsuarios) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

