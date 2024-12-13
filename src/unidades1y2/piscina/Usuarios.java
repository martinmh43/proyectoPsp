package unidades1y2.piscina;

import java.util.Random;

public class Usuarios implements Runnable{

    int tipo;
    String[] lista = new String[]{"HOMBRE", "MUJER", "NIÑO", "NIÑA", "SUBMARINISTA"};
    Piscina piscina;

    public Usuarios (){
        Random r = new Random();
        tipo = r.nextInt(5);
    }

    public int getTipo(){
        return this.tipo;
    }

    @Override
    public void run() {
        piscina.salto(this);
    }
}
