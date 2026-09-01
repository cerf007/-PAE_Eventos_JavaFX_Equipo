package ni.edu.uam.pae_eventos_javafx_equipo.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private UUID codigo;
    private String nombre;
    private String precio;
    private String cantidad;

    @Override
    public String toString() {
        return "Inventario{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }
}
