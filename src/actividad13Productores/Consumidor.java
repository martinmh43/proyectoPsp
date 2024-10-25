package actividad13Productores;

public class Consumidor extends Thread {
    private long retardo;
    private Almacen almacen;
    private int id;

    public Consumidor(Almacen almacen, long retardo, int id) {
        super(String.valueOf(id));
        this.retardo = retardo;
        this.almacen = almacen;
        this.id = id;
    }

    public void run() {
        while (true) {
            String producto = almacen.retirar();
            System.out.println("Producto " + producto + " retirado por Consumidor " + id);
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
