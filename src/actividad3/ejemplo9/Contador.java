package actividad3.ejemplo9;

public class Contador {

    private volatile int n;
    public Contador(int n) {
        this.n = n;
    }
    public void inc() {
        n++;
    }
    public int get() {
        return n;
    }

}
