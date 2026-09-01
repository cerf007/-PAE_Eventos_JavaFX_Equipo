package ni.edu.uam.pae_eventos_javafx_equipo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;

import java.io.IOException;

public class MenuController {
    @FXML
    private void irInventario(ActionEvent event) {
        try {
            Navegador.cambiarVentana(event, "inventario-view.fxml", "Reto 1: Inventario de Pulpería");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irRecepcion(ActionEvent event) {
        try {
            Navegador.cambiarVentana(event, "recepcion-view.fxml", "Reto 2: Recepción de Café");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irArtesania(ActionEvent event) {
        try {
            Navegador.cambiarVentana(event, "artesania-view.fxml", "Reto 3: Tienda de Artesanías");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
