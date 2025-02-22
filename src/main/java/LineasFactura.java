import javax.swing.table.DefaultTableModel;

public class LineasFactura {

    int numeroFacturaCliente;
    int idArticulo;
    String descripcionArticulo;
    String codigoArticulo;
    double pvpArticulo;
    double ivaArticulo;
    int idProveedorArticulo;
    String nombreProveedorArticulo;


    public LineasFactura(int numeroFacturaCliente, int idArticulo, String descripcionArticulo, String codigoArticulo, double pvpArticulo, double ivaArticulo, int idProveedorArticulo, String nombreProveedorArticulo) {
        this.numeroFacturaCliente = numeroFacturaCliente;
        this.idArticulo = idArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.codigoArticulo = codigoArticulo;
        this.pvpArticulo = pvpArticulo;
        this.ivaArticulo = ivaArticulo;
        this.idProveedorArticulo = idProveedorArticulo;
        this.nombreProveedorArticulo = nombreProveedorArticulo;
    }



    public int getIdArticulo() {
        return idArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public double getPvpArticulo() {
        return pvpArticulo;
    }

    public double getIvaArticulo() {
        return ivaArticulo;
    }

    public int getIdProveedorArticulo() {
        return idProveedorArticulo;
    }

    public String getNombreProveedorArticulo() {
        return nombreProveedorArticulo;
    }






    private DefaultTableModel model;

    public DefaultTableModel obtener_lineas_factura() {

        return model;
    }


}
