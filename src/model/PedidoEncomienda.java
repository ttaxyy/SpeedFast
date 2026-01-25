package model;

public class PedidoEncomienda extends Pedido{
    private int peso; //en gramos
    private boolean embalaje;

    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, int distanciaKm, Repartidor repartidor, int peso, boolean embalaje) {
        super(idPedido, direccionEntrega, distanciaKm, repartidor);
        this.peso = peso;
        this.embalaje = embalaje;
    }

    public int getPeso() {return peso;}
    public void setPeso(int peso) {this.peso = peso;}

    public boolean isEmbalaje() {return embalaje;}
    public void setEmbalaje(boolean embalaje) {this.embalaje = embalaje;}

    @Override
    public void calcularTiempoEntrega() {
        int tiempoEntrega = (int) (20 + (1.5 * distanciaKm)); //Usa el número entero, no redondea.
        System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
    }

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
