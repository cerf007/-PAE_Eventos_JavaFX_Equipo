package ni.edu.uam.pae_eventos_javafx_equipo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;
import ni.edu.uam.pae_eventos_javafx_equipo.dao.ArtesaniaDAO;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Artesania;

import java.io.IOException;

public class ArtesaniaController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtImagenPath;

    @FXML private TableView<Artesania> tableArtesania;
    @FXML private TableColumn<Artesania, String> colCodigo;
    @FXML private TableColumn<Artesania, String> colNombre;
    @FXML private TableColumn<Artesania, Double> colPrecio;
    @FXML private TableColumn<Artesania, String> colCategoria;
    @FXML private TableColumn<Artesania, ImageView> colImagen;

    private ArtesaniaDAO artesaniaDAO = new ArtesaniaDAO();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        colImagen.setCellValueFactory(cellData -> {
            String path = cellData.getValue().getImagePath();
            ImageView imageView = new ImageView();
            try {
                if (path != null && !path.isEmpty()) {
                    Image img = new Image(path, 50, 50, true, true);
                    imageView.setImage(img);
                }
            } catch (Exception e) {

            }
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            return new javafx.beans.property.SimpleObjectProperty<>(imageView);
        });

        actualizarTabla();
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void accionGuardar(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String categoria = txtCategoria.getText().trim();
        String imagenPath = txtImagenPath.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || categoria.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Complete los campos obligatorios.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Artesania nueva = new Artesania(codigo, nombre, precio, categoria, imagenPath);
            artesaniaDAO.agregar(nueva);
            actualizarTabla();
            limpiarCampos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Artesanía guardada correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El precio debe ser un valor numérico.");
        }
    }

    @FXML
    private void accionBuscar(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Artesanía");
        dialog.setHeaderText("Búsqueda por Código");
        dialog.setContentText("Ingrese el código:");

        dialog.showAndWait().ifPresent(codigo -> {
            Artesania encontrada = artesaniaDAO.buscarPorCodigo(codigo);
            ObservableList<Artesania> resultado = FXCollections.observableArrayList();
            if (encontrada != null) {
                resultado.add(encontrada);
                tableArtesania.setItems(resultado);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No existe artesanía con ese código.");
            }
        });
    }

    @FXML
    private void menuCatalogo(ActionEvent event) {
        actualizarTabla();
    }

    @FXML
    private void menuVentas(ActionEvent event) {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Ventas", "Módulo de ventas de artesanías en desarrollo.");
    }

    @FXML
    private void menuAyuda(ActionEvent event) {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Ayuda", "Reto 3: Tienda de artesanías nicaragüenses.\nUtilice la barra de herramientas para registrar y buscar.");
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            Navegador.cambiarVentana(event, "menu-view.fxml", "Menú Principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarTabla() {
        tableArtesania.setItems(FXCollections.observableArrayList(artesaniaDAO.obtenerRegistros()));
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCategoria.clear();
        txtImagenPath.clear();
    }

    private void mostrarAlerta(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}