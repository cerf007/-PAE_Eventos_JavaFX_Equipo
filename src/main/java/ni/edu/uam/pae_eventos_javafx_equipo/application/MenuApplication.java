package ni.edu.uam.pae_eventos_javafx_equipo.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("/ni/edu/uam/pae_eventos_javafx_equipo/menu-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Menú Principal - Retos en Pareja");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

}
