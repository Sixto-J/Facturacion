import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Clientes{
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

    public Clientes(int id, String nombreCliente, String direccionCliente, String cpCliente, String poblacionCliente, String provinciaCliente, String paisCliente, String cifCliente, String telCliente, String emailCliente, String ibanCliente, double riesgoCliente, double descuentoCliente, String observacionesCliente) {
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
    public String getDireccionCliente() {
        return direccionCliente;
    }
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
    public String getCpCliente() {
        return cpCliente;
    }
    public void setCpCliente(String cpCliente) {
        this.cpCliente = cpCliente;
    }
    public String getPoblacionCliente() {
        return poblacionCliente;
    }
    public void setPoblacionCliente(String poblacionCliente) {
        this.poblacionCliente = poblacionCliente;
    }
    public String getProvinciaCliente() {
        return provinciaCliente;
    }
    public void setProvinciaCliente(String provinciaCliente) {
        this.provinciaCliente = provinciaCliente;
    }


    public void CrearCliente(){

    }

    public void ModificarCliente(){

    }
    public void VerCliente(){

    }








    public List<Clientes> obtenerClientes() {
        List<Clientes> clientes = new ArrayList<>();
        String query = "SELECT nombre, pais FROM clientes"; // Cambia 'clientes' por el nombre de tu tabla
        try (
             Connection connection = ConexionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String pais = resultSet.getString("pais");
                Clientes cliente = new Clientes(nombre, pais);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }




}