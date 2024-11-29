package unidades1y2.barberoZZZ;

public class Cliente extends Thread {
    private final Barberia barberia;

    public Cliente(int id, Barberia barberia) {
        super("Cliente " + id);
        this.barberia = barberia;
    }

    @Override
    public void run() {
        System.out.println(getName() + " llega a la barbería.");
        if (barberia.intentarSentarse(this)) {
            System.out.println(getName() + " se sienta en la sala de espera.");
        } else {
            System.out.println(getName() + " encuentra la barbería llena y se va.");
        }
    }
}
