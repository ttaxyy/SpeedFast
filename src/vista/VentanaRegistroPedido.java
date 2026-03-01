package vista;

import dao.PedidoDAO;
import model.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {
    private PedidoDAO pedidoDAO;

    private JTextField txtRegion = new JTextField();
    private JTextField txtComuna = new JTextField();
    private JTextField txtCalle = new JTextField();
    private JTextField txtNumero = new JTextField();
    private JComboBox<String> TipoCB = new JComboBox<>(
        new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"}
    );

    private JPanel panelExtra;
    private CardLayout cardLayout;

    private JTextField txtPeso = new JTextField();
    private JCheckBox  chkEmbalaje = new JCheckBox("¿Desea embalaje?"); //Boolean

    public VentanaRegistroPedido(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;

        setTitle("Registrar Pedido");
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del pedido"));

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
        //TODO: Agregar selección de estado

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

        setLayout(new BorderLayout(10, 10));
        add(panel,  BorderLayout.NORTH);
        add(panelExtra,  BorderLayout.CENTER);
        add(btnGuardar,  BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            String tipo = (String) TipoCB.getSelectedItem();

            if (txtRegion.getText().trim().isEmpty() || txtComuna.getText().trim().isEmpty() ||
                    txtCalle.getText().trim().isEmpty() || txtNumero.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Por favor completa todos los campos.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE); //Manejo de campos vacíos
                return;
            }

            int numero; //Cambia string a int
            try {
                numero = Integer.parseInt(txtNumero.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Número de calle debe ser valor numérico.",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Direccion dir = new Direccion( //Junta valores para crear objeto dirección
                txtRegion.getText().trim(),
                txtComuna.getText().trim(),
                txtCalle.getText().trim(),
                numero
            );

            Pedido nuevoPedido = null;

            switch (tipo) {
                case "COMIDA" -> nuevoPedido = new PedidoComida(dir);
                case "EXPRESS" -> nuevoPedido = new PedidoExpress(dir);

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

                    nuevoPedido = new PedidoEncomienda(dir, peso, embalaje);
                }
            }

            try {
                pedidoDAO.guardarPedido(nuevoPedido);
                JOptionPane.showMessageDialog(this,
                        "Pedido registrado con éxito.");
                dispose();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this,
                        "Error al agregar el pedido a la zona de carga.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
