package model;

public class PedidoExpress extends Pedido{
    public PedidoExpress(int idPedido, Direccion direccionEntrega, int distanciaKm, Repartidor repartidor) {
        super(idPedido, direccionEntrega, distanciaKm, repartidor);
    }

    @Override
    public void calcularTiempoEntrega() {
        int tiempoEntrega = 10;

        if (distanciaKm > 5) {
            tiempoEntrega = 15;
        }

        System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
    }
}
