package unidades1y2.robotsEmpaquetadores;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        int maxCapacity = 10;
        ConveyorBelt conveyorBelt = new ConveyorBelt(maxCapacity);

        // Crear hilos para el colocador y los empaquetadores
        Thread placer = new Thread(new PlacerRobot(conveyorBelt));
        Thread packer1 = new Thread(new PackerRobot(conveyorBelt, 1));
        Thread packer2 = new Thread(new PackerRobot(conveyorBelt, 2));
        Thread packer3 = new Thread(new PackerRobot(conveyorBelt, 3));

        // Iniciar todos los hilos
        placer.start();
        packer1.start();
        packer2.start();
        packer3.start();
    }
}