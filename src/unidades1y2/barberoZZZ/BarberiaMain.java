package unidades1y2.barberoZZZ;

public class BarberiaMain {
    public static void main(String[] args) {
        int numClientes = 10;
        int numSillas = 5;

        Barberia barberia = new Barberia(numSillas);
        Barbero barbero = new Barbero(barberia);
        barbero.start();

        // Crear e iniciar los hilos de los clientes
        for (int i = 1; i <= numClientes; i++) {
            Cliente cliente = new Cliente(i, barberia);
            cliente.start();
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
