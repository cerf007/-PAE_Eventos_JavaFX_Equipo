package ni.edu.uam.pae_eventos_javafx_equipo.dao;

import ni.edu.uam.pae_eventos_javafx_equipo.interfaces.Crud;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Inventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioDAO implements Crud<Inventario> {
    private static final List<Inventario> registros = new ArrayList<>();

    @Override
    public void agregar(Inventario entidad) {
        registros.add(entidad);
    }

    @Override
    public List<Inventario> obtenerRegistros() {
        return registros;
    }

    public Inventario buscarPorCodigo(String codigo) {
        for (Inventario item : registros) {
            if (item.getCodigo().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }

}
