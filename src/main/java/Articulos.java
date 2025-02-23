import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Articulos {


    private int idArticulo;
    private String codigoArticulo;
    //private String codigoBarrasArticulo;
    private String descripcionArticulo;
    private double costeArticulo;
    private double margenComercialArticulo;
    private double pvpArticulo;
    private int proveedorArticulo;
    private int stockArticulo;
    private String observacionesArticulo;
    private int familiaArticulo;

    private DefaultTableModel model;


    public Articulos(int idArticulo, String codigoArticulo, String descripcionArticulo, double costeArticulo,
                     double margenComercialArticulo, double pvpArticulo, int proveedorArticulo,
                     int stockArticulo, String observacionesArticulo, int familiaArticulo) {

        //String codigoBarrasArticulo,
        this.idArticulo = idArticulo;
        this.codigoArticulo = codigoArticulo;
        //this.codigoBarrasArticulo = codigoBarrasArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.costeArticulo = costeArticulo;
        this.margenComercialArticulo = margenComercialArticulo;
        this.pvpArticulo = pvpArticulo;
        this.proveedorArticulo = proveedorArticulo;
        this.stockArticulo = stockArticulo;
        this.observacionesArticulo = observacionesArticulo;
        this.familiaArticulo = familiaArticulo;
    }

    public Articulos(){

    }


    public DefaultTableModel obtener_articulos() {

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {


            // Create a DefaultTableModel and JTable
            model = new DefaultTableModel(); //clientesArray, columnNames


            // Execute SELECT query
            String query = "SELECT * FROM articulos"; // Replace with your table name
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata to retrieve column names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Add column names to the model
            Vector<String> column_Names = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                column_Names.add(metaData.getColumnName(i));
            }
            model.setColumnIdentifiers(column_Names);



            // Add rows to the model
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }


    public List<Articulos> obtener_articulos_v1(){
        List<Articulos> lista_articulos = new ArrayList<>();
        String query = "SELECT idArticulo, codigoArticulo, descripcionArticulo, costeArticulo," +
                " margenComercialArticulo, pvpArticulo, proveedorArticulo, stockArticulo," +
                " observacionesArticulo, familiaArticulo FROM articulos";

        //codigoBarrasArticulo,



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
                        //codigoBarrasArticulo = resultSet.getString("codigoBarrasArticulo");
                        descripcionArticulo = resultSet.getString("codigonArticulo");
                        costeArticulo = resultSet.getDouble("costeArticulo");
                        margenComercialArticulo = resultSet.getDouble("margenComercialArticulo");
                        pvpArticulo = resultSet.getDouble("pvpArticulo");
                        proveedorArticulo = resultSet.getInt("proveedorArticulo");
                        stockArticulo = resultSet.getInt("stockArticulo");
                        observacionesArticulo = resultSet.getString("observacionesArticulo");
                        familiaArticulo = resultSet.getInt("familiaArticulo");

                        Articulos articulo = new Articulos(idArticulo, codigoArticulo, descripcionArticulo, costeArticulo,
                                margenComercialArticulo, pvpArticulo, proveedorArticulo, stockArticulo, observacionesArticulo,
                                familiaArticulo
                                //codigoBarrasArticulo,
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


