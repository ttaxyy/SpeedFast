package dao;

import controller.ConexionBD;
import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public void guardarPedido(Pedido p) {
        if (p == null) throw new IllegalArgumentException("El pedido no puede ser nulo.");

        String sql = "INSERT INTO pedido (tipo, estado, dir_region, dir_comuna, dir_calle, dir_numero, peso, embalaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String tipo = switch (p) {
                case PedidoComida ignored -> "COMIDA";
                case PedidoEncomienda ignored -> "ENCOMIENDA";
                case PedidoExpress ignored -> "EXPRESS";
                default -> throw new IllegalArgumentException("Tipo desconocido");
            };

            Direccion dir = p.getDireccionEntrega();

            stmt.setString(1, tipo);
            stmt.setString(2, p.getEstado().name());
            stmt.setString(3, dir.getRegion());
            stmt.setString(4, dir.getComuna());
            stmt.setString(5, dir.getCalle());
            stmt.setInt(6, dir.getNumero());

            if (p instanceof PedidoEncomienda pe) {
                stmt.setInt(7,  pe.getPeso());
                stmt.setBoolean(8, pe.isEmbalaje());
            } else {
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, java.sql.Types.BOOLEAN);
            }

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys(); //Guarda el ID generado por sql
            if (keys.next()) {
                p.setIdPedido(keys.getInt(1));
            }

            System.out.println("Pedido " + p.getIdPedido() + " agregado a la zona de carga.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el pedido.");
        }
    }

    public void eliminarPedido(int id) {
        String sql = "DELETE FROM pedido WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido de la base de datos.");
        }
    }

    public void editarPedido(int id, Pedido p) {
        String sql = "UPDATE pedido SET tipo=?, estado=?, dir_region=?, dir_comuna=?, dir_calle=?, dir_numero=?, peso=?, embalaje=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            String tipo = switch (p) {
                case PedidoComida ignored -> "COMIDA";
                case PedidoEncomienda ignored -> "ENCOMIENDA";
                case PedidoExpress ignored -> "EXPRESS";
                default -> throw new IllegalArgumentException("Tipo desconocido");
            };

            Direccion dir = p.getDireccionEntrega();

            stmt.setString(1, tipo);
            stmt.setString(2, p.getEstado().name());
            stmt.setString(3, dir.getRegion());
            stmt.setString(4, dir.getComuna());
            stmt.setString(5, dir.getCalle());
            stmt.setInt(6, dir.getNumero());

            if (p instanceof PedidoEncomienda pe) {
                stmt.setInt(7,  pe.getPeso());
                stmt.setBoolean(8, pe.isEmbalaje());
            } else {
                stmt.setNull(7, Types.INTEGER);
                stmt.setNull(8, java.sql.Types.BOOLEAN);
            }
            stmt.setInt(9, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido de la base de datos.");
        }
    }

    public List<Pedido> listarPedidos() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Direccion dir = new Direccion(
                        rs.getString("dir_region"),
                        rs.getString("dir_comuna"),
                        rs.getString("dir_calle"),
                        rs.getInt("dir_numero")
                );

                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String estadoStr = rs.getString("estado");
                Pedido.EstadoPedido estado = Pedido.EstadoPedido.valueOf(estadoStr);

                Pedido pedido = switch (tipo) {
                    case "COMIDA" -> new PedidoComida(id, dir);
                    case "EXPRESS" -> new PedidoExpress(id, dir);
                    case "ENCOMIENDA" -> new PedidoEncomienda(
                            id, dir,
                            rs.getInt("peso"),
                            rs.getBoolean("embalaje")
                    );
                    default -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
                };
                pedido.setEstado(estado);

                lista.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos desde la base de datos.");
        }

        return lista;
    }
}
