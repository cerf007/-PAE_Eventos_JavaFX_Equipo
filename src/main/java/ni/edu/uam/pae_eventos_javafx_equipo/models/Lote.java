package ni.edu.uam.pae_eventos_javafx_equipo.models;


public class Lote {
    private String codigo;
    private String productor;
    private String producto;
    private double cantidad;

    public Lote(String codigo, String productor, String producto, double cantidad) {
        this.codigo = codigo;
        this.productor = productor;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getProductor() { return productor; }
    public void setProductor(String productor) { this.productor = productor; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
}