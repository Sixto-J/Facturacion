import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Facturas {


    private int idFacturaCliente;
    private int numeroFacturaCliente;
    private Date fechaFacturaCliente;
    private int idClienteFactura;
    private double baseImponibleFacturaCliente;
    private double ivaFacturaCliente;
    private double totalFacturaCliente;
    private String hashFacturaCiente;
    private String qrFacturaCliente;
    private boolean cobradaFactura;
    private int formaCobroFactura;
    private Date fechaCobroFactura;
    private String observacionesFacturaClientes;

    private DefaultTableModel model;


    public Facturas(double baseImponibleFacturaCliente, boolean cobradaFactura,
                    Date fechaCobroFactura, Date fechaFacturaCliente, int formaCobroFactura,
                    String hashFacturaCiente, int idClienteFactura, int idFacturaCliente,
                    double ivaFacturaCliente, int numeroFacturaCliente, String observacionesFacturaClientes,
                    String qrFacturaCliente, double totalFacturaCliente) {
        this.baseImponibleFacturaCliente = baseImponibleFacturaCliente;
        this.cobradaFactura = cobradaFactura;
        this.fechaCobroFactura = fechaCobroFactura;
        this.fechaFacturaCliente = fechaFacturaCliente;
        this.formaCobroFactura = formaCobroFactura;
        this.hashFacturaCiente = hashFacturaCiente;
        this.idClienteFactura = idClienteFactura;
        this.idFacturaCliente = idFacturaCliente;
        this.ivaFacturaCliente = ivaFacturaCliente;
        this.numeroFacturaCliente = numeroFacturaCliente;
        this.observacionesFacturaClientes = observacionesFacturaClientes;
        this.qrFacturaCliente = qrFacturaCliente;
        this.totalFacturaCliente = totalFacturaCliente;
    }

    public Facturas(){

    }


    public boolean isCobradaFactura() {
        return cobradaFactura;
    }
    public void crearFacturas() {} // calculo IVA y total
    public void verFacturas() {}



    public DefaultTableModel obtener_facturas(){

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            model = new DefaultTableModel();

            // Execute SELECT query
            String query = "SELECT * FROM facturasClientes"; // Replace with your table name
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