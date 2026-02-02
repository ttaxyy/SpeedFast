package model;

import java.util.ArrayList;

public class Repartidor implements Runnable {
    private String nombreRepartidor;
    private boolean tieneMochila;
    private ArrayList<Pedido> pedidosAsignados;

    public Repartidor(String nombreRepartidor, boolean tieneMochila, ArrayList pedidosAsignados) {
        this.nombreRepartidor = nombreRepartidor;
        this.tieneMochila = tieneMochila;
        this.pedidosAsignados = pedidosAsignados;
    }

    public String getNombreRepartidor() {return nombreRepartidor;}
    public void setNombreRepartidor(String nombreRepartidor) {this.nombreRepartidor = nombreRepartidor;}

    public boolean isTieneMochila() {return tieneMochila;}
    public void setTieneMochila(boolean tieneMochila) {this.tieneMochila = tieneMochila;}

    public ArrayList<Pedido> getPedidosAsignados() {return pedidosAsignados;}
    public void setPedidosAsignados(ArrayList<Pedido> pedidosAsignados) {this.pedidosAsignados = pedidosAsignados;}

    @Override
    public void run() {
        try {
            for (Pedido p : pedidosAsignados) {
                System.out.println("Pedido recibido (ID:" + p.getIdPedido()+ "). Se ha asignado a: " + getNombreRepartidor());
                Thread.sleep(1500);
                p.despachar();
                Thread.sleep(p.calcularTiempoEntrega() * 100L);
                System.out.println("El pedido (ID:" + p.getIdPedido()+ ") ha sido entregado.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Se ha interrumpido la entrega.");
        }
    }
}
