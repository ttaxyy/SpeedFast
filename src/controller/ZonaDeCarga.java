package controller;

import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> colaPedidos;

    public ZonaDeCarga() {
        this.colaPedidos = new LinkedBlockingQueue<>();
    }

    public Pedido retirarPedido() {
        Pedido pedido = colaPedidos.poll();
        if (pedido != null) {
            System.out.println("Pedido " + pedido.getIdPedido() + " retirado de la zona de carga.");
        }
        return pedido;
    }

    public boolean estaVacia() {
        return colaPedidos.isEmpty();
    }
}
