package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entrega {
    private int idEntrega;
    private Pedido pedido;
    private Repartidor repartidor;
    private LocalDate fecha;
    private LocalTime hora;

    public Entrega(Pedido pedido, Repartidor repartidor, LocalDate fecha, LocalTime hora) {
        this.idEntrega = 0;
        this.pedido = pedido;
        this.repartidor = repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Entrega(int idEntrega, Pedido pedido, Repartidor repartidor, LocalDate fecha, LocalTime hora) {
        this.idEntrega = idEntrega;
        this.pedido = pedido;
        this.repartidor = repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdEntrega() {return idEntrega;}
    public void setIdEntrega(int idEntrega) {this.idEntrega = idEntrega;}

    public Pedido getPedido() {return pedido;}
    public void setPedido(Pedido pedido) {this.pedido = pedido;}

    public Repartidor getRepartidor() {return repartidor;}
    public void setRepartidor(Repartidor repartidor) {this.repartidor = repartidor;}

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public LocalTime getHora() {return hora;}
    public void setHora(LocalTime hora) {this.hora = hora;}
}
