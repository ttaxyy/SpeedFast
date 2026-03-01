package ui;

import controller.ConexionBD;
import model.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));

        try (Connection conn = ConexionBD.obtenerConexion()) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
        }

        /*ExecutorService executor = Executors.newFixedThreadPool(3);

        //for (int i = 0; i < 2; i++) { //Con este bucle, a cada repartidor se le asignan dos pedidos.
            // Arreglar: El cuarto pedido siempre se le asigna al repartidor 1, incluso si es que ya tiene un pedido asignado.
            executor.execute(new Repartidor ("Benjamín Gómez", true, zonaDeCarga));
            executor.execute(new Repartidor ("Rodrigo Castro", false, zonaDeCarga));
            executor.execute(new Repartidor("Sofía Morales", true, zonaDeCarga));
        }

        executor.shutdown();

        System.out.println("Todos los pedidos han sido entregados correctamente.");*/
    }
}