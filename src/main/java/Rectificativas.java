import java.util.Date;

public class Rectificativas {


    private int idRectificativaCliente;
    private int numeroRectificativaCliente;
    private Date fechaRectificativaCliente;
    private int idClienteRectificativaCliente;
    private double baseImponibleRectificativaCliente;
    private double ivaRectificativaCliente;
    private double totalRectificativaCliente;
    private String hashRectificativaCliente;
    private String qrRectificativaCliente;
    private String observacionesRectificativaCliente;


    public Rectificativas(double totalRectificativaCliente, String qrRectificativaCliente, String observacionesRectificativaCliente, int numeroRectificativaCliente, double ivaRectificativaCliente, int idRectificativaCliente, int idClienteRectificativaCliente, String hashRectificativaCliente, Date fechaRectificativaCliente, double baseImponibleRectificativaCliente) {
        this.totalRectificativaCliente = totalRectificativaCliente;
        this.qrRectificativaCliente = qrRectificativaCliente;
        this.observacionesRectificativaCliente = observacionesRectificativaCliente;
        this.numeroRectificativaCliente = numeroRectificativaCliente;
        this.ivaRectificativaCliente = ivaRectificativaCliente;
        this.idRectificativaCliente = idRectificativaCliente;
        this.idClienteRectificativaCliente = idClienteRectificativaCliente;
        this.hashRectificativaCliente = hashRectificativaCliente;
        this.fechaRectificativaCliente = fechaRectificativaCliente;
        this.baseImponibleRectificativaCliente = baseImponibleRectificativaCliente;
    }

    public double getTotalRectificativaCliente() {
        return totalRectificativaCliente;
    }

    public String getQrRectificativaCliente() {
        return qrRectificativaCliente;
    }

    public String getObservacionesRectificativaCliente() {
        return observacionesRectificativaCliente;
    }

    public int getNumeroRectificativaCliente() {
        return numeroRectificativaCliente;
    }

    public double getIvaRectificativaCliente() {
        return ivaRectificativaCliente;
    }

    public int getIdRectificativaCliente() {
        return idRectificativaCliente;
    }

    public int getIdClienteRectificativaCliente() {
        return idClienteRectificativaCliente;
    }

    public String getHashRectificativaCliente() {
        return hashRectificativaCliente;
    }

    public Date getFechaRectificativaCliente() {
        return fechaRectificativaCliente;
    }

    public double getBaseImponibleRectificativaCliente() {
        return baseImponibleRectificativaCliente;
    }







    public void  crearRectificativas(){
        //TODO
    }


}
