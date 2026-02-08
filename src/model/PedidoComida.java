package model;

public class PedidoComida extends Pedido{
    public PedidoComida(int idPedido, Direccion direccionEntrega, int distanciaKm, Estado estadoActual) {
        super(idPedido, direccionEntrega, distanciaKm, estadoActual);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempoEntrega = 15 + (2 * distanciaKm);
        //System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
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
