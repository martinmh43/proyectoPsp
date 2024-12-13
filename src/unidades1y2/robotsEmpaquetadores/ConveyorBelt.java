package unidades1y2.robotsEmpaquetadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

class ConveyorBelt {
    private final List<Integer> conveyorBelt;
    private final Semaphore emptySpaces;
    private final Semaphore mutex;
    private int totalPacked;

    public ConveyorBelt(int maxCapacity) {
        this.conveyorBelt = Collections.synchronizedList(new ArrayList<>());
        this.emptySpaces = new Semaphore(maxCapacity);
        this.mutex = new Semaphore(1);
        this.totalPacked = 0;
    }

    public void placeProduct(int product) throws InterruptedException {
        emptySpaces.acquire();
        mutex.acquire();
        try {
            conveyorBelt.add(product);
            System.out.println("Colocador: Producto " + product + " colocado en la cinta.");
        } finally {
            mutex.release();
        }
    }

    public boolean removeProduct(int productType) {
        synchronized (conveyorBelt) {
            Iterator<Integer> iterator = conveyorBelt.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == productType) {
                    iterator.remove();
                    incrementTotalPacked();
                    emptySpaces.release();
                    return true;
                }
            }
            return false;
        }
    }

    private synchronized void incrementTotalPacked() {
        totalPacked++;
        System.out.println("Total productos empaquetados: " + totalPacked);
    }
}