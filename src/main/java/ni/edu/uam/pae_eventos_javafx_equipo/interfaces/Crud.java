package ni.edu.uam.pae_eventos_javafx_equipo.interfaces;

import java.util.List;

public interface Crud<T> {

    public void  agregar(T entidad);

    public List<T> obtenerRegistros();

}
