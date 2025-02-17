import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;


public class Proveedores {

    private int idProveedor;
    private String nombreProveedor;
    private String direccionProveedor;
    private String cpProveedor;
    private String poblacionProveedor;
    private String provinciaProveedor;
    private String paisProveedor;
    private String cifProveedor;
    private String telProveedor;
    private String emailProveedor;
    private String ibanProveedor;
    private double riesgoProveedor;
    private double descuentoProveedor;
    private String observacionesProveedor;


    private DefaultTableModel model;


    public Proveedores(int idProveedor, String nombreProveedor, String direccionProveedor, String cpProveedor,
                       String poblacionProveedor, String provinciaProveedor, String paisProveedor,
                       String cifProveedor, String telProveedor, String emailProveedor, String ibanProveedor,
                       double riesgoProveedor, double descuentoProveedor, String observacionesProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccionProveedor = direccionProveedor;
        this.cpProveedor = cpProveedor;
        this.poblacionProveedor = poblacionProveedor;
        this.provinciaProveedor = provinciaProveedor;
        this.paisProveedor = paisProveedor;
        this.cifProveedor = cifProveedor;
        this.telProveedor = telProveedor;
        this.emailProveedor = emailProveedor;
        this.ibanProveedor = ibanProveedor;
        this.riesgoProveedor = riesgoProveedor;
        this.descuentoProveedor = descuentoProveedor;
        this.observacionesProveedor = observacionesProveedor;
    }

    public Proveedores() {

    }




    public DefaultTableModel obtener_proveedores() {

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {


            // Create a DefaultTableModel and JTable
            model = new DefaultTableModel();


            // Execute SELECT query
            String query = "SELECT * FROM proveedores"; // Replace with your table name
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