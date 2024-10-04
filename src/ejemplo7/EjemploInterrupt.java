package ejemplo7;

public class EjemploInterrupt extends Thread {
    public void run() {
        while (!isInterrupted()) {
            System.out.println("en el Hilo");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("interrumpido mientras dormía");
                interrupt();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        EjemploInterrupt h = new EjemploInterrupt();
        h.start();
        Thread.sleep(2000);
        h.interrupt();
        h.join();
        System.out.println("hilo finalizado");
    }
}
