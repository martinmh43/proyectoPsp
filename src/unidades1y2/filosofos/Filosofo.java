package unidades1y2.filosofos;

public class Filosofo extends Thread {

    private Mesa mesa;
    private int id;


    public Filosofo(Mesa mesa, int id) {
        this.mesa = mesa;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            mesa.cogerTenedor(this.id);
            try {
                Thread.sleep(1000); //Simula que come y ademas me ayuda a que la consola no se ejecuta super rapido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mesa.soltarTenedor(this.id);
        }
    }

}
