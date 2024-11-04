package unidades1y2.ejercicioClaseSwingGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
        Mesa mesa = new Mesa();
        Fumadores fumadorTabaco = new Fumadores(mesa,"tabaco");
        Fumadores fumadorPapel = new Fumadores(mesa, "papel");
        Fumadores fumadorCerillas = new Fumadores(mesa, "cerillas");

        Agente agenre = new Agente(mesa);

        fumadorTabaco.start();
        fumadorPapel.start();
        fumadorCerillas.start();
        agenre.start();
    }




}
