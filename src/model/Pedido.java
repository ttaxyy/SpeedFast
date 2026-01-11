package model;

public class Pedido {
    protected int idPedido;
    protected Direccion direccionEntrega;
    protected String tipoPedido;
    protected Repartidor repartidor;

    public Pedido(int idPedido, Direccion direccionEntrega, String tipoPedido, Repartidor repartidor) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.repartidor = repartidor;
    }

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    public Direccion getDireccionEntrega() {return direccionEntrega;}
    public void setDireccionEntrega(Direccion direccionEntrega) {this.direccionEntrega = direccionEntrega;}

    public String getTipoPedido() {return tipoPedido;}
    public void setTipoPedido(String tipoPedido) {this.tipoPedido = tipoPedido;}

    public Repartidor getRepartidor() {return repartidor;}
    public void setRepartidor(Repartidor repartidor) {this.repartidor = repartidor;}

    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor.");
    }

    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor.");
    }
}
