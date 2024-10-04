package hilo1;

public class UnHilo extends Thread{

    public static void main(String[] args) {
        Thread t = new UnHilo("Pee");
        for (int i = 1; i <= 3; i++) {
            new UnHilo(i).start();
            t.start();
        }
    }

    public UnHilo(String name){
        super(name);
    }

    public UnHilo(int id){
        super("hilo" + id);
    }

    public UnHilo(){
        super();
    }

    public UnHilo(ThreadGroup group, String name){
        super(group,name);
    }

    @Override
    public void run(){
        for (int i = 1; i<=5; i++) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {}
            System.out.println(getName() + ", message " + i);
        }
    }
}