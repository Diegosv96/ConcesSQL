import controlador.Concesionario;
import database.DBConnection;
import modelo.Coche;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Concesionario concesionario = new Concesionario();

        boolean loop = true;
        while(loop){
            System.out.println("\nMenú:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consultar coche por ID");
            System.out.println("4. Listar coches");
            System.out.println("5. Exportar coches a archivo CSV");
            System.out.println("6. Terminar programa");
            System.out.print("Selecciona una opción: ");

            int opcion = scan.nextInt();
            int id;

            switch(opcion){
                case 1:System.out.println("Matrícula: ");
                    String matricula = scan.next();
                    System.out.println("Marca: ");
                    String marca = scan.next();
                    System.out.println("Modelo: ");
                    String modelo = scan.next();
                    System.out.println("Color: ");
                    String color = scan.next();

                    System.out.println(concesionario.anadirCoche(matricula,marca,modelo,color));
                    break;
                case 2:
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
                    concesionario.exportarCSV();
                    System.out.println("csv creado");
                    break;
                case 6:
                    loop = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        System.out.println("Fin del programa");
        scan.close();
    }
}
