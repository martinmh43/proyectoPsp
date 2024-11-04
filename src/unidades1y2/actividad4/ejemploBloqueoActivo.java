package unidades1y2.actividad4;

public class ejemploBloqueoActivo {

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Hilo1: Haciendo el flag true...");
            flag = true;
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Hilo2: esperando a que el flag sea true...");
            while (!flag) {
            }
            System.out.println("Hilo2: el flag es true");
        });

        t2.start();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        t1.start();
    }
}
