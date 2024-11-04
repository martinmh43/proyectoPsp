package unidades1y2.filosofos;

import java.util.HashMap;

public class Mesa {

    HashMap<Integer, Integer> tenedores = new HashMap<>() {{
        put(0, null);
        put(1, null);
        put(2, null);
        put(3, null);
        put(4, null);
    }};
    //False no cogido true que si, se inicializan a false

    public synchronized void cogerTenedor(int filosofoId) {

        int d = getDcho(filosofoId);
        int i = getIzq(filosofoId);


        while ((tenedores.get(d) != null) && tenedores.get(i) != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        tenedores.put(d, filosofoId);
        tenedores.put(i, filosofoId);
        System.out.println("El filosofo " + filosofoId + " acaba de coger lo tenedores y va a comer");

    }

    public synchronized void soltarTenedor(int filosofoId) {
        int d = getDcho(filosofoId);
        int i = getIzq(filosofoId);

        tenedores.put(d, null);
        tenedores.put(i, null);
        System.out.println("El filosofo " + filosofoId + " acaba de soltar los tenedores");
        notifyAll();
    }


    public int getDcho(int filosofoId) {
        if (filosofoId == 0) {
            return tenedores.size() - 1;
        }
        return filosofoId - 1;
    }

    public int getIzq(int filosofoId) {
        return filosofoId;
    }

}




