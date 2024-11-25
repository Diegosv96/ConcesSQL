package controlador;

import modelo.Coche;
import modeloDAO.CochesDAO;

import java.io.*;
import java.util.ArrayList;

public class Concesionario {

    private CochesDAO cochesDAO;
    public Concesionario() {
        cochesDAO = new CochesDAO();
    }

    public String anadirCoche(String matricula, String marca, String modelo, String color) {
        try{
            Coche coche = new Coche(matricula, marca, modelo, color);
            cochesDAO.insertarCoche(coche);
            return "Coche creado";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String eliminarCoche(int id) {
        try{
            cochesDAO.eliminarCoche(id);
            return "Coche eliminado";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String buscarCoche(int id) {
        try{
            return cochesDAO.buscarCoche(id).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listarCoches() {
        try{
            System.out.println("Coches:");
            for (Coche coche : cochesDAO.listaCoches()) {
                System.out.println(coche.toString() + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarCSV() {
        File file = new File("src/main/java/resources/coches.csv");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Id;Matrícula;Marca;Modelo;Color");
            for (Coche c : cochesDAO.listaCoches()) {
                writer.printf("%d;%s;%s;%s;%s%n", c.getId(), c.getMatricula(), c.getMarca(), c.getModelo(), c.getColor());
            }
            System.out.println("Coches exportados a coches.csv");
        } catch (Exception e) {
            System.out.println("Error al exportar los coches: " + e.getMessage());
        }
    }
}
