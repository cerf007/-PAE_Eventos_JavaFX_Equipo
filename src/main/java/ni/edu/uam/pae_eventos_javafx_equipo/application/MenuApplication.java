package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;

import java.io.IOException;


public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navegador.cambiarVentana(stage, "menu-view.fxml", "Menú Principal - Retos en Pareja");
    }

}
