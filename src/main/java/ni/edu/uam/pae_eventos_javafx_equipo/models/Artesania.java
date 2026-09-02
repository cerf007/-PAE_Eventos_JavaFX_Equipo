package ni.edu.uam.pae_eventos_javafx_equipo.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Artesania {
    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;
    private String imagenPath;
}
