import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProveedoresFormInputData extends JFrame {

    private JTextField nombre;
    private JTextField direccion;
    private JTextField cp;
    private JTextField poblacion;
    private JTextField provincia;
    private JTextField pais;
    private JTextField CIF;
    private JTextField telefono;
    private JTextField email;
    private JTextField IBAN;
    private JTextField riesgo;
    private JTextField descuento;
    private JTextField observaciones;

    private JButton submitButton;


    public ProveedoresFormInputData() {

        setTitle("Formulario de Proveedores");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //setLayout(new GridLayout(15, 4));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.weightx=2;

        Utilidades u = new Utilidades();

        // Create labels and text fields
        JLabel nombreLabel = new JLabel("Nombre");
        nombre = new JTextField();

        JLabel paisLabel = new JLabel("Pais");
        pais = new JTextField();

        JLabel direccionLabel = new JLabel("Direccion");
        direccion = new JTextField();

        JLabel cpLabel = new JLabel("CP");
        cp = new JTextField();
        u.CheckInputListener(cp);

        JLabel poblacionLabel = new JLabel("Poblacion");
        poblacion = new JTextField();

        JLabel provinciaLabel = new JLabel("Provincia");
        provincia = new JTextField();

        JLabel CIFLabel = new JLabel("CIF");
        CIF = new JTextField();

        JLabel telefonoLabel = new JLabel("Telefono");
        telefono = new JTextField();
        u.CheckInputListener(telefono);

        JLabel emailLabel = new JLabel("Email");
        email = new JTextField();

        JLabel ibanLabel = new JLabel("IBAN:");
        IBAN = new JTextField();

        JLabel riesgoLabel = new JLabel("Riesgo");
        riesgo = new JTextField();
        u.CheckInputListener(riesgo);

        JLabel descuentoLabel = new JLabel("Descuento");
        descuento = new JTextField();
        u.CheckInputListener(descuento);

        JLabel observacionesLabel = new JLabel("Observaciones");
        observaciones = new JTextField();


        submitButton = new JButton("Crear Proveedor");



        //listener en el JFrame
        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(u.areFieldsFilled(nombre,direccion,cp,poblacion,provincia,pais,CIF)){
                    insertProveedor();
                }

            }
        });


        // Add components to the frame

        gbc.gridx = 0; gbc.gridy = 0;
        add(nombreLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 20;
        nombre.setPreferredSize(new Dimension(250, 40));
        add(nombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(direccionLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        direccion.setPreferredSize(new Dimension(250, 40));
        add(direccion, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(cpLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        cp.setPreferredSize(new Dimension(250, 40));
        add(cp, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(poblacionLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        poblacion.setPreferredSize(new Dimension(250, 40));
        add (poblacion, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(provinciaLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        provincia.setPreferredSize(new Dimension(250, 40));
        add(provincia, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(paisLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        pais.setPreferredSize(new Dimension(250, 40));
        add(pais, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(CIFLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        CIF.setPreferredSize(new Dimension(250, 40));
        add(CIF, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(telefonoLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        telefono.setPreferredSize(new Dimension(250, 40));
        add(telefono, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 8;
        email.setPreferredSize(new Dimension(250, 40));
        add(email, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        add(ibanLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 9;
        IBAN.setPreferredSize(new Dimension(250, 40));
        add(IBAN, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        add(riesgoLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 10;
        riesgo.setPreferredSize(new Dimension(250, 40));
        add(riesgo, gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        add(descuentoLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 11;
        descuento.setPreferredSize(new Dimension(250, 40));
        add(descuento, gbc);

        gbc.gridx = 0; gbc.gridy = 12;
        add(observacionesLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 12;
        observaciones.setPreferredSize(new Dimension(250, 40));
        add(observaciones, gbc);


        add(new JLabel()); // Empty cell

        gbc.gridx = 2; gbc.gridy = 13; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        submitButton.setPreferredSize(new Dimension(150, 40));


        add(submitButton, gbc);


        Font defaultFont = UIManager.getFont("Label.font");
        System.out.println("Default Font: " + defaultFont);
        Font font = new Font("Lucida Grande", Font.PLAIN, 14);
        u.setFontRecursively(ProveedoresFormInputData.this, font);

        pack();
        this.setVisible(true);
    }




    private void insertProveedor() {



        String query = "INSERT INTO proveedores (nombreProveedor, direccion, cpProveedor, poblacionProveedor," +
                " provinciaProveedor, paisProveedor, cifProveedor, telProveedor, emailProveedor," +
                " ibanProveedor, riesgoProveedor, descuentoProveedor," +
                " observacionesProveedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        


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

            JOptionPane.showMessageDialog(this, "Provider added successfully!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding provider: " + ex.getMessage());
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
