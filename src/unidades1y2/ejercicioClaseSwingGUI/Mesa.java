package unidades1y2.ejercicioClaseSwingGUI;

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
        System.out.println("El agente ha puesto los ingredientes " + ingrediente1 + " y " + ingrediente2);
        notifyAll();
    }

    public synchronized void retirarIngredientes(String ingrediente) {

        while (!ingredientes.containsAll(faltantes(ingrediente))) {
            System.out.println("El fumador " + ingrediente + " se va a dormir");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ingredientes.clear();
        System.out.println("El fumador " + ingrediente + " toma los ingredientes y se despiertan los fumadores.");
        notifyAll();
    }

    private Set<String> faltantes(String ingrediente) {
        Set<String> todos = new HashSet<>();
        todos.add("tabaco");
        todos.add("papel");
        todos.add("cerillas");
        todos.remove(ingrediente);
        return todos;
    }
}
