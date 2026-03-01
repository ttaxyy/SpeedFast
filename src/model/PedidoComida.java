package model;

public class PedidoComida extends Pedido{
    public PedidoComida(Direccion direccionEntrega) {
        super(direccionEntrega);
    }

    public PedidoComida(int idPedido, Direccion direccionEntrega) {
        super(idPedido, direccionEntrega);
    }

    @Override //Eliminar
    public int calcularTiempoEntrega() {
        int tiempoEntrega = 15 + (2 * 0);
        return tiempoEntrega;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor de comida.");
    }

    @Override
    public void asignarRepartidor(Repartidor repartidor) {
        if (repartidor.isTieneMochila()) {
            System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor de comida, quien tiene mochila térmica.");
        } else {
            System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor de comida, quien NO tiene mochila térmica.");
        }
    }

    @Override
    public void despachar() {
        System.out.println("Se ha despachado el pedido de comida.");
    }
}
