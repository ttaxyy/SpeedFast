package model;

public class PedidoExpress extends Pedido{
    public PedidoExpress(int idPedido, Direccion direccionEntrega, String tipoPedido, Repartidor repartidor) {
        super(idPedido, direccionEntrega, tipoPedido, repartidor);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor express.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor express.");
    }
}
