package ejercicioClase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazFumadoreses extends JFrame {

    private Mesa mesa;
    private Fumadores FumadoresTabaco;
    private Fumadores FumadoresPapel;
    private Fumadores FumadoresCerillas;
    private Agente agente;

    public InterfazFumadoreses(Mesa mesa, Fumadores FumadoresTabaco, Fumadores FumadoresPapel, Fumadores FumadoresCerillas, Agente agente) {
        this.mesa = mesa;
        this.FumadoresTabaco = FumadoresTabaco;
        this.FumadoresPapel = FumadoresPapel;
        this.FumadoresCerillas = FumadoresCerillas;
        this.agente = agente;

        setTitle("Simulación de Fumadoreses");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnSuspender = new JButton("Suspender");
        JButton btnReanudar = new JButton("Reanudar");
        JButton btnParar = new JButton("Parar");

        add(btnSuspender);
        add(btnReanudar);
        add(btnParar);

        btnSuspender.addActionListener(e -> suspenderHilos());
        btnReanudar.addActionListener(e -> reanudarHilos());
        btnParar.addActionListener(e -> pararHilos());
    }

    private void suspenderHilos() {
        FumadoresTabaco.suspender();
        FumadoresPapel.suspender();
        FumadoresCerillas.suspender();
        agente.suspender();
        System.out.println("Todos los hilos han sido suspendidos.");
    }

    private void reanudarHilos() {
        FumadoresTabaco.reanudar();
        FumadoresPapel.reanudar();
        FumadoresCerillas.reanudar();
        agente.reanudar();
        System.out.println("Todos los hilos han sido reanudados.");
    }

    private void pararHilos() {
        FumadoresTabaco.parar();
        FumadoresPapel.parar();
        FumadoresCerillas.parar();
        agente.parar();
        System.out.println("Todos los hilos han sido detenidos.");
    }

    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        Fumadores FumadoresTabaco = new Fumadores(mesa, "tabaco");
        Fumadores FumadoresPapel = new Fumadores(mesa, "papel");
        Fumadores FumadoresCerillas = new Fumadores(mesa, "cerillas");
        Agente agente = new Agente(mesa);

        InterfazFumadoreses ventana = new InterfazFumadoreses(mesa, FumadoresTabaco, FumadoresPapel, FumadoresCerillas, agente);
        ventana.setVisible(true);

        FumadoresTabaco.start();
        FumadoresPapel.start();
        FumadoresCerillas.start();
        agente.start();
    }
}
