package unidades1y2.actividad15;

import java.util.concurrent.Semaphore;

public class Parque {

    private static final int NUMPERSONAS = 20;
    private static final int NUMPLAZAS = 5;
    public static final Semaphore BANCO = new Semaphore(NUMPLAZAS);

    public static void main(String[] args) throws InterruptedException {
        Thread[] personas = new Thread[NUMPERSONAS];

        for (int i = 0; i < NUMPERSONAS; i++) {
            personas[i] = new Persona(i + 1);
            personas[i].start();
        }

        for (int i = 0; i < NUMPERSONAS; i++) {
            personas[i].join();
        }
    }
}
