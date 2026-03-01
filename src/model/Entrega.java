package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entrega {
    private int idEntrega;
    private int idPedido;
    private int idRepartidor;
    private LocalDate fecha;
    private LocalTime hora;

    public Entrega(int idEntrega, int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {
        this.idEntrega = idEntrega;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Entrega(int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {
        this.idEntrega = 0;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
