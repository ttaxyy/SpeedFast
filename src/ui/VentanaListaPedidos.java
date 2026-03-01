package ui;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.ZonaDeCarga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {
    private ZonaDeCarga zonaDeCarga;
    private DefaultTableModel tableModel;

    public VentanaListaPedidos(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("Lista de Pedidos");
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Dirección", "Estado", "Tipo"}, 0
        );

        JTable tabla = new JTable(tableModel);
        JButton btnRefrescar = new JButton("Refrescar tabla");
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(btnRefrescar, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());
        cargarDatos();
    }

    private void cargarDatos() {
        tableModel.setRowCount(0); // limpia la tabla antes de recargar

        for (Pedido p : zonaDeCarga.listarPedidos()) {
            tableModel.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getDireccionEntrega(),
                    p.getEstado(),
                    getTipo(p),
            });
        }
    }

    private String getTipo(Pedido p) {
        if (p instanceof PedidoComida){
            return "Comida";
        }else if (p instanceof PedidoEncomienda) {
            return "Encomienda";
        } else {
            return "Express";
        }
    }
}
