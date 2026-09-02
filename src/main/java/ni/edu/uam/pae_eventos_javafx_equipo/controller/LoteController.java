package ni.edu.uam.pae_eventos_javafx_equipo.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import ni.edu.uam.pae_eventos_javafx_equipo.Navegador;
import ni.edu.uam.pae_eventos_javafx_equipo.dao.LoteDAO;
import ni.edu.uam.pae_eventos_javafx_equipo.models.Lote;


public class LoteController {
    @FXML private HBox rootPane;


    @FXML private TextField txtCodigo;
    @FXML private TextField txtProductor;
    @FXML private ComboBox<String> cbProducto;
    @FXML private TextField txtCantidad;
    @FXML private Label lblMensaje;


    @FXML private TableView<Lote> tablaLotes;
    @FXML private TableColumn<Lote, String> colCodigo;
    @FXML private TableColumn<Lote, String> colProductor;
    @FXML private TableColumn<Lote, String> colProducto;
    @FXML private TableColumn<Lote, Double> colCantidad;

    private LoteDAO dao;

    @FXML
    public void initialize() {

        FadeTransition ft = new FadeTransition(Duration.millis(800), rootPane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        dao = LoteDAO.getInstancia();


        cbProducto.getItems().addAll("Arabica Typica", "Arabica Bourbon", "Arabica Geisha");
        cbProducto.setValue("Arabica Typica");


        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));


        tablaLotes.setItems(dao.obtenerTodos());


        configurarEventosTabla();
    }

    @FXML
    private void guardarLote() {
        try {
            String codigo = txtCodigo.getText();
            String productor = txtProductor.getText();
            String producto = cbProducto.getValue();

            if (codigo.isEmpty() || productor.isEmpty()) {
                throw new Exception("Los campos Código y Productor son obligatorios.");
            }

            double cantidad = Double.parseDouble(txtCantidad.getText());


            Lote nuevo = new Lote(codigo, productor, producto, cantidad);
            dao.registrarLote(nuevo);


            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            lblMensaje.setText("Lote registrado con éxito");
            txtCodigo.clear(); txtProductor.clear(); txtCantidad.clear();

        } catch (NumberFormatException e) {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText("La cantidad debe ser numérica.");
        } catch (Exception e) {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void volverAlMenu(javafx.event.ActionEvent event) {
        try {
            Navegador.cambiarVentana(event, "menu-view.fxml", "Menú Principal");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarEventosTabla() {

        tablaLotes.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Detalles de Entrega");
                    info.setHeaderText("Información del Lote: " + seleccionado.getCodigo());
                    info.setContentText("Productor Responsable: " + seleccionado.getProductor() +
                            "\nTipo de Producto: " + seleccionado.getProducto() +
                            "\nVolumen Entregado: " + seleccionado.getCantidad() + " QQ");
                    info.showAndWait();
                }
            }
        });

        ContextMenu menuContextual = new ContextMenu();

        MenuItem menuEditar = new MenuItem("Modificar Lote");
        menuEditar.setOnAction(e -> {
            Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                txtCodigo.setText(seleccionado.getCodigo());
                txtProductor.setText(seleccionado.getProductor());
                cbProducto.setValue(seleccionado.getProducto());
                txtCantidad.setText(String.valueOf(seleccionado.getCantidad()));
                dao.eliminarLote(seleccionado);
                lblMensaje.setText("Modifique los datos y presione 'Registrar'.");
                lblMensaje.setStyle("-fx-text-fill: #F57C00;");
            }
        });

        MenuItem menuEliminar = new MenuItem("Eliminar Registro");
        menuEliminar.setOnAction(e -> {
            Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {

                Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
                confirmar.setTitle("Precaución");
                confirmar.setHeaderText("¿Eliminar definitivamente el lote " + seleccionado.getCodigo() + "?");
                confirmar.setContentText("Esta acción borrará el registro del sistema.");

                confirmar.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        dao.eliminarLote(seleccionado);
                        lblMensaje.setText("Lote eliminado.");
                        lblMensaje.setStyle("-fx-text-fill: red;");
                    }
                });
            }
        });

        menuContextual.getItems().addAll(menuEditar, menuEliminar);
        tablaLotes.setContextMenu(menuContextual);
    }
}