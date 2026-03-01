package vista;

import dao.PedidoDAO;
import dao.RepartidorDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    public VentanaPrincipal() {
        setTitle("Speedfast");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 25));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnEditar = new JButton("Editar pedido por ID");
        JButton btnListar = new JButton("Listar pedidos");

        JButton btnRegRep = new JButton("Registrar repartidor");
        JButton btnEdRep = new JButton("Editar repartidor por ID");
        JButton btnListRep = new JButton("Listar repartidores");

        JButton btnIniciar = new JButton("Iniciar Entrega");

        panel.add(btnRegistrar);
        panel.add(btnEditar);
        panel.add(btnListar);

        panel.add(btnRegRep);
        panel.add(btnEdRep);
        panel.add(btnListRep);

        panel.add(new JLabel(""));
        panel.add(btnIniciar);

        add(panel, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e ->
                new VentanaRegistroPedido(pedidoDAO).setVisible(true)
        );

        btnEditar.addActionListener(e ->
                new VentanaEditarPedido(pedidoDAO).setVisible(true)
        );

        btnListar.addActionListener(e ->
                new VentanaListaPedidos(pedidoDAO).setVisible(true)
        );


        btnRegRep.addActionListener(e ->
                new VentanaRegistroRepartidor(repartidorDAO).setVisible(true)
        );

        btnEdRep.addActionListener(e ->
                new VentanaEditarRepartidor(repartidorDAO).setVisible(true)
        );

        btnListRep.addActionListener(e ->
                new VentanaListaRepartidores(repartidorDAO).setVisible(true)
        );
    }
}

