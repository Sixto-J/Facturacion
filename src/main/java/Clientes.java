import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Clientes {
    private int id;
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

    public Clientes(int id, String nombreCliente, String direccionCliente, String cpCliente,
                    String poblacionCliente, String provinciaCliente, String paisCliente, String cifCliente,
                    String telCliente, String emailCliente, String ibanCliente, double riesgoCliente,
                    double descuentoCliente, String observacionesCliente) {
        this.id = id;
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }




    public void CrearCliente() {
    }
    public void ModificarCliente() {
    }
    public void VerCliente() {
    }


    // Override toString() method
    @Override
    public String toString() {
        return "Client{" +
                "name='" + nombreCliente + '\'' +
                ", idr='" + id + '\'' +
                ", address='" + direccionCliente + '\'' +
                '}';
    }


    public List<Clientes> crearAñadirClientes() {
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

                        id = resultSet.getInt("id");
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

                        Clientes cliente = new Clientes(id, nombreCliente, paisCliente, direccionCliente, cpCliente, poblacionCliente, provinciaCliente,
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


    }


}


