package unidades1y2.actividad4;

public class ejemploDeadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Hilo1: aguantando lock 1...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Hilo1: esperando por lock 2...");
                synchronized (lock2) {
                    System.out.println("Hilo1: esperando lock 1 & 2...");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Hilo2: aguantando lock 2...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Hilo2: esperando por lock 1...");
                synchronized (lock1) {
                    System.out.println("Hilo2: aguantando lock 1 & 2...");
                }
            }
        });

        t1.start();
        t2.start();
    }

}
