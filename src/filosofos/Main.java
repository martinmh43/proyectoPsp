package filosofos;

public class Main {



    public static void main(String[] args) {

        Mesa mesa = new Mesa();
        Filosofo filosofo0 = new Filosofo(mesa, 0);
        Filosofo filosofo1 = new Filosofo(mesa, 1);
        Filosofo filosofo2 = new Filosofo(mesa, 2);
        Filosofo filosofo3 = new Filosofo(mesa, 3);
        Filosofo filosofo4 = new Filosofo(mesa, 4);

        filosofo0.start();
        filosofo1.start();
        filosofo2.start();
        filosofo3.start();
        filosofo4.start();

    }

}
