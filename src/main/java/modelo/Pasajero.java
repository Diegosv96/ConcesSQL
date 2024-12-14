package modelo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pasajero {
    int id, edad, peso;
    String nombre;
    int pasajeros_coches_FK;

    public Pasajero(int edad, int peso, String nombre){
        this.edad = edad;
        this.peso = peso;
        this.nombre = nombre;
    }
    /*
    public Pasajero(int edad, int peso, String nombre, int pasajeros_coches_FK){
        this.edad = edad;
        this.peso = peso;
        this.nombre = nombre;
        this.pasajeros_coches_FK = pasajeros_coches_FK;
    }
    */
}
