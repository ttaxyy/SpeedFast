package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    public enum EstadoPedido {
        PENDIENTE,
        EN_REPARTO,
        ENTREGADO
    }

    protected int idPedido;
    protected Direccion direccionEntrega;
    protected int distanciaKm;
    protected EstadoPedido estado;
    private static List<Pedido> historialEntregas = Collections.synchronizedList(new ArrayList<>());

    public Pedido(int idPedido, Direccion direccionEntrega, int distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public Direccion getDireccionEntrega() {return direccionEntrega;}
    public void setDireccionEntrega(Direccion direccionEntrega) {this.direccionEntrega = direccionEntrega;}

    public int getDistanciaKm() {return distanciaKm;}
    public void setDistanciaKm(int distanciaKm) {this.distanciaKm = distanciaKm;}

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

    public void registrarEntrega() {
        historialEntregas.add(this);
    }

    @Override
    public abstract void despachar();

    @Override
    public void cancelar() {
        System.out.println("Se ha cancelado la orden " + idPedido + ".");
    }

    @Override
    public void verHistorial() {
        if (historialEntregas.isEmpty()) {
            System.out.println("No hay entregas registradas aún");
        } else {
            for (Pedido p : historialEntregas) {
                    System.out.println(
                        "ID del Pedido: " + p.idPedido + ", dirección de entrega: " + direccionEntrega + ".");
            }
        }
    }
}
