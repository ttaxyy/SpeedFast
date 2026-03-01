package ui;

import controller.RepartidorDAO;
import model.Pedido;
import model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaRepartidores extends JFrame{
    private RepartidorDAO repartidorDAO;
    private DefaultTableModel tableModel;

    public VentanaListaRepartidores(RepartidorDAO repartidorDAO) {
        this.repartidorDAO = repartidorDAO;

        setTitle("Lista de Repartidores");
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Tiene mochila"}, 0
        );

        JTable tabla = new JTable(tableModel);
        JButton btnRefrescar = new JButton("Refrescar tabla");
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(btnRefrescar, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());
        cargarDatos();
    }

    private void cargarDatos() {
        tableModel.setRowCount(0);

        for (Repartidor rep : repartidorDAO.listarTodos()) {
            tableModel.addRow(new Object[]{
                    rep.getIdRepartidor(),
                    rep.getNombreRepartidor(),
                    rep.isTieneMochila()
            });
        }
    }
}
