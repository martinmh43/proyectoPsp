package unidades1y2.robotsEmpaquetadores;


import java.util.Random;

class PlacerRobot implements Runnable {
    private final ConveyorBelt conveyorBelt;
    private final Random random;

    public PlacerRobot(ConveyorBelt conveyorBelt) {
        this.conveyorBelt = conveyorBelt;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int product = random.nextInt(3) + 1;
                conveyorBelt.placeProduct(product);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
