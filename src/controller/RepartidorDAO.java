package controller;

import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {
    public void agregarRepartidor(Repartidor rep) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rep.getNombreRepartidor());

            stmt.executeUpdate();
        } catch (
        SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar repartidor.");
        }
    }

    public List<Repartidor> listarTodos() {
        String sql = "SELECT * FROM repartidor";
        List<Repartidor> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String nombre = rs.getString("nombre");

                Repartidor repartidor = new Repartidor(nombre, true);

                lista.add(repartidor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar repartidores desde la base de datos.");
        }

        return lista;
    }
}
