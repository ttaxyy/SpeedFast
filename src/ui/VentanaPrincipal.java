package ui;

import model.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

    public VentanaPrincipal() {
        setTitle("Speedfast");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnIniciar = new JButton("Iniciar Entrega");

        panel.add(btnRegistrar);
        panel.add(btnListar);
        panel.add(btnIniciar);

        add(panel, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e ->
                new VentanaRegistroPedido(zonaDeCarga).setVisible(true)
        );

        btnListar.addActionListener(e ->
                new VentanaListaPedidos(zonaDeCarga).setVisible(true)
        );
    }
}

