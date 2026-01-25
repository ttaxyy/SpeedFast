package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        Direccion direccion1 = new Direccion("Valparaíso", "Viña del Mar", "14 Norte", 1200);
        Repartidor repartidor1 = new Repartidor("Benjamín Gómez", true);
        Repartidor repartidor2 = new Repartidor("Rodrigo Castro", false);

        Pedido[] pedidos = {
           new PedidoComida(1, direccion1, 15, repartidor1),
           new PedidoEncomienda(2, direccion1, 15, repartidor1,40, true),
           new PedidoExpress(3, direccion1, 15, repartidor1),
           new PedidoComida(4, direccion1, 7, repartidor2),
           new PedidoEncomienda(5, direccion1, 13, repartidor2,20, false),
           new PedidoExpress(6, direccion1, 15, repartidor2),
        };

        for (Pedido p : pedidos) {
            System.out.println("------------------------------------------------");
            System.out.println("Pedido recibido.");
            p.asignarRepartidor();
            System.out.println("-----");
            System.out.println("Ingresando manualmente repartidor...");

            String nombreRepartidor = p.getRepartidor().getNombreRepartidor();
            p.asignarRepartidor(nombreRepartidor);
            System.out.println("Información del pedido:");
            p.mostrarResumen();
            System.out.println("-----");
            p.calcularTiempoEntrega();
            p.despachar();
            p.cancelar();
            p.registrarEntrega();
        }
        System.out.println("------------------------------------------------");
        System.out.println("Historial de todos los pedidos:");
        pedidos[0].verHistorial();
    }
}