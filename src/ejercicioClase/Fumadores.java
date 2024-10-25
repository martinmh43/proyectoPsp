package ejercicioClase;

public class Fumadores extends Thread {

    private Mesa mesa;
    private String ingrediente;
    private volatile boolean suspendido = false;
    private volatile boolean detenido = false;
    private final Object control = new Object();

    public Fumadores(Mesa mesa, String ingrediente) {
        this.mesa = mesa;
        this.ingrediente = ingrediente;
    }



    @Override
    public void run() {
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
            mesa.retirarIngredientes(ingrediente);
            System.out.println("El fumador con " + ingrediente + " está fumando.");
            try {
                Thread.sleep(1000); // Simula el tiempo que tarda en fumar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("El fumador con " + ingrediente + " ha terminado de fumar.");
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
