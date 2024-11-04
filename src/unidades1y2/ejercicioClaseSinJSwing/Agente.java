package unidades1y2.ejercicioClaseSinJSwing;

import java.util.Random;

public class Agente extends Thread {

    private Mesa mesa;

    public Agente(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        String[] ingredientes = {"tabaco", "papel", "cerillas"};
        Random random = new Random();

            int index1 = random.nextInt(3);
            int index2;
            do {
                index2 = random.nextInt(3);
            } while (index1 == index2);

            String ingrediente1 = ingredientes[index1];
            String ingrediente2 = ingredientes[index2];

            mesa.colocarIngredientes(ingrediente1, ingrediente2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
