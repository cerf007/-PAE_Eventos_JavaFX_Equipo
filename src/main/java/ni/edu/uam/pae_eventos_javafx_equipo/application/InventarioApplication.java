package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;

import java.io.IOException;

public class InventarioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navegador.cambiarVentana(stage, "inventario-view.fxml", "Reto 1: Inventario de Pulpería");
    }

    public static void main(String[] args) {
        launch(args);
    }
}