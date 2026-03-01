package model;

import interfaces.Cancelable;
import interfaces.Despachable;

public abstract class Pedido implements Despachable, Cancelable {
    public enum EstadoPedido {
        PENDIENTE,
        EN_REPARTO,
        ENTREGADO
    }

    public enum TipoPedido {
        COMIDA,
        ENCOMIENDA,
        EXPRESS
    }

    protected int idPedido;
    protected Direccion direccionEntrega;
    protected EstadoPedido estado;

    public Pedido(Direccion direccionEntrega) { //Constructor sin ID
        this.idPedido = 0;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(int idPedido, Direccion direccionEntrega) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public Direccion getDireccionEntrega() {return direccionEntrega;}
    public void setDireccionEntrega(Direccion direccionEntrega) {this.direccionEntrega = direccionEntrega;}

    public EstadoPedido getEstado() {return estado;}
    public void setEstado(EstadoPedido nuevoEstado) {this.estado = nuevoEstado;}

    public void mostrarResumen() {
        System.out.println("ID del pedido: " + idPedido + ", dirección de entrega: " + direccionEntrega + ".");
    }

    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor.");
    }

    public void asignarRepartidor(Repartidor repartidor) {
        System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor.");
    }

    public abstract int calcularTiempoEntrega();

    @Override
    public abstract void despachar();

    @Override
    public void cancelar() {
        System.out.println("Se ha cancelado la orden " + idPedido + ".");
    }
}
