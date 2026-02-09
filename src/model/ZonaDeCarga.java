package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> colaPedidos;

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
    }

    public synchronized void agregarPedido(Pedido p) throws InterruptedException {
        colaPedidos.put(p); //TODO: Cambiar a try/catch para manejar exception
    };

    public synchronized Pedido retirarPedido() {
        Pedido pedido = colaPedidos.poll(); //poll() => Retira el primer elemento de la cola y lo devuelve.

        if (pedido != null) {
            System.out.println("Pedido " + pedido.getIdPedido() + " retirado de la zona de carga");
        }

        return pedido;
    }
}
