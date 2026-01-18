package model;

public class PedidoComida extends Pedido{
    public PedidoComida(int idPedido, Direccion direccionEntrega, int distanciaKm, Repartidor repartidor) {
        super(idPedido, direccionEntrega, distanciaKm, repartidor);
    }

    @Override
    public void calcularTiempoEntrega() {
        int tiempoEntrega = 15 + (2 * distanciaKm);
        System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
    }
}
