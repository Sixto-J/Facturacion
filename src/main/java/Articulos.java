import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Articulos {


    private int idArticulo;
    private String codigoArticulo;
    private String codigoBarrasArticulo;
    private String descripcionArticulo;
    private double costeArticulo;
    private double margenComercialArticulo;
    private double pvpArticulo;
    private int proveedorArticulo;
    private int stockArticulo;
    private String observacionesArticulo;
    private int familiaArticulo;

    public Articulos(int idArticulo, String codigoArticulo,
                     String codigoBarrasArticulo, String descripcionArticulo, double costeArticulo,
                     double margenComercialArticulo, double pvpArticulo, int proveedorArticulo,
                     int stockArticulo, String observacionesArticulo, int familiaArticulo) {
        this.idArticulo = idArticulo;
        this.codigoArticulo = codigoArticulo;
        this.codigoBarrasArticulo = codigoBarrasArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.costeArticulo = costeArticulo;
        this.margenComercialArticulo = margenComercialArticulo;
        this.pvpArticulo = pvpArticulo;
        this.proveedorArticulo = proveedorArticulo;
        this.stockArticulo = stockArticulo;
        this.observacionesArticulo = observacionesArticulo;
        this.familiaArticulo = familiaArticulo;
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
    public int getFamiliaArticulo() { return familiaArticulo;}
    public String getObservacionesArticulo() {
        return observacionesArticulo;
    }
    public int getProveedorArticulo() {
        return proveedorArticulo;
    }
    public double getPvpArticulo() {
        return pvpArticulo;
    }
    public int getStockArticulo() {
        return stockArticulo;
    }


    public void crearArticulo() {
    }
    public void modificarArticulo() {
    }
    public void verArticulo() {
    }



    public List<Articulos> obtenerArticulos() {
        List<Articulos> lista_articulos = new ArrayList<>();
        String query = "SELECT idArticulo, codigoArticulo, codigoBarrasArticulo, descripcionArticulo, costeArticulo," +
                " margenComercialArticulo, pvpArticulo, proveedorArticulo, stockArticulo," +
                " observacionesArticulo, familiaArticulo FROM articulos";


        try (ConexionDB conn = new ConexionDB()) {

            Connection connection = conn.getConnection();

            if (connection == null) {
                System.out.println("Failed to establish a database connection.");
                return lista_articulos; // Return empty list if connection failed
            }


            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (!resultSet.isBeforeFirst()) { // Check if the ResultSet is empty
                    System.out.println("No rows found in the ResultSet.");
                } else {


                    while (resultSet.next()) {
                        idArticulo = resultSet.getInt("idArticulo");
                        codigoArticulo = resultSet.getString("codigoArticulo");
                        codigoBarrasArticulo = resultSet.getString("codigoBarrasArticulo");
                        descripcionArticulo = resultSet.getString("codigonArticulo");
                        costeArticulo = resultSet.getDouble("costeArticulo");
                        margenComercialArticulo = resultSet.getDouble("margenComercialArticulo");
                        pvpArticulo = resultSet.getDouble("pvpArticulo");
                        proveedorArticulo = resultSet.getInt("proveedorArticulo");
                        stockArticulo = resultSet.getInt("stockArticulo");
                        observacionesArticulo = resultSet.getString("observacionesArticulo");
                        familiaArticulo = resultSet.getInt("familiaArticulo");

                        Articulos articulo = new Articulos(idArticulo, codigoArticulo, codigoBarrasArticulo,
                                descripcionArticulo,
                                costeArticulo, margenComercialArticulo, pvpArticulo, proveedorArticulo, stockArticulo, observacionesArticulo,
                                familiaArticulo
                        );
                        lista_articulos.add(articulo);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista_articulos;
        }


    }


}


