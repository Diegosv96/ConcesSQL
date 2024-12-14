package controlador;

import database.DBConnection;
import modelo.Coche;
import modelo.Pasajero;
import modeloDAO.CochesDAO;
import modeloDAO.PasajerosDAO;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Concesionario {

    private CochesDAO cochesDAO;
    private PasajerosDAO pasajerosDAO;
    private DBConnection db;
    private Connection connection;
    public Concesionario() {
        db = new DBConnection();
        connection = db.getConnection();
        cochesDAO = new CochesDAO(connection);
        pasajerosDAO = new PasajerosDAO(connection);
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
                System.out.println(coche.toString());
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

    public void cerrarConexion(){
        db.closeConnection();
    }

    public String anadirPasajero(String nombre, int edad, int peso, int cocheId) {
        try {
            Coche coche = cochesDAO.buscarCoche(cocheId);
            if(coche != null){
                Pasajero pas = new Pasajero(edad,peso,nombre);
                pasajerosDAO.insertPasajero(pas,cocheId);
                return "Pasajero añadido correctamente";
            }else{
                return "No existe el coche seleccionado";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String borrarPasajero(int id) {
        try {
            pasajerosDAO.deletePasajero(id);
            return "Pasajero eliminado";
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public String buscarPasajero(int id){
        try {
            return pasajerosDAO.selectPasajero(id).toString();
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void listarPasajeros(){
        try {
            System.out.println("Pasajeros:");
            for(Pasajero pas : pasajerosDAO.selectAllPasajeros()){
                System.out.println(pas.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String anadirPasajeroCoche(int pasajeroId, int cocheId) {
        try {
            if(pasajerosDAO.selectPasajero(pasajeroId) == null){
                return "No existe el pasajro seleccionado";
            }
            if(cochesDAO.buscarCoche(cocheId) == null){
                return "No existe el coche seleccionado";
            }
            pasajerosDAO.updatePasajeroFK(pasajeroId,cocheId);
            return "Pasajero añadido";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String eliminarPasajeroCoche(int pasajeroId) {
        try {
            if(pasajerosDAO.selectPasajero(pasajeroId) == null){
                return "No existe el pasajro seleccionado";
            }
            pasajerosDAO.updatePasajeroFKNull(pasajeroId);
            return "Pasajero añadido";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarPasajerosCoche(int cocheId){
        try {
            if(cochesDAO.buscarCoche(cocheId)==null){
                System.out.println("No existe el coche seleccionado");
            }else{
                System.out.println("Pasajeros:");
                for(Pasajero pas : pasajerosDAO.selectPasajerosCoche(cocheId)){
                    System.out.println(pas.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
