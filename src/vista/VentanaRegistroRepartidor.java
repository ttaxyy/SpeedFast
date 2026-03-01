package vista;

import dao.RepartidorDAO;
import model.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroRepartidor extends JFrame {
    private RepartidorDAO repartidorDAO;

    private JTextField txtNombre = new JTextField();
    private JCheckBox  chkMochila = new JCheckBox("¿Tiene mochila?");

    public VentanaRegistroRepartidor(RepartidorDAO repartidorDAO) {
        this.repartidorDAO = repartidorDAO;

        setTitle("Registrar Repartidor");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        JButton btnGuardar = new JButton("Guardar");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel(""));
        panel.add(chkMochila);

        setLayout(new BorderLayout(10, 10));
        add(panel,  BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {

            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor completa todos los campos.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Repartidor repartidor = new Repartidor(txtNombre.getText().trim(), chkMochila.isSelected());

            repartidorDAO.agregarRepartidor(repartidor);
            JOptionPane.showMessageDialog(this,
                    "Repartidor registrado con éxito.");
            dispose();
        });
    }
}
