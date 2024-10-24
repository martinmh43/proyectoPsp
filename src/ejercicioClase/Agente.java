package ejercicioClase;

import java.util.Random;

public class Agente extends Thread{

    private Mesa mesa;
    private String[] ingredientes = {"tabaco", "papel", "cerillas"};
    private Random r = new Random();

    public Agente (Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {

        while(true) {
            int ing1 = r.nextInt(3);
            int ing2;
            do {
                ing2 = r.nextInt(3);
            } while (ing1 == ing2);

            String ingrediente1 = ingredientes[ing1];
            String ingrediente2 = ingredientes[ing2];

            mesa.colocarIngredientes(ingrediente1,ingrediente2);
            System.out.println("El agente coloca " + ingrediente1 + " y " + ingrediente2 + " en la mesa");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("PROBLEMAS EN EL PARAISO AGENTE");
                e.printStackTrace();
            }
        }
    }
}
