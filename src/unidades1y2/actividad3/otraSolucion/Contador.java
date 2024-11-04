package unidades1y2.actividad3.otraSolucion;

import java.util.concurrent.atomic.AtomicInteger;

public class Contador {
    private AtomicInteger n = new AtomicInteger();

    public Contador(int n) {
        this.n.set(n);
    }

    public void inc() {
        n.incrementAndGet();
    }

    public int get() {
        return n.get();
    }
}
