package ni.edu.uam.pae_eventos_javafx_equipo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;
import ni.edu.uam.pae_eventos_javafx_equipo.dao.InventarioDAO;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Inventario;

import java.io.IOException;

public class InventarioController {
    @FXML
    private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Inventario> tableInventario;
    @FXML private TableColumn<Inventario, String> colCodigo;
    @FXML private TableColumn<Inventario, String> colNombre;
    @FXML private TableColumn<Inventario, Double> colPrecio;
    @FXML private TableColumn<Inventario, Integer> colCantidad;

    private InventarioDAO inventarioDAO = new InventarioDAO();

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

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (precio < 0 || cantidad < 0) {
                mostrarAlerta(Alert.AlertType.ERROR, "Valor inválido", "El precio y la cantidad no pueden ser negativos.");
                return;
            }

            if (inventarioDAO.buscarPorCodigo(codigo) != null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Código duplicado", "Ya existe un producto registrado con este código.");
                return;
            }

            Inventario nuevo = new Inventario(codigo, nombre, precio, cantidad);
            inventarioDAO.agregar(nuevo);
            actualizarTabla();
            limpiarCampos();

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto guardado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de formato", "El precio debe ser numérico y la cantidad un número entero.");
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBuscado = txtBuscar.getText().trim();
            if (codigoBuscado.isEmpty()) {
                actualizarTabla();
                return;
            }

            Inventario encontrado = inventarioDAO.buscarPorCodigo(codigoBuscado);
            ObservableList<Inventario> resultadoUnico = FXCollections.observableArrayList();
            if (encontrado != null) {
                resultadoUnico.add(encontrado);
                tableInventario.setItems(resultadoUnico);
            } else {
                tableInventario.setItems(resultadoUnico);
                mostrarAlerta(Alert.AlertType.WARNING, "Sin resultados", "No se encontró ningún producto con el código: " + codigoBuscado);
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
        ObservableList<Inventario> lista = FXCollections.observableArrayList(inventarioDAO.obtenerRegistros());
        tableInventario.setItems(lista);
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        txtBuscar.clear();
    }

    private void mostrarAlerta(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
