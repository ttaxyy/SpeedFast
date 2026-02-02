package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected int idPedido;
    protected Direccion direccionEntrega;
    protected int distanciaKm;
    protected Repartidor repartidor;
    private static ArrayList<Pedido> historialEntregas = new ArrayList<>();

    public Pedido(int idPedido, Direccion direccionEntrega, int distanciaKm, Repartidor repartidor) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.repartidor = repartidor;
    }

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public Direccion getDireccionEntrega() {return direccionEntrega;}
    public void setDireccionEntrega(Direccion direccionEntrega) {this.direccionEntrega = direccionEntrega;}

    public int getDistanciaKm() {return distanciaKm;}
    public void setDistanciaKm(int distanciaKm) {this.distanciaKm = distanciaKm;}

    public Repartidor getRepartidor() {return repartidor;}
    public void setRepartidor(Repartidor repartidor) {this.repartidor = repartidor;}

    public void mostrarResumen() {
        System.out.println("ID del pedido: " + idPedido + ", dirección de entrega: " + direccionEntrega + ". Repartidor asignado: " + repartidor.getNombreRepartidor() + ".");
    }

    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor.");
    }

    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor.");
    }

    public abstract void calcularTiempoEntrega();

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
                        "ID del Pedido: " + p.idPedido + ", dirección de entrega: " + direccionEntrega + ". Repartidor: " + p.repartidor.getNombreRepartidor());
            }
        }
    }
}
