package ejercicioClaseSinJSwing;

public class Fumadores extends Thread {

    private Mesa mesa;
    private String ingrediente;

    public Fumadores(Mesa mesa, String ingrediente) {
        this.mesa = mesa;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {
            mesa.retirarIngredientes(ingrediente);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
