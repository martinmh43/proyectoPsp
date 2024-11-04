package unidades1y2.actividad13Productores;

public class Productor extends Thread {
    private long retardo;
    private long contador = 0;
    private Almacen almacen;
    private int id;

    public Productor(Almacen almacen, long retardo, int id) {
        super(String.valueOf(id));
        this.retardo = retardo;
        this.almacen = almacen;
        this.id = id;
    }

    public void run() {
        while (true) {
            String producto = String.format("%d", ++contador);
            almacen.almacenar(producto);
            System.out.println("Producto " + producto + " almacenado por Productor " + id);
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
