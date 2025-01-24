public class Articulo(){


    private String nombreArticulo;
    private String descripcionArticulo;
    private int idArticulo
    private String codigoArticulo
    private String codigoBarrasArticulo
    private String descripcionArticulo
    private double costeArticulo
    private double margenComercialArticulo
    private double pvpArticulo
    private double stockArticulo
    private String observacionesArticulo
    private int proveedorArticulo

    public Articulo(String nombreArticulo, String descripcionArticulo, int idArticulo, String codigoArticulo, String codigoBarrasArticulo, String descripcionArticulo, double costeArticulo, double margenComercialArticulo, double pvpArticulo, double stockArticulo, String observacionesArticulo, int proveedorArticulo) {
        this.nombreArticulo = nombreArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.idArticulo = idArticulo;
        this.codigoArticulo = codigoArticulo;
        this.codigoBarrasArticulo = codigoBarrasArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.costeArticulo = costeArticulo;
        this.margenComercialArticulo = margenComercialArticulo;
        this.pvpArticulo = pvpArticulo;
        this.stockArticulo = stockArticulo;
        this.observacionesArticulo = observacionesArticulo;
        this.proveedorArticulo = proveedorArticulo;
    }


    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getCodigoBarrasArticulo() {
        return codigoBarrasArticulo;
    }

    public double getCosteArticulo() {
        return costeArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public double getMargenComercialArticulo() {
        return margenComercialArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public String getObservacionesArticulo() {
        return observacionesArticulo;
    }

    public int getProveedorArticulo() {
        return proveedorArticulo;
    }

    public double getPvpArticulo() {
        return pvpArticulo;
    }

    public double getStockArticulo() {
        return stockArticulo;
    }
}


public void crearArticulo(){

}
public void modificarArticulo(){

}
public void verArticulo(){

}