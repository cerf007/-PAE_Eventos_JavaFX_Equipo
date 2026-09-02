package ni.edu.uam.pae_eventos_javafx_equipo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lote {
    private String codigo;
    private String productor;
    private String producto;
    private double cantidad;
}