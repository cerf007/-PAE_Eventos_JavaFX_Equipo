package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;

import java.io.IOException;

public class LoteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navegador.cambiarVentana(stage, "recepcion-view.fxml", "Cooperativa Agro - Registro de Lotes");
    }
}