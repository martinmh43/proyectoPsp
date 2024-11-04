package unidades1y2.ejerciciosRepaso_finalTema.ejercicio1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable{

    long tiempo;

    public Main(long timepo){
        this.tiempo = timepo;
    }

    public static void main(String[] args){

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i<20; i++) {
            service.submit(new Main(i * 500));
        }
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " dormira " + tiempo +" milisegundos");
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " ha finalizado");
    }
}
