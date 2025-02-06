import java.util.Date;

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

    public double getTotalFacturaCliente() {
        return totalFacturaCliente;
    }

    public String getQrFacturaCliente() {
        return qrFacturaCliente;
    }

    public String getObservacionesFacturaClientes() {
        return observacionesFacturaClientes;
    }

    public int getNumeroFacturaCliente() {
        return numeroFacturaCliente;
    }

    public double getIvaFacturaCliente() {
        return ivaFacturaCliente;
    }

    public int getIdFacturaCliente() {
        return idFacturaCliente;
    }

    public int getIdClienteFactura() {
        return idClienteFactura;
    }

    public String getHashFacturaCiente() {
        return hashFacturaCiente;
    }

    public int getFormaCobroFactura() {
        return formaCobroFactura;
    }

    public Date getFechaFacturaCliente() {
        return fechaFacturaCliente;
    }

    public Date getFechaCobroFactura() {
        return fechaCobroFactura;
    }

    public boolean isCobradaFactura() {
        return cobradaFactura;
    }

    public double getBaseImponibleFacturaCliente() {
        return baseImponibleFacturaCliente;
    }


    public void crearFacturas() {

        // calculo IVA y total

    }

    public void verFacturas() {

    }


}



