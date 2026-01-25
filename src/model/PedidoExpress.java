package model;

public class PedidoExpress extends Pedido {
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

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor express.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor express.");
    }

    @Override
    public void despachar() {
        System.out.println("Se ha despachado el pedido express.");
    }
}
