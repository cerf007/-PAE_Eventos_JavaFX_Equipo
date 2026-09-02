package ni.edu.uam.pae_eventos_javafx_equipo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;
import ni.edu.uam.pae_eventos_javafx_equipo.dao.InventarioDAO;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Inventario;

import java.io.IOException;

public class InventarioController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Inventario> tableInventario;
    @FXML private TableColumn<Inventario, String> colCodigo;
    @FXML private TableColumn<Inventario, String> colNombre;
    @FXML private TableColumn<Inventario, Double> colPrecio;
    @FXML private TableColumn<Inventario, Integer> colCantidad;

    private final InventarioDAO inventarioDAO = new InventarioDAO();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        actualizarTabla();
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        // Validar campos vacíos
        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Todos los campos son obligatorios.");
            return;
        }

        // Validar formato numérico y valores positivos
        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (precio < 0 || cantidad < 0) {
                mostrarAlerta(Alert.AlertType.WARNING, "Valores Inválidos", "El precio y la cantidad no pueden ser negativos.");
                return;
            }

            Inventario nuevoProducto = new Inventario(codigo, nombre, precio, cantidad);
            inventarioDAO.agregar(nuevoProducto);

            actualizarTabla();
            limpiarCampos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto registrado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Incorrecto", "El precio debe ser un número (decimal) y la cantidad un entero.");
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
        // Detectar si la tecla presionada fue ENTER
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBusqueda = txtBuscar.getText().trim();

            if (codigoBusqueda.isEmpty()) {
                actualizarTabla();
                return;
            }

            Inventario encontrado = inventarioDAO.buscarPorCodigo(codigoBusqueda);

            if (encontrado != null) {
                tableInventario.setItems(FXCollections.observableArrayList(encontrado));
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No Encontrado", "No se encontró ningún producto con el código: " + codigoBusqueda);
                actualizarTabla();
            }
        }
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
        tableInventario.setItems(FXCollections.observableArrayList(inventarioDAO.obtenerRegistros()));
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}