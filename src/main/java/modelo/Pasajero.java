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
    int cocheId;

    public Pasajero(int edad, int peso, String nombre){
        this.edad = edad;
        this.peso = peso;
        this.nombre = nombre;
    }
    /*
    public Pasajero(int edad, int peso, String nombre, int cocheId){
        this.edad = edad;
        this.peso = peso;
        this.nombre = nombre;
        this.cocheId = cocheId;
    }
    */
}
