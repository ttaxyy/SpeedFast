package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class PedidoComida extends Pedido implements Despachable, Cancelable, Rastreable {
    public PedidoComida(int idPedido, Direccion direccionEntrega, int distanciaKm, Repartidor repartidor) {
        super(idPedido, direccionEntrega, distanciaKm, repartidor);
    }

    @Override
    public void calcularTiempoEntrega() {
        int tiempoEntrega = 15 + (2 * distanciaKm);
        System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
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

    @Override
    public void despachar() {
        System.out.println("Se ha despachado el pedido de comida.");
    }

    @Override
    public void cancelar() {
        System.out.println("Se ha cancelado la orden " + idPedido + ".");
    }

    @Override
    public void verHistorial() {
        
    }
}
