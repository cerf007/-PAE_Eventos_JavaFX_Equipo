package ni.edu.uam.pae_eventos_javafx_equipo.models;

// Lote.java (Modelo)
public class Recepcion {
    private String codigo;
    private String productor;
    private String producto;
    private double cantidad;

    public Recepcion(String codigo, String productor, String producto, double cantidad) {
        this.codigo = codigo; this.productor = productor;
        this.producto = producto; this.cantidad = cantidad;
    }
    public String getCodigo() { return codigo; }
    public String getProductor() { return productor; }
    public String getProducto() { return producto; }
    public double getCantidad() { return cantidad; }
}
