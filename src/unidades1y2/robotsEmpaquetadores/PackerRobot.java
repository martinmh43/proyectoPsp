package unidades1y2.robotsEmpaquetadores;


class PackerRobot implements Runnable {
    private final ConveyorBelt conveyorBelt;
    private final int productType;

    public PackerRobot(ConveyorBelt conveyorBelt, int productType) {
        this.conveyorBelt = conveyorBelt;
        this.productType = productType;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (conveyorBelt.removeProduct(productType)) {
                    System.out.println("Empaquetador " + productType + ": Producto empaquetado.");
                }
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
