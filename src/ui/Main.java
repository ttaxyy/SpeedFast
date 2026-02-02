package ui;

import model.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Direccion direccion1 = new Direccion("Valparaíso", "Viña del Mar", "14 Norte", 1200);

        ArrayList<Pedido> pedidos1 = new ArrayList<>() {
            {   add(new PedidoComida(1, direccion1, 15));
                add(new PedidoEncomienda(2, direccion1, 15,40, true));
                add(new PedidoExpress(3, direccion1, 15));
            }
        };

        ArrayList<Pedido> pedidos2 = new ArrayList<>() {
            {   add(new PedidoComida(4, direccion1, 7));
                add(new PedidoEncomienda(5, direccion1, 13,20, false));
                add(new PedidoExpress(6, direccion1, 15));
            }
        };

        ArrayList<Pedido> pedidos3 = new ArrayList<>() {
            {   add(new PedidoComida(7, direccion1, 16));
                add(new PedidoEncomienda(8, direccion1, 12,15, false));
                add(new PedidoExpress(9, direccion1, 4));
            }
        };

        /* Repartidor[] repartidores = {
                new Repartidor ("Benjamín Gómez", true, pedidos1),
                new Repartidor ("Rodrigo Castro", false, pedidos2),
                new Repartidor("Sofía Morales", true, pedidos3),
        };


        for (Repartidor r : repartidores) {
            Thread thread = new Thread(r);
            thread.start();
        } */

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Repartidor ("Benjamín Gómez", true, pedidos1));
        executor.execute(new Repartidor ("Rodrigo Castro", false, pedidos2));
        executor.execute(new Repartidor("Sofía Morales", true, pedidos3));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdownNow();
    }
}