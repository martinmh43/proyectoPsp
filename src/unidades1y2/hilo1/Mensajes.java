package unidades1y2.hilo1;

public class Mensajes {


//    public static void main(String[] args) {
//        Thread hilo = new Thread("Pepe") {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 5; i++) {
//                    try {
//                        Thread.sleep(100);
//
//                    } catch (InterruptedException e) {
//                    }
//                    System.out.println(getName() + ", message " + i);
//                }
//            }
//
//        };
//        hilo.start();
//    }

    public static void main(String[] args) {
        Thread hilo = new Thread("Pepe");
        hilo.start();
    }


}
