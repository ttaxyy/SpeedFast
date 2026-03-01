package vista;

import dao.PedidoDAO;
import model.*;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarPedido extends JFrame {
    private PedidoDAO pedidoDAO;

    private JTextField txtId = new JTextField();

    private JTextField txtRegion = new JTextField();
    private JTextField txtComuna = new JTextField();
    private JTextField txtCalle = new JTextField();
    private JTextField txtNumero = new JTextField();
    private JComboBox<String> TipoCB = new JComboBox<>(
            new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"}
    );
    private JComboBox<String> EstadoCB = new JComboBox<>(
            new String[]{"PENDIENTE", "EN_REPARTO", "ENTREGADO"}
    );

    private JPanel panelExtra;
    private CardLayout cardLayout;

    private JTextField txtPeso = new JTextField();
    private JCheckBox  chkEmbalaje = new JCheckBox("¿Desea embalaje?");

    public VentanaEditarPedido(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;

        setTitle("Editar Pedido");
        setSize(400, 550);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del pedido"));

        panel.add(new JLabel("Id:"));
        panel.add(txtId);
        panel.add(new JLabel("Región:"));
        panel.add(txtRegion);
        panel.add(new JLabel("Comuna:"));
        panel.add(txtComuna);
        panel.add(new JLabel("Calle:"));
        panel.add(txtCalle);
        panel.add(new JLabel("Número:"));
        panel.add(txtNumero);

        panel.add(new JLabel("Tipo:"));
        panel.add(TipoCB);

        panel.add(new JLabel("Estado"));
        panel.add(EstadoCB);

        cardLayout = new CardLayout();
        panelExtra = new JPanel(cardLayout);
        panelExtra.setBorder(BorderFactory.createTitledBorder("Datos adicionales"));

        JPanel panelVacio = new JPanel();
        panelVacio.add(new JLabel("Sin datos adicionales para este tipo."));

        JPanel panelEncomienda = new JPanel(new GridLayout(2, 2, 8, 8));
        panelEncomienda.add(new JLabel("Peso (kg):"));
        panelEncomienda.add(txtPeso);

        panelEncomienda.add(new JLabel(""));
        panelEncomienda.add(chkEmbalaje);

        panelExtra.add(panelVacio,"COMIDA"); //TODO: Solucionar el que aparezcan datos adicionales para comida
        panelExtra.add(panelEncomienda,"ENCOMIENDA");
        panelExtra.add(panelVacio,"EXPRESS");

        TipoCB.addActionListener(e -> {
            String tipo = (String) TipoCB.getSelectedItem();
            cardLayout.show(panelExtra, tipo);
        });

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnGuardar);
        panelBtn.add(btnEliminar);


        setLayout(new BorderLayout(10, 10));
        add(panel,  BorderLayout.NORTH);
        add(panelExtra,  BorderLayout.CENTER);
        add(panelBtn,  BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            String tipo = (String) TipoCB.getSelectedItem();
            String estado = (String) EstadoCB.getSelectedItem();

            if (txtId.getText().trim().isEmpty() || txtRegion.getText().trim().isEmpty() || txtComuna.getText().trim().isEmpty() ||
                    txtCalle.getText().trim().isEmpty() || txtNumero.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Por favor completa todos los campos.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE); //Manejo de campos vacíos
                return;
            }

            int id, numero;
            try {
                id = Integer.parseInt(txtId.getText().trim());
                numero = Integer.parseInt(txtNumero.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Id y Número de calle deben ser valores numéricos.",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Direccion dir = new Direccion(
                    txtRegion.getText().trim(),
                    txtComuna.getText().trim(),
                    txtCalle.getText().trim(),
                    numero
            );

            Pedido pedido = null;

            switch (tipo) {
                case "COMIDA" -> pedido = new PedidoComida(id, dir);
                case "EXPRESS" -> pedido = new PedidoExpress(id, dir);

                case "ENCOMIENDA" -> {
                    int peso;
                    boolean embalaje = chkEmbalaje.isSelected();

                    if (txtPeso.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                "Ingresa el peso del paquete.",
                                "Error de validación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        peso = Integer.parseInt(txtPeso.getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "El peso debe ser un número.",
                                "Error de formato", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    pedido = new PedidoEncomienda(id, dir, peso, embalaje);
                }
            }

            pedido.setEstado(Pedido.EstadoPedido.valueOf(estado));

            try { //TODO: Manejar el caso donde no haya pedido con ese ID
                pedidoDAO.editarPedido(id, pedido);
                JOptionPane.showMessageDialog(this,
                        "Pedido editado con éxito.");
                dispose();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this,
                        "Error al editar el pedido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtId.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Por favor rellena el ID.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id;
            try {
                id = Integer.parseInt(txtId.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Id y Número de calle deben ser valores numéricos.",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                pedidoDAO.eliminarPedido(id);
                JOptionPane.showMessageDialog(this,
                        "Pedido eliminado con éxito.");
                dispose();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar el pedido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
