package ejercicioClase;

import java.util.HashSet;
import java.util.Set;

public class Mesa {

    Set<String> ingredientes = new HashSet<>();

    public synchronized void colocarIngredientes(String ingrediente1, String ingrediente2) {

        while (!ingredientes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        notifyAll();
    }

    public synchronized void retirarIngredientes(String ingrediente) {

        while (ingredientes.contains(ingrediente)) {
           try {
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
        }

        ingredientes.clear();
        notifyAll();
    }
}
