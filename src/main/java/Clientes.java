import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Clientes {
    private int idCliente;
    private String nombreCliente;
    private String direccionCliente;
    private String cpCliente;
    private String poblacionCliente;
    private String provinciaCliente;
    private String paisCliente;
    private String cifCliente;
    private String telCliente;
    private String emailCliente;
    private String ibanCliente;
    private double riesgoCliente;
    private double descuentoCliente;
    private String observacionesCliente;
    private DefaultTableModel model;

    public Clientes(int id, String nombreCliente, String direccionCliente, String cpCliente,
                    String poblacionCliente, String provinciaCliente, String paisCliente, String cifCliente,
                    String telCliente, String emailCliente, String ibanCliente, double riesgoCliente,
                    double descuentoCliente, String observacionesCliente) {
        this.idCliente = id;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.cpCliente = cpCliente;
        this.poblacionCliente = poblacionCliente;
        this.provinciaCliente = provinciaCliente;
        this.paisCliente = paisCliente;
        this.cifCliente = cifCliente;
        this.telCliente = telCliente;
        this.emailCliente = emailCliente;
        this.ibanCliente = ibanCliente;
        this.riesgoCliente = riesgoCliente;
        this.descuentoCliente = descuentoCliente;
        this.observacionesCliente = observacionesCliente;
    }

    public Clientes() {

    }

    public void CrearCliente() {}
    public void ModificarCliente() {}
    public void VerCliente() {}


    // Override toString() method
    @Override
    public String toString() {
        return "Client{" +
                "name='" + nombreCliente + '\'' +
                ", idr='" + idCliente + '\'' +
                ", address='" + direccionCliente + '\'' +
                '}';
    }


    public DefaultTableModel obtener_clientes() {

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {


            // Create a DefaultTableModel and JTable
            model = new DefaultTableModel(); //clientesArray, columnNames


            // Execute SELECT query
            String query = "SELECT * FROM clientes"; // Replace with your table name
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

   /*   public List<Clientes> obtener_clientes_v1() {
        List<Clientes> lista_clientes = new ArrayList<>();

        try (ConexionDB cdb = new ConexionDB()) {
            Connection connection = cdb.getConnection();

            if (connection == null) {
                System.out.println("Failed to establish a database connection.");
                return lista_clientes; // Return empty list if connection failed
            }

            String query = "INSERT INTO clientes (nombreCliente, paisCliente, direccionCliente, cpCliente," +
                    " poblacionCliente, provinciaCliente," +
                    "cifCliente, telCliente, emailCliente, ibanCliente, riesgoCliente, descuentoCliente," +
                    "observacionesCliente) VALUES ()";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (!resultSet.isBeforeFirst()) { // Check if the ResultSet is empty
                    System.out.println("No rows found in the ResultSet.");
                } else {


                    while (resultSet.next()) {

                        System.out.println("Processing row: " + resultSet.getRow());

                        idCliente = resultSet.getInt("idCliente");
                        nombreCliente = resultSet.getString("nombreCliente");
                        paisCliente = resultSet.getString("paisCliente");
                        direccionCliente = resultSet.getString("direccionCliente");
                        cpCliente = resultSet.getString("cpCliente");
                        poblacionCliente = resultSet.getString("poblacionCliente");
                        provinciaCliente = resultSet.getString("provinciaCliente");
                        cifCliente = resultSet.getString("cifCliente");
                        telCliente = resultSet.getString("telCliente");
                        emailCliente = resultSet.getString("emailCliente");
                        ibanCliente = resultSet.getString("ibanCliente");
                        riesgoCliente = resultSet.getDouble("riesgoCliente");
                        descuentoCliente = resultSet.getDouble("descuentoCliente");
                        observacionesCliente = resultSet.getString("observacionesCliente");

                        Clientes cliente = new Clientes(idCliente, nombreCliente, paisCliente, direccionCliente, cpCliente, poblacionCliente, provinciaCliente,
                                cifCliente, telCliente, emailCliente, ibanCliente, riesgoCliente, descuentoCliente, observacionesCliente);

                        lista_clientes.add(cliente);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return lista_clientes;


    }*/


}


