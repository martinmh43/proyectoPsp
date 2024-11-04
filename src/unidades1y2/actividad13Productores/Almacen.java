package unidades1y2.actividad13Productores;

public class Almacen {
    private int almacenados = 0;
    private String[] productos;

    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }

    public synchronized void almacenar(String producto) {
        while (almacenados == productos.length) { // almacén lleno
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        productos[almacenados++] = producto;
        System.out.println("Producto " + producto + " almacenado.");
        notifyAll();
    }

    public synchronized String retirar() {
        while (almacenados == 0) { // almacén vacío
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String producto = productos[--almacenados];
        System.out.println("Producto " + producto + " retirado.");
        notifyAll();
        return producto;
    }
}
