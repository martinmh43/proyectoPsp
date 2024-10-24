package ejercicioClase;

public class Fumadores extends Thread {

    private Mesa mesa;
    private String ingrediente;

    public Fumadores(Mesa mesa, String ingrediente) {
        this.mesa = mesa;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {
        while (true) {

            mesa.retirarIngredientes(this.ingrediente);
            System.out.println("El fumador con " + this.ingrediente + " esta echandose un piti");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("ERROR 404, HUMO NO ENCONTRADO");
                e.printStackTrace();
            }
            System.out.println("El fumador con " + this.ingrediente + " se quedo sin piti");
        }
    }


}
