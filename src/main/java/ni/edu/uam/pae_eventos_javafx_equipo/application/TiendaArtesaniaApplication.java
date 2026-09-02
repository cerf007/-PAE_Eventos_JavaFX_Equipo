package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;

import java.io.IOException;

public class TiendaArtesaniaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navegador.cambiarVentana(stage, "artesania-view.fxml", "Reto 3: Tienda de Artesanías");
    }

    public static void main(String[] args) {
        launch(args);
    }
}