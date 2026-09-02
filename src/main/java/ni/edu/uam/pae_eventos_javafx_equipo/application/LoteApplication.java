package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoteApplication.class.getResource("/ni/edu/uam/pae_eventos_javafx_equipo/recepcion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Cooperativa Agro - Registro de Lotes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}