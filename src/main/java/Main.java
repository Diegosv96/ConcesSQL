import controlador.Concesionario;
import database.DBConnection;
import modelo.Coche;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Concesionario concesionario = new Concesionario();

        int opcion;
        int id;
        String matricula;
        String marca;
        String modelo;
        String color;

        boolean loop = true;
        while(loop){
            System.out.println("\nMenú:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consultar coche por ID");
            System.out.println("4. Listar coches");
            System.out.println("5. Modificar coche");
            System.out.println("6. Gestión de pasajeros");
            System.out.println("7. Terminar programa");
            System.out.print("Selecciona una opción: ");

            opcion = scan.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("Matrícula: ");
                    matricula = scan.next();
                    System.out.println("Marca: ");
                    marca = scan.next();
                    System.out.println("Modelo: ");
                    modelo = scan.next();
                    System.out.println("Color: ");
                    color = scan.next();

                    System.out.println(concesionario.anadirCoche(matricula,marca,modelo,color));
                    break;
                case 2:
                    concesionario.listarCoches();
                    System.out.println("Id del coche a eliminar: ");
                    id = scan.nextInt();

                    System.out.println(concesionario.eliminarCoche(id));
                    break;
                case 3:
                    System.out.println("Id del coche a buscar: ");
                    id = scan.nextInt();

                    System.out.println(concesionario.buscarCoche(id));
                    break;
                case 4:
                    concesionario.listarCoches();
                    break;
                case 5:
                    concesionario.listarCoches();
                    System.out.println("Id del coche a modificar: ");
                    id = scan.nextInt();
                    System.out.println("Matrícula: ");
                    matricula = scan.next();
                    System.out.println("Marca: ");
                    marca = scan.next();
                    System.out.println("Modelo: ");
                    modelo = scan.next();
                    System.out.println("Color: ");
                    color = scan.next();
                    concesionario.modificarCoche(new Coche(id,matricula,marca,modelo,color));
                    break;
                case 6:
                    menuPasajeros(scan,concesionario);
                    break;
                case 7:
                    loop = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        System.out.println("Fin del programa");
        concesionario.cerrarConexion();
        scan.close();
    }

    public static void menuPasajeros(Scanner scan, Concesionario concesionario){
        boolean loop = true;
        while(loop){
            System.out.println("1. Añadir pasajero");
            System.out.println("2. Borrar pasajero");
            System.out.println("3. Consultar pasajero");
            System.out.println("4. Listar pasajeros");
            System.out.println("5. Añadir pasajero a un coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar pasajeros de un coche");
            System.out.println("8. Salir al menú principal");

            int op = scan.nextInt();
            String nombre;
            int id, edad, peso, cocheId;

            switch(op){
                case 1:
                    concesionario.listarCoches();
                    System.out.println("Nombre: ");
                    nombre = scan.next();
                    System.out.println("Edad: ");
                    edad = scan.nextInt();
                    System.out.println("Peso: ");
                    peso = scan.nextInt();
                    System.out.println("Id del coche: ");
                    cocheId = scan.nextInt();
                    System.out.println(concesionario.anadirPasajero(nombre,edad,peso,cocheId));
                    break;
                case 2:
                    concesionario.listarPasajeros();
                    System.out.println("Id del pasajero a eliminar: ");
                    id = scan.nextInt();
                    System.out.println(concesionario.borrarPasajero(id));
                    break;
                case 3:
                    System.out.println("Id del pasajero a buscar: ");
                    id = scan.nextInt();
                    System.out.println(concesionario.buscarPasajero(id));
                    break;
                case 4:
                    concesionario.listarPasajeros();
                    break;
                case 5:
                    concesionario.listarCoches();
                    concesionario.listarPasajeros();
                    System.out.println("Id del pasajero: ");
                    id = scan.nextInt();
                    System.out.println("Id del coche ");
                    cocheId = scan.nextInt();
                    System.out.println(concesionario.anadirPasajeroCoche(id,cocheId));
                    break;
                case 6:
                    System.out.println("Id del pasajero: ");
                    id = scan.nextInt();
                    System.out.println(concesionario.eliminarPasajeroCoche(id));
                    break;
                case 7:
                    System.out.println("Id del coche: ");
                    cocheId = scan.nextInt();
                    concesionario.listarPasajerosCoche(cocheId);
                    break;
                case 8:
                    loop = false;
                    break;
            }
        }
    }
}
