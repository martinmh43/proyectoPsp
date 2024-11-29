package unidades1y2.barberoZZZ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Barberia {
    private final Object lock = new Object();
    private final Semaphore sillasEspera;
    private final Queue<Cliente> colaClientes;
    private boolean barberoDormido;

    public Barberia(int numSillas) {
        this.sillasEspera = new Semaphore(numSillas);
        this.colaClientes = new LinkedList<>();
        this.barberoDormido = true;
    }

    public boolean intentarSentarse(Cliente cliente) {
        if (sillasEspera.tryAcquire()) {
            synchronized (lock) {
                colaClientes.add(cliente);
                if (barberoDormido) {
                    barberoDormido = false;
                    System.out.println(cliente.getName() + " despierta al barbero.");
                    lock.notify();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Cliente obtenerCliente() {
        synchronized (lock) {
            while (colaClientes.isEmpty()) {
                try {
                    barberoDormido = true;
                    System.out.println("El barbero está durmiendo...");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            return colaClientes.poll();
        }
    }

    public void liberarSilla() {
        sillasEspera.release();
    }
}
