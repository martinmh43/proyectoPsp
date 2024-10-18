package actividad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploSuspension extends Thread {
    static boolean suspendido = false;  //No se ha declarado volatile porque usamos bloques synchronized como el que
                                        // empieza en la linea 29, de esta forma tenemos la llamada exclusion mutua.
    static boolean parado = false;

    public synchronized void suspender() {
        suspendido = true;
    }
    public synchronized void reanudar() {
        suspendido = false;
        notify();
    }

    public synchronized void parar() {
        parado = true;
        notify();
    }

    @Override
    public void run() {
        while (!parado) {
            try {
                synchronized (this) {
                    if (suspendido) {
                        System.out.println("suspendido");
                        wait();
                    }
                }
                System.out.println("en ejecución");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("En teoria se paro el hilo bien :)");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        EjemploSuspension hilo = new EjemploSuspension();
        hilo.start();
        System.out.println("s -> suspender / r -> reanudar / p -> parar");

        do {
            String opcion = in.readLine();
            switch (opcion.toLowerCase()) {
                case "s":
                    hilo.suspender();
                    break;
                case "r":
                    hilo.reanudar();
                    break;
                case "p":
                    hilo.parar();
                    hilo.join();
                    System.out.println("Se acabo el programa");
                    return;
            }
        } while(true);
    }

}
