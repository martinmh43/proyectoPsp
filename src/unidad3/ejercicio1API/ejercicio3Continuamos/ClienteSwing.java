package unidad3.ejercicio1API.ejercicio3Continuamos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClienteSwing {

    private static DataOutputStream out;
    private static DataInputStream in;
    private static JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cliente ECO");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);

            JPanel panel = new JPanel(new BorderLayout());

            textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            JTextField textField = new JTextField();
            JButton sendButton = new JButton("Enviar");
            bottomPanel.add(textField, BorderLayout.CENTER);
            bottomPanel.add(sendButton, BorderLayout.EAST);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setVisible(true);

            JRootPane rootPane = frame.getRootPane();
            rootPane.putClientProperty("apple.awt.fullscreenable", true);

            sendButton.addActionListener(e -> {
                String mensaje = textField.getText();
                if (!mensaje.isEmpty()) {
                    try {
                        out.writeUTF(mensaje);
                        textArea.append("Tú: " + mensaje + "\n");
                        textField.setText("");
                    } catch (IOException ex) {
                        textArea.append("Error al enviar mensaje: " + ex.getMessage() + "\n");
                    }
                }
            });

            new Thread(() -> {
                try (Socket socket = new Socket("localhost", 9000)) {
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());

                    textArea.append("Conectado al servidor.\n");

                    while (true) {
                        String respuesta = in.readUTF();
                        textArea.append("Servidor: " + respuesta + "\n");
                    }
                } catch (EOFException ex) {
                    textArea.append("El servidor cerró la conexión.\n");
                } catch (IOException ex) {
                    textArea.append("Error: " + ex.getMessage() + "\n");
                }
            }).start();

            frame.setExtendedState(JFrame.NORMAL);

            frame.getRootPane().getActionMap().put("minimize", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setState(Frame.ICONIFIED);
                }
            });

            frame.getRootPane().getActionMap().put("fullscreen", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setExtendedState(frame.getExtendedState() ^ JFrame.MAXIMIZED_BOTH);
                }
            });

            frame.getRootPane().getActionMap().put("restore", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setExtendedState(JFrame.NORMAL);
                }
            });
        });
    }
}
