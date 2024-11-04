package unidades1y2.ejercicioClaseSwingGUI;

import javax.swing.*;
public class Ventana extends JFrame {

    private JButton Reanudar;
    private JButton Suspender;
    private JButton Parar;

    private Fumadores fumadorTabaco;
    private Fumadores fumadorPapel;
    private Fumadores fumadorCerillas;
    private Agente agente;
    private Mesa mesa;

    public Ventana() {

        setTitle("Simulación de Fumadores");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mesa = new Mesa();
        fumadorTabaco = new Fumadores(mesa, "tabaco");
        fumadorPapel = new Fumadores(mesa, "papel");
        fumadorCerillas = new Fumadores(mesa, "cerillas");
        agente = new Agente(mesa);


        fumadorTabaco.start();
        fumadorPapel.start();
        fumadorCerillas.start();
        agente.start();


        Parar.addActionListener(e -> {
            fumadorTabaco.suspender();
            fumadorPapel.suspender();
            fumadorCerillas.suspender();
        });

        Reanudar.addActionListener(e -> {
            fumadorTabaco.reanudar();
            fumadorPapel.reanudar();
            fumadorCerillas.reanudar();
        });

        Suspender.addActionListener(e -> {
            agente.suspender();
        });
    }
}

