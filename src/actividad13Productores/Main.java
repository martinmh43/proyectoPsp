package actividad13Productores;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen(10); // Capacidad del almacén: 10

        int numConsumidores = 5;
        int numProductores = 5;

        for (int i = 1; i <= numConsumidores; i++) {
            Consumidor consumidor = new Consumidor(almacen, 200 * i, i);
            consumidor.start();
        }
        for (int i = 1; i <= numProductores; i++) {
            Productor productor = new Productor(almacen, 100 * i, i);
            productor.start();
        }


    }
}
