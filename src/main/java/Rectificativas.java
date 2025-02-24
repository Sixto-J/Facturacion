import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Rectificativas {


    private int idRectificativaCliente;
    private int numeroRectificativaCliente;
    private Date fechaRectificativaCliente;
    private int idClienteRectificativaCliente;
    private double baseImponibleRectificativaCliente;
    private double ivaRectificativaCliente;
    private double totalRectificativaCliente;
    private String observacionesRectificativaCliente;
    // private String hashRectificativaCliente;
    // private String qrRectificativaCliente;

    private DefaultTableModel model;


    public Rectificativas(double totalRectificativaCliente, String observacionesRectificativaCliente, int numeroRectificativaCliente, double ivaRectificativaCliente, int idRectificativaCliente, int idClienteRectificativaCliente, Date fechaRectificativaCliente, double baseImponibleRectificativaCliente) {
        // String qrRectificativaCliente, String hashRectificativaCliente,

        this.totalRectificativaCliente = totalRectificativaCliente;
        this.observacionesRectificativaCliente = observacionesRectificativaCliente;
        this.numeroRectificativaCliente = numeroRectificativaCliente;
        this.ivaRectificativaCliente = ivaRectificativaCliente;
        this.idRectificativaCliente = idRectificativaCliente;
        this.idClienteRectificativaCliente = idClienteRectificativaCliente;
        this.fechaRectificativaCliente = fechaRectificativaCliente;
        this.baseImponibleRectificativaCliente = baseImponibleRectificativaCliente;
        //  this.qrRectificativaCliente = qrRectificativaCliente;
        //  this.hashRectificativaCliente = hashRectificativaCliente;
    }

    public Rectificativas(){

    }


    public DefaultTableModel obtener_rectificativas(){
        //TODO

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            model = new DefaultTableModel();

            // Execute SELECT query
            String query = "SELECT * FROM rectificativasClientes"; // Replace with your table name
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
