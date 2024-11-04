package unidades1y2.hilo1;

public class HiloMensaje extends Thread{

    public static void main(String[] args) {
        Thread hilo1 = new Thread("Pepe");
        Thread hilo2 = new Thread("Pepe");
        hilo1.start();
        hilo2.start();
    }


    @Override
    public void run() {
        for (int i = 1; i<=5; i++) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {}
            System.out.println(getName() + ", message " + i);
        }
    }



}
