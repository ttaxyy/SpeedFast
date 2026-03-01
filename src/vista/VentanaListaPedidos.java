package vista;

import dao.PedidoDAO;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {
    private PedidoDAO pedidoDAO;
    private DefaultTableModel tableModel;

    public VentanaListaPedidos(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;

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

        for (Pedido p : pedidoDAO.listarPedidos()) {
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
            return "COMIDA";
        }else if (p instanceof PedidoEncomienda) {
            return "ENCOMIENDA";
        } else {
            return "EXPRESS";
        }
    }
}
