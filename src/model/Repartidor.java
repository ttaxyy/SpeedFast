package model;

import controller.ZonaDeCarga;

public class Repartidor implements Runnable {
    private int idRepartidor;
    private String nombreRepartidor;
    private boolean tieneMochila;

    public Repartidor(String nombreRepartidor, boolean tieneMochila) {
        this.idRepartidor = 0;
        this.nombreRepartidor = nombreRepartidor;
        this.tieneMochila = tieneMochila;
    }

    public Repartidor(int idRepartidor, String nombreRepartidor, boolean tieneMochila) {
        this.idRepartidor = idRepartidor;
        this.nombreRepartidor = nombreRepartidor;
        this.tieneMochila = tieneMochila;
    }

    public int getIdRepartidor() {return idRepartidor;}
    public void setIdRepartidor(int idRepartidor) {this.idRepartidor = idRepartidor;}

    public String getNombreRepartidor() {return nombreRepartidor;}
    public void setNombreRepartidor(String nombreRepartidor) {this.nombreRepartidor = nombreRepartidor;}

    public boolean isTieneMochila() {return tieneMochila;}
    public void setTieneMochila(boolean tieneMochila) {this.tieneMochila = tieneMochila;}

    @Override
    public void run() {
        /*try {
            Pedido pedido = zonaDeCarga.listarPedidos();
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
        }*/
    }
}
