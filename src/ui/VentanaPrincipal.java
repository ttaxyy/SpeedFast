package ui;

import controller.PedidoDAO;
import controller.RepartidorDAO;
import controller.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    public VentanaPrincipal() {
        setTitle("Speedfast");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnRegRep = new JButton("Registrar repartidor");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnListRep = new JButton("Listar repartidores");
        JButton btnIniciar = new JButton("Iniciar Entrega");

        panel.add(btnRegistrar);
        panel.add(btnRegRep);
        panel.add(btnListar);
        panel.add(btnListRep);
        panel.add(btnIniciar);

        add(panel, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e ->
                new VentanaRegistroPedido(pedidoDAO).setVisible(true)
        );

        btnRegRep.addActionListener(e ->
                new VentanaRegistroRepartidor(repartidorDAO).setVisible(true)
        );

        btnListar.addActionListener(e ->
                new VentanaListaPedidos(pedidoDAO).setVisible(true)
        );

        btnListRep.addActionListener(e ->
                new VentanaListaRepartidores(repartidorDAO).setVisible(true)
        );
    }
}

