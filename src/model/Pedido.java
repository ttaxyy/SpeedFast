package model;

public class Pedido {
    protected int idPedido;
    protected Direccion direccionEntrega;
    protected int distanciaKm;
    protected Repartidor repartidor;

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

    public void calcularTiempoEntrega() { } //Abstracto
}
