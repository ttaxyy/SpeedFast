package model;

public class PedidoEncomienda extends Pedido {
    private int peso; //en gramos
    private boolean embalaje;

    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, int distanciaKm, int peso, boolean embalaje) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.peso = peso;
        this.embalaje = embalaje;
    }

    public int getPeso() {return peso;}
    public void setPeso(int peso) {this.peso = peso;}

    public boolean isEmbalaje() {return embalaje;}
    public void setEmbalaje(boolean embalaje) {this.embalaje = embalaje;}

    @Override
    public int calcularTiempoEntrega() {
        int tiempoEntrega = (int) (20 + (1.5 * distanciaKm)); //Usa el número entero, no redondea.
        //System.out.println("Tiempo de entrega calculado: " + tiempoEntrega + " minutos.");
        return tiempoEntrega;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor de encomienda.");
    }

    @Override
    public void asignarRepartidor(Repartidor repartidor) {
        if (embalaje) {
            System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor de encomienda. El pedido está embalado y pesa " + peso + " gramos.");
        } else {
            System.out.println("Se ha asignado " + repartidor.getNombreRepartidor() + " como repartidor de encomienda. El pedido no está embalado y pesa " + peso + " gramos.");
        }
    }

    @Override
    public void despachar() {
        System.out.println("Se ha despachado el pedido de encomienda.");
    }
}
