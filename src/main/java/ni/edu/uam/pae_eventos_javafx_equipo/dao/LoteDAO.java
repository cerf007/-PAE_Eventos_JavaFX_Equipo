package ni.edu.uam.pae_eventos_javafx_equipo.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Lote;

public class LoteDAO {
    private static LoteDAO instancia;
    private final ObservableList<Lote> lotes;

    private LoteDAO() {
        lotes = FXCollections.observableArrayList();
    }

    public static LoteDAO getInstancia() {
        if (instancia == null) {
            instancia = new LoteDAO();
        }
        return instancia;
    }

    public ObservableList<Lote> obtenerTodos() {
        return lotes;
    }

    public void registrarLote(Lote lote) {
        lotes.add(lote);
    }

    public void eliminarLote(Lote lote) {
        lotes.remove(lote);
    }
}