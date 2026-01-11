package model;

public class PedidoComida extends Pedido{
    public PedidoComida(int idPedido, Direccion direccionEntrega, String tipoPedido, Repartidor repartidor) {
        super(idPedido, direccionEntrega, tipoPedido, repartidor);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor de comida.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        if (repartidor.isTieneMochila()) {
            System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor de comida, quien tiene mochila térmica.");
        } else {
            System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor de comida, quien NO tiene mochila térmica.");
        }
    }
}
