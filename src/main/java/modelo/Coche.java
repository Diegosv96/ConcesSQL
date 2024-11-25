package modelo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coche {
    private int id;
    private String matricula, marca, modelo, color;

    public Coche(String matricula, String marca, String modelo, String color){
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
}
