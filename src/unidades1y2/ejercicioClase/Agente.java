package unidades1y2.ejercicioClase;

import java.util.Random;

public class Agente extends Thread {

    private Mesa mesa;
    private volatile boolean suspendido = false;
    private volatile boolean detenido = false;
    private final Object control = new Object();

    public Agente(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        String[] ingredientes = {"tabaco", "papel", "cerillas"};
        Random random = new Random();

        while (!detenido) {
            synchronized (control) {
                while (suspendido) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            int index1 = random.nextInt(3);
            int index2;
            do {
                index2 = random.nextInt(3);
            } while (index1 == index2);

            String ingrediente1 = ingredientes[index1];
            String ingrediente2 = ingredientes[index2];

            mesa.colocarIngredientes(ingrediente1, ingrediente2);
            System.out.println("El agente ha puesto " + ingrediente1 + " y " + ingrediente2 + " en la mesa");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void suspender() {
        suspendido = true;
    }

    public void reanudar() {
        suspendido = false;
        synchronized (control) {
            control.notifyAll();
        }
    }

    public void parar() {
        detenido = true;
        reanudar();
    }
}
