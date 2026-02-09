package ui;

import model.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Direccion direccion1 = new Direccion("Valparaíso", "Viña del Mar", "14 Norte", 1200);
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        zonaDeCarga.agregarPedido(new PedidoComida(1, direccion1, 8));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(2, direccion1, 15,40, true));
        zonaDeCarga.agregarPedido(new PedidoExpress(3, direccion1, 10));
        zonaDeCarga.agregarPedido(new PedidoComida(4, direccion1, 7));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(5, direccion1, 13,20, false));
        zonaDeCarga.agregarPedido(new PedidoExpress(6, direccion1, 15));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 2; i++) { //Con este bucle, a cada repartidor se le asignan dos pedidos.
            // Arreglar: El cuarto pedido siempre se le asigna al repartidor 1, incluso si es que ya tiene un pedido asignado.
            executor.execute(new Repartidor ("Benjamín Gómez", true, zonaDeCarga));
            executor.execute(new Repartidor ("Rodrigo Castro", false, zonaDeCarga));
            executor.execute(new Repartidor("Sofía Morales", true, zonaDeCarga));
        }

        executor.shutdown();

        System.out.println("Todos los pedidos han sido entregados correctamente.");
    }
}