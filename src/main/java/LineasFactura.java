import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class LineasFactura {

    int numeroFacturaCliente;
    int idArticulo;
    String descripcionArticulo;
    String codigoArticulo;
    double pvpArticulo;
    double ivaArticulo;
    int idProveedorArticulo;
    String nombreProveedorArticulo;
    private DefaultTableModel model;

    public LineasFactura(){

    }


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


    public DefaultTableModel obtener_lineas_factura(int idFacturaCliente) {

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            model = new DefaultTableModel();

            // Execute SELECT query
            String query = "SELECT * FROM lineasFacturasClientes WHERE numeroFacturaCliente ="+idFacturaCliente+";"; // Replace with your table name
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


}
