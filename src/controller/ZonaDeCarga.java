package controller;

import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> colaPedidos;

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
    }

    public void agregarPedido(Pedido p) throws InterruptedException {
        if (p == null) throw new IllegalArgumentException("El pedido no puede ser nulo.");

        String sql = "INSERT INTO pedido (tipo, estado, distancia_km, dir_region, dir_comuna, dir_calle, dir_numero, peso, embalaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String tipo = switch (p) {
                case PedidoComida ignored -> "Comida";
                case PedidoEncomienda ignored -> "Encomienda";
                case PedidoExpress ignored -> "Express";
                default -> throw new IllegalArgumentException("Tipo desconocido");
            };

            Direccion dir = p.getDireccionEntrega();

            stmt.setString(1, tipo);
            stmt.setString(2, p.getEstado().name());
            stmt.setInt(3, p.getDistanciaKm());
            stmt.setString(4, dir.getRegion());
            stmt.setString(5, dir.getComuna());
            stmt.setString(6, dir.getCalle());
            stmt.setInt(7, dir.getNumero());

            if (p instanceof PedidoEncomienda pe) {
                stmt.setInt(8,  pe.getPeso());
                stmt.setBoolean(9, pe.isEmbalaje());
            } else {
                stmt.setNull(8, Types.INTEGER);
                stmt.setNull(9, java.sql.Types.BOOLEAN);
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

    public Pedido retirarPedido() {
        Pedido pedido = colaPedidos.poll();
        if (pedido != null) {
            System.out.println("Pedido " + pedido.getIdPedido() + " retirado de la zona de carga.");
        }
        return pedido;
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
                int distancia = rs.getInt("distancia_km");
                String tipo = rs.getString("tipo");

                String estadoStr = rs.getString("estado");
                Pedido.EstadoPedido estado = Pedido.EstadoPedido.valueOf(estadoStr);

                Pedido pedido = switch (tipo) {
                    case "Comida" -> new PedidoComida(id, dir, distancia);
                    case "Express" -> new PedidoExpress(id, dir, distancia);
                    case "Encomienda" -> new PedidoEncomienda(
                            id, dir, distancia,
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

    public boolean existePedido(int id) { //Revisar si existe ID (ya que se ingresa manual en panel)
        return colaPedidos.stream().anyMatch(p -> p.getIdPedido() == id);
    }

    public boolean estaVacia() {
        return colaPedidos.isEmpty();
    }
}
