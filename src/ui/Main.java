package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        Direccion direccion1 = new Direccion("Valparaíso", "Viña del Mar", "14 Norte", 1200);
        Repartidor repartidor1 = new Repartidor("Benjamín Gómez", true);
        Repartidor repartidor2 = new Repartidor("Rodrigo Castro", false);
        Pedido[] pedidos = {
              new PedidoComida(1, direccion1, "Delivery", repartidor1),
              new PedidoEncomienda(2, direccion1, "Encomienda", repartidor1,40, true),
              new PedidoExpress(3, direccion1, "Express", repartidor1),
              new PedidoComida(4, direccion1, "Delivery", repartidor2),
                new PedidoEncomienda(5, direccion1, "Encomienda", repartidor2,20, false),
              new PedidoExpress(6, direccion1, "Express", repartidor2),
        };

        for (Pedido p : pedidos) {
            p.asignarRepartidor();
        }

        System.out.println("------------------------------------------------");

        for (Pedido p : pedidos) {
            p.asignarRepartidor(p.getRepartidor().getNombreRepartidor());
        }
    }
}