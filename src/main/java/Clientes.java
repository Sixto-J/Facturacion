
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
        this.provinciaCliente = provinciaCliente
    }


    public void CrearCliente(){

    }

    public void ModificarCliente(){

    }
    public void VerCliente(){

    }

}