package ni.edu.uam.pae_eventos_javafx_equipo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navegador {

    public static void cambiarVentana(Stage stage, String fxmlFile, String titulo) throws IOException {
        String ruta = fxmlFile.startsWith("/") ? fxmlFile : "/" + fxmlFile;
        if (Navegador.class.getResource(ruta) == null) {
            ruta = fxmlFile;
        }

        FXMLLoader loader = new FXMLLoader(Navegador.class.getResource(ruta));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public static void cambiarVentana(ActionEvent event, String fxmlFile, String titulo) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cambiarVentana(stage, fxmlFile, titulo);
    }
}