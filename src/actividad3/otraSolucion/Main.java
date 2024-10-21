package actividad3.otraSolucion;

public class Main {

    private static void run(Contador c) {
        for (int i = 0; i < 100; i++) {
            c.inc();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador(100);
        Thread t1 = new Thread(() -> run(c));
        Thread t2 = new Thread(() -> run(c));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Contador = " + c.get());
    }
}


