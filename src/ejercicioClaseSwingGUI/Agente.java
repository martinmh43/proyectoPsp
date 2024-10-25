package ejercicioClaseSwingGUI;

import java.util.Random;

public class Agente extends Thread {
    private Mesa mesa;
    private boolean suspendido = false;

    public Agente(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        String[] ingredientes = {"tabaco", "papel", "cerillas"};
        Random random = new Random();

        while (true) {
            synchronized (this) {
                while (suspendido) {
                    try {
                        wait(); // El agente se detiene si está suspendido.
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
            try {
                Thread.sleep(1000); // Espera antes de poner nuevos ingredientes.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void suspender() {
        suspendido = true;
    }

    public synchronized void reanudar() {
        suspendido = false;
        notify();
    }
}

