import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Articulos {


    private String nombreArticulo;
    private String descripcionArticulo;
    private int idArticulo;
    private String codigoArticulo;
    private String codigoBarrasArticulo;
    private double costeArticulo;
    private double margenComercialArticulo;
    private double pvpArticulo;
    private double stockArticulo;
    private String observacionesArticulo;
    private int proveedorArticulo;

    public Articulos(int idArticulo, String nombreArticulo, String descripcionArticulo, String codigoArticulo,
                     String codigoBarrasArticulo,double costeArticulo,
                     double margenComercialArticulo,double pvpArticulo,
                     double stockArticulo, String observacionesArticulo, int proveedorArticulo) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.codigoArticulo = codigoArticulo;
        this.codigoBarrasArticulo = codigoBarrasArticulo;
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

    public void crearArticulo(){

    }
    public void modificarArticulo(){

    }
    public void verArticulo(){

    }



    public List<Articulos> obtenerArticulos() {
        List<Articulos> articulos = new ArrayList<>();
        String query = "SELECT idArticulo, nombreArticulo, descripcionArticulo, codigoArticulo," +
                " codigoBarrasArticulo FROM articulos"; // Cambia 'clientes' por el nombre de tu tabla


        try (ConexionDB conn = new ConexionDB()) {

            Connection connection = conn.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                idArticulo= resultSet.getInt("idArticulo");
                nombreArticulo = resultSet.getString("nombreArticulo");
                descripcionArticulo = resultSet.getString("descripcionArticulo");
                codigoArticulo = resultSet.getString("codigoArticulo");
                codigoBarrasArticulo = resultSet.getString("codigoBarrasArticulo");
                costeArticulo = resultSet.getDouble("costeArticulo");
                margenComercialArticulo = resultSet.getDouble("margenComercialArticulo");
                pvpArticulo = resultSet.getDouble("pvpArticulo");
                stockArticulo = resultSet.getDouble("stockArticulo");
                observacionesArticulo = resultSet.getString("observacionesArticulo");
                proveedorArticulo = resultSet.getInt("proveedorArticulo");

                Articulos articulo = new Articulos(idArticulo,nombreArticulo,
                        descripcionArticulo, codigoArticulo,
                        codigoBarrasArticulo, costeArticulo, margenComercialArticulo, pvpArticulo, stockArticulo, observacionesArticulo,
                        proveedorArticulo);
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }




}


