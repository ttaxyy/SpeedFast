package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> colaPedidos;

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
    }

    public void agregarPedido(Pedido p) throws InterruptedException {
        if (p == null) throw new IllegalArgumentException("El pedido no puede ser nulo");
        colaPedidos.put(p);
        System.out.println("Pedido " + p.getIdPedido() + " agregado a la zona de carga.");
    }

    public Pedido retirarPedido() {
        Pedido pedido = colaPedidos.poll();
        if (pedido != null) {
            System.out.println("Pedido " + pedido.getIdPedido() + " retirado de la zona de carga.");
        }
        return pedido;
    }

    public List<Pedido> listarPedidos() { //Convierte el queue en lista
        return new ArrayList<>(colaPedidos);
    }

    public boolean existePedido(int id) { //Revisar si existe ID (ya que se ingresa manual en panel)
        return colaPedidos.stream().anyMatch(p -> p.getIdPedido() == id);
    }

    public boolean estaVacia() {
        return colaPedidos.isEmpty();
    }
}
