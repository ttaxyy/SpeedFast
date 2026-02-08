package model;

public class PedidoExpress extends Pedido {
    public PedidoExpress(int idPedido, Direccion direccionEntrega, int distanciaKm, Estado estadoActual) {
        super(idPedido, direccionEntrega, distanciaKm, estadoActual);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempoEntrega = 10;

        if (distanciaKm > 5) {
            tiempoEntrega = 15;
        }

        //System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
        return tiempoEntrega;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor express.");
    }

    @Override
    public void asignarRepartidor(Repartidor repartidor) {
        System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor express.");
    }

    @Override
    public void despachar() {
        System.out.println("Se ha despachado el pedido express.");
    }
}
