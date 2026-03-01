package model;

import controller.ZonaDeCarga;

public class Repartidor implements Runnable {
    private String nombreRepartidor;
    private boolean tieneMochila;
    //private ArrayList<Pedido> pedidosAsignados;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombreRepartidor, boolean tieneMochila, ZonaDeCarga zonaDeCarga) {
        this.nombreRepartidor = nombreRepartidor;
        this.tieneMochila = tieneMochila;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombreRepartidor() {return nombreRepartidor;}
    public void setNombreRepartidor(String nombreRepartidor) {this.nombreRepartidor = nombreRepartidor;}

    public boolean isTieneMochila() {return tieneMochila;}
    public void setTieneMochila(boolean tieneMochila) {this.tieneMochila = tieneMochila;}

    //public ArrayList<Pedido> getPedidosAsignados() {return pedidosAsignados;}
    //public void setPedidosAsignados(ArrayList<Pedido> pedidosAsignados) {this.pedidosAsignados = pedidosAsignados;}

    @Override
    public void run() {
        try {
            Pedido pedido = zonaDeCarga.retirarPedido();
            if (pedido == null) {
                System.out.println("No hay pedidos.");
                return;
            }

            System.out.println("Pedido (ID:" + pedido.getIdPedido()+ ") se ha asignado a: " + getNombreRepartidor());
            Thread.sleep(1500);
            //pedido.despachar();
            pedido.setEstado(Pedido.EstadoPedido.EN_REPARTO);
            Thread.sleep(pedido.calcularTiempoEntrega() * 100L);
            pedido.setEstado(Pedido.EstadoPedido.ENTREGADO);
            System.out.println("El pedido (ID:" + pedido.getIdPedido()+ ") ha sido entregado.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Se ha interrumpido la entrega.");
        }
    }
}
