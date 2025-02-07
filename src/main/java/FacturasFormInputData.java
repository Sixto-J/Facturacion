import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FacturasFormInputData extends JFrame {

    private JTextField numeroFacturaCliente;
    private JTextField fechaFacturaCliente;
    private JTextField idClienteFactura;
    private JTextField baseImponibleFacturaCliente;
    private JTextField ivaFacturaCliente;
    private JTextField totalFacturaCliente;
    private JTextField hashFacturaCliente;
    private JTextField qrFacturaCliente;
    private JTextField cobradaFactura;
    private JTextField formaCobroFactura;
    private JTextField fechaCobroFactura;
    private JTextField observacionesFacturaCliente;

    private JButton submitButton;


    public FacturasFormInputData() {

        setTitle("Datos de la factura");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(15, 4));


        // Create labels and text fields
        JLabel numeroFacturaClienteLabel = new JLabel("Número Factura de cliente");
        numeroFacturaCliente = new JTextField();
        JLabel fechaFacturaClienteLabel = new JLabel("Fecha factura de cliente");
        fechaFacturaCliente = new JTextField();
        JLabel idClienteFacturaLabel = new JLabel("Id Cliente");
        idClienteFactura = new JTextField();
        JLabel baseImponibleFacturaClienteLabel = new JLabel("Base Imponible");
        baseImponibleFacturaCliente = new JTextField();
        JLabel ivaFacturaClienteLabel = new JLabel("IVA");
        ivaFacturaCliente = new JTextField();
        JLabel totalFacturaClienteLabel = new JLabel("Total");
        totalFacturaCliente = new JTextField();
        JLabel hashFacturaClienteLabel = new JLabel("Hash");
        hashFacturaCliente = new JTextField();
        JLabel qrFacturaClienteLabel = new JLabel("QR");
        qrFacturaCliente = new JTextField();
        JLabel cobradaFacturaLabel = new JLabel("Cobrada");
        cobradaFactura = new JTextField();
        JLabel formaCobroFacturaLabel = new JLabel("Forma de cobro");
        formaCobroFactura = new JTextField();
        JLabel fechaCobroFacturaLabel = new JLabel("Fecha de cobro");
        fechaCobroFactura = new JTextField();
        JLabel observacionesLabel = new JLabel("Observaciones");
        observacionesFacturaCliente = new JTextField();


        submitButton = new JButton("Submit");

        // Add components to the frame
        add(numeroFacturaClienteLabel);
        add(numeroFacturaCliente);
        add(fechaFacturaClienteLabel);
        add(fechaFacturaCliente);
        add(idClienteFacturaLabel);
        add(idClienteFactura);
        add(baseImponibleFacturaClienteLabel);
        add (baseImponibleFacturaCliente);
        add(ivaFacturaClienteLabel);
        add(ivaFacturaCliente);
        add(totalFacturaClienteLabel);
        add(totalFacturaCliente);
        add(hashFacturaClienteLabel);
        add(hashFacturaCliente);
        add(qrFacturaClienteLabel);
        add(qrFacturaCliente);
        add(cobradaFacturaLabel);
        add(cobradaFactura);
        add(formaCobroFacturaLabel);
        add(formaCobroFactura);
        add(fechaCobroFacturaLabel);
        add(fechaCobroFactura);
        add(observacionesLabel);
        add(observacionesFacturaCliente);

        add(new JLabel()); // Empty cell
        add(submitButton);

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCustomer();
            }
        });

        this.setVisible(true);
    }


    private void insertCustomer() {



        String query = "INSERT INTO facturasClientes (numeroFacturaCliente, fechaFacturaCliente, idClienteFactura," +
                " baseImponibleFacturaCliente, ivaFacturaCliente, totalFacturaCliente, hasFacturaCliente," +
                " qrFacturaCliente, cobradaFactura, formaCobroFactura, fechaCobroFactura" +
                " observacionesFacturaCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, numeroFacturaCliente.getText());
            pstmt.setString(2, fechaFacturaCliente.getText());
            pstmt.setString(3, idClienteFactura.getText());
            pstmt.setString(4, baseImponibleFacturaCliente.getText());
            pstmt.setString(5, ivaFacturaCliente.getText());
            pstmt.setString(6, totalFacturaCliente.getText());
            pstmt.setString(7, hashFacturaCliente.getText());
            pstmt.setString(8, qrFacturaCliente.getText());
            pstmt.setString(9, cobradaFactura.getText());
            pstmt.setString(10, formaCobroFactura.getText());
            pstmt.setDouble(11, Double.valueOf(fechaCobroFactura.getText()));
            pstmt.setString(13, observacionesFacturaCliente.getText());


            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer: " + ex.getMessage());
        }
    }

    private void clearFields() {
        numeroFacturaCliente.setText("");
        fechaFacturaCliente.setText("");
        idClienteFactura.setText("");
        baseImponibleFacturaCliente.setText("");
        ivaFacturaCliente.setText("");
        totalFacturaCliente.setText("");
        hashFacturaCliente.setText("");
        qrFacturaCliente.setText("");
        cobradaFactura.setText("");
        formaCobroFactura.setText("");
        fechaCobroFactura.setText("");
        observacionesFacturaCliente.setText("");
    }

}