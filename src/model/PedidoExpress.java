package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class PedidoExpress extends Pedido implements Despachable, Cancelable, Rastreable {
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

    @Override
    public void cancelar() {
        System.out.println("Se ha cancelado la orden " + idPedido + ".");
    }

    @Override
    public void verHistorial() {

    }
}
