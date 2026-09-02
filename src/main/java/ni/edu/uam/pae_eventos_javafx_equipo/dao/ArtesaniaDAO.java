package ni.edu.uam.pae_eventos_javafx_equipo.dao;

import ni.edu.uam.pae_eventos_javafx_equipo.interfaces.Crud;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Artesania;

import java.util.ArrayList;
import java.util.List;

public class ArtesaniaDAO implements Crud<Artesania> {
    private static final List<Artesania> registros = new ArrayList<>();

    @Override
    public void agregar(Artesania entidad) {
        registros.add(entidad);
    }

    @Override
    public List<Artesania> obtenerRegistros() {
        return registros;
    }

    public Artesania buscarPorCodigo(String codigo) {
        for (Artesania item : registros) {
            if (item.getCodigo().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }
}