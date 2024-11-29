package unidades1y2.barberoZZZ;

public class Barbero extends Thread {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = barberia.obtenerCliente();
            if (cliente != null) {
                System.out.println("El barbero comienza a atender al " + cliente.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println("El barbero termina de atender al " + cliente.getName());
                barberia.liberarSilla();
            }
        }
    }
}
