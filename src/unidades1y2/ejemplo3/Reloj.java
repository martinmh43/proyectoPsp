package unidades1y2.ejemplo3;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reloj extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel hora;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public Reloj() {
        super("Reloj");
        hora = new JLabel(formatter.format(LocalDateTime.now()));
        hora.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(70, 70, 70, 70),
                hora.getBorder()
        ));
        hora.setFont(hora.getFont().deriveFont(30f));
        hora.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(hora);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void run() {

    }

    public void actualizar() {
        Runnable actualizarHora = new Runnable() {
            public void run() {
                hora.setText(formatter.format(LocalDateTime.now()));
            }
        };
        while (true) {
            SwingUtilities.invokeLater(actualizarHora);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void iniciar() {
        setVisible(true);
        new Thread(this::actualizar, "segundero").start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Reloj().iniciar();
            }
        });
    }
}