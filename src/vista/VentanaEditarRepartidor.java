package vista;

import dao.RepartidorDAO;
import model.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarRepartidor extends JFrame {
    private RepartidorDAO repartidorDAO;

    private JTextField txtId = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JCheckBox  chkMochila = new JCheckBox("¿Tiene mochila?");

    public VentanaEditarRepartidor(RepartidorDAO repartidorDAO) {
        this.repartidorDAO = repartidorDAO;

        setTitle("Editar Repartidor");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnGuardar);
        panelBtn.add(btnEliminar);

        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel(""));
        panel.add(chkMochila);

        setLayout(new BorderLayout(10, 10));
        add(panel,  BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {

            if (txtId.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor completa todos los campos.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(txtId.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Id debe ser un valor numérico.",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Repartidor repartidor = new Repartidor(id, txtNombre.getText().trim(), chkMochila.isSelected());

            try {
                repartidorDAO.editarRepartidor(id, repartidor);
                JOptionPane.showMessageDialog(this,
                    "Repartidor editado con éxito.");
                dispose();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this,
                        "Error al editar el repartidor.",
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
                repartidorDAO.eliminarRepartidor(id);
                JOptionPane.showMessageDialog(this,
                        "Repartidor eliminado con éxito.");
                dispose();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar el repartidor.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
