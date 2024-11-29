package unidades1y2.actividad15;

public class Persona extends Thread {

    public Persona(int id) {
        super("Persona " + id);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(getName() + " pasea por el parque");
                Thread.sleep((long) (Math.random() * 2000 + 1000));
                System.out.println(getName() + " llega al banco");
                if (Parque.BANCO.tryAcquire()) {
                    try {
                        System.out.println(getName() + " se ha sentado");
                        Thread.sleep((long) (Math.random() * 500));
                        System.out.println(getName() + " se ha levantado");
                    } finally {
                        Parque.BANCO.release(); // Libera el lugar en el banco
                    }
                    break;
                } else {
                    System.out.println("banco lleno, " + getName() + " vuelve a pasear");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " fue interrumpida");
            Thread.currentThread().interrupt();
        }
    }
}
