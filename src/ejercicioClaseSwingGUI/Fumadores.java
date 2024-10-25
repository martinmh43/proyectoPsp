package ejercicioClaseSwingGUI;

public class Fumadores extends Thread {
    private Mesa mesa;
    private String ingrediente;
    private boolean suspendido = false;

    public Fumadores(Mesa mesa, String ingrediente) {
        this.mesa = mesa;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (suspendido) {
                    try {
                        wait(); // El hilo se detiene si está suspendido.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mesa.retirarIngredientes(ingrediente);
            try {
                Thread.sleep(1000); // Simula el tiempo que toma fumar.
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

