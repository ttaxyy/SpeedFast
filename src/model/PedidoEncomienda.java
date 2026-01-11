package model;

public class PedidoEncomienda extends Pedido{
    private int peso; //en gramos
    private boolean embalaje;

    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, String tipoPedido, Repartidor repartidor, int peso, boolean embalaje) {
        super(idPedido, direccionEntrega, tipoPedido, repartidor);
        this.peso = peso;
        this.embalaje = embalaje;
    }

    public int getPeso() {return peso;}
    public void setPeso(int peso) {this.peso = peso;}

    public boolean isEmbalaje() {return embalaje;}
    public void setEmbalaje(boolean embalaje) {this.embalaje = embalaje;}

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor de encomienda.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        if (embalaje) {
            System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor de encomienda. El pedido está embalado y pesa " + peso + " gramos.");
        } else {
            System.out.println("Se ha asignado " + nombreRepartidor + " como repartidor de encomienda. El pedido no está embalado y pesa " + peso + " gramos.");
        }
    }
}
