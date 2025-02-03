import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientesFormInputData extends JFrame {

    private JTextField nombre;
    private JTextField pais;
    private JTextField direccion;
    private JTextField cp;
    private JTextField poblacion;
    private JTextField provincia;
    private JTextField CIF;
    private JTextField telefono;
    private JTextField email;
    private JTextField IBAN;
    private JTextField riesgo;
    private JTextField descuento;
    private JTextField observaciones;

    private JButton submitButton;


    public ClientesFormInputData() {

        setTitle("Formulario de Clientes");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(15, 4));


        // Create labels and text fields
        JLabel nombreLabel = new JLabel("Nombre");
        nombre = new JTextField();
        JLabel paisLabel = new JLabel("Pais");
        pais = new JTextField();
        JLabel direccionLabel = new JLabel("Direccion");
        direccion = new JTextField();
        JLabel cpLabel = new JLabel("CP");
        cp = new JTextField();
        JLabel poblacionLabel = new JLabel("Poblacion");
        poblacion = new JTextField();
        JLabel provinciaLabel = new JLabel("Provincia");
        provincia = new JTextField();
        JLabel CIFLabel = new JLabel("CIF");
        CIF = new JTextField();
        JLabel telefonoLabel = new JLabel("Telefono");
        telefono = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        email = new JTextField();
        JLabel ibanLabel = new JLabel("IBAN:");
        IBAN = new JTextField();
        JLabel riesgoLabel = new JLabel("Riesgo");
        riesgo = new JTextField();
        JLabel descuentoLabel = new JLabel("Descuento");
        descuento = new JTextField();
        JLabel observacionesLabel = new JLabel("Observaciones");
        observaciones = new JTextField();


        submitButton = new JButton("Submit");

        // Add components to the frame
        add(nombreLabel);
        add(nombre);
        add(paisLabel);
        add(pais);
        add(direccionLabel);
        add(direccion);
        add(cpLabel);
        add (cp);
        add(poblacionLabel);
        add(poblacion);
        add(provinciaLabel);
        add(provincia);
        add(CIFLabel);
        add(CIF);
        add(telefonoLabel);
        add(telefono);
        add(emailLabel);
        add(email);
        add(ibanLabel);
        add(IBAN);
        add(riesgoLabel);
        add(riesgo);
        add(descuentoLabel);
        add(descuento);
        add(observacionesLabel);
        add(observaciones);

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



        String query = "INSERT INTO clientes (nombreCliente, direccionCliente, cpCliente, poblacionCliente," +
                " provinciaCliente, paisCliente, cifCliente, telCliente, emailCliente," +
                " ibanCliente, riesgoCliente, descuentoCliente," +
                " observacionesCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombre.getText());
            pstmt.setString(2, pais.getText());
            pstmt.setString(3, direccion.getText());
            pstmt.setString(4, cp.getText());
            pstmt.setString(5, poblacion.getText());
            pstmt.setString(6, provincia.getText());
            pstmt.setString(7, CIF.getText());
            pstmt.setString(8, telefono.getText());
            pstmt.setString(9, email.getText());
            pstmt.setString(10, IBAN.getText());
            pstmt.setDouble(11, Double.valueOf(riesgo.getText()));
            pstmt.setDouble(12, Double.valueOf(descuento.getText()));
            pstmt.setString(13, observaciones.getText());


            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer: " + ex.getMessage());
        }
    }

    private void clearFields() {
        nombre.setText("");
        pais.setText("");
        direccion.setText("");
        cp.setText("");
        poblacion.setText("");
        provincia.setText("");
        CIF.setText("");
        telefono.setText("");
        email.setText("");
        IBAN.setText("");
        riesgo.setText("");
        descuento.setText("");
        observaciones.setText("");
    }

}