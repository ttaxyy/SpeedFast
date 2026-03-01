package dao;

import controller.ConexionBD;
import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {
    public void agregarRepartidor(Repartidor rep) {
        String sql = "INSERT INTO repartidor (nombre, tieneMochila) VALUES (?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, rep.getNombreRepartidor());
            stmt.setBoolean(2, rep.isTieneMochila());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys(); //Guarda el ID generado por sql
            if (keys.next()) {
                rep.setIdRepartidor(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar repartidor.");
        }
    }

    public void editarRepartidor(int id, Repartidor rep) {
        String sql = "UPDATE repartidor SET nombre=?, tieneMochila=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rep.getNombreRepartidor());
            stmt.setBoolean(2, rep.isTieneMochila());
            stmt.setInt(3, rep.getIdRepartidor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar repartidor.");
        }
    }

    public void eliminarRepartidor(int id) {
        String sql = "DELETE FROM repartidor WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el repartidor de la base de datos.");
        }
    }

    public List<Repartidor> listarTodos() {
        String sql = "SELECT * FROM repartidor";
        List<Repartidor> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                boolean mochila = rs.getBoolean("tieneMochila");

                Repartidor repartidor = new Repartidor(id, nombre, mochila);

                lista.add(repartidor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar repartidores desde la base de datos.");
        }

        return lista;
    }
}
