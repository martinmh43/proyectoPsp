package unidades1y2.piscina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Piscina {

    public int c = 0;
    Semaphore piscina = new Semaphore(8);
    private final List<Integer> nadadores = Collections.synchronizedList(new ArrayList<>(List.of(0, 0, 0, 0, 0)));


    public synchronized void salto(Usuarios u) {
        Random r = new Random();
        while (c < 20) {
            try {
                switch (u.getTipo()) {
                    case 0:
                        piscina.acquire(1);
                        nadadores.set(0, nadadores.get(0)+1);
                        System.out.println("Un hombre ha entrado a nadar");
                        break;
                    case 1:
                        piscina.acquire(1);
                        nadadores.set(1, nadadores.get(1)+1);
                        System.out.println("Una mujer ha entrado a nadar");
                        break;
                    case 2:
                        piscina.acquire(1);
                        nadadores.set(2, nadadores.get(2)+1);
                        System.out.println("Un niño ha entrado a nadar");
                        break;
                    case 3:
                        piscina.acquire(1);
                        nadadores.set(3, nadadores.get(3)+1);
                        System.out.println("Una nila ha entrado a nadar");
                        break;
                    case 4:
                        piscina.acquire(2);
                        nadadores.set(4, nadadores.get(4)+1);
                        System.out.println("Un buzo ha entrado a nadar");
                        break;
                }
                int timepo = r.nextInt(31) + 50;
                    Thread.sleep(timepo);
                salirPiscina();
                piscina.release();
                System.out.println("Han entrado " + nadadores.get(0) + " hombres, " +
                        nadadores.get(1) + " mujeres, " + nadadores.get(2) + " niños, " +
                        nadadores.get(3) + " niñas y " + nadadores.get(4) + " submarinistas.");
            } catch (InterruptedException e) {
                System.out.println("Cagaste");
            }
        }
    }

    private synchronized void salirPiscina() {
        c++;
        System.out.println("Total de personas que han salidod e la piscina: " + c);
    }


}
