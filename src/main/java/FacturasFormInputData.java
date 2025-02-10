import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


public class FacturasFormInputData extends JFrame {

    private JTextField numeroFacturaCliente;
    private JTextField fechaFacturaCliente;
    private JComboBox idClienteFactura;
    private JTextField baseImponibleFacturaCliente;
    private JComboBox ivaFacturaCliente;
    private JTextField totalFacturaCliente;
    private JTextField hashFacturaCliente;
    private JTextField qrFacturaCliente;
    private JComboBox cobradaFactura;
    private JTextField formaCobroFactura;
    private JTextField fechaCobroFactura;
    private JTextField observacionesFacturaCliente;

    private JButton submitButton;
    private JButton verLineaProductos;


    public FacturasFormInputData() {

        setTitle("Datos de la factura");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(5, 20, 5, 20); // Add padding around components




        // Create labels and text fields
        JLabel numeroFacturaClienteLabel = new JLabel("Número Factura de cliente");
        numeroFacturaCliente = new JTextField();
        //numeroFacturaCliente.setPreferredSize(new Dimension(100, 50));


        JLabel fechaFacturaClienteLabel = new JLabel("Fecha factura de cliente");
        fechaFacturaCliente = new JTextField();






        JLabel idClienteFacturaLabel = new JLabel("Id Cliente");
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
            // Execute SELECT query
            String query_cliente = "SELECT idCliente FROM clientes"; // Replace with your table name
            ResultSet resultSet_cliente = statement.executeQuery(query_cliente);

            ArrayList<String> idClientes = new ArrayList<>();

            while(resultSet_cliente.next()){
                idClientes.add(String.valueOf(resultSet_cliente.getInt("idCliente")));

            }
            DefaultComboBoxModel<String> model_clientes = new DefaultComboBoxModel<>(idClientes.toArray(new String[0]));
            idClienteFactura = new JComboBox<>(model_clientes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        JLabel baseImponibleFacturaClienteLabel = new JLabel("Base Imponible");
        baseImponibleFacturaCliente = new JTextField();
        baseImponibleFacturaCliente.setEnabled(false);



        JLabel ivaFacturaClienteLabel = new JLabel("IVA");
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
            // Execute SELECT query
            String query_iva = "SELECT idTipoIva FROM tiposIva"; // Replace with your table name
            ResultSet resultSet_iva = statement.executeQuery(query_iva);

            ArrayList<String> tiposIva = new ArrayList<>();

            while(resultSet_iva.next()){
                tiposIva.add(String.valueOf(resultSet_iva.getInt("idTipoIva")));

            }
            DefaultComboBoxModel<String> model_iva = new DefaultComboBoxModel<>(tiposIva.toArray(new String[0]));
            ivaFacturaCliente = new JComboBox<>(model_iva);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        JLabel totalFacturaClienteLabel = new JLabel("Total");
        totalFacturaCliente = new JTextField();
        totalFacturaCliente.setEnabled(false);

        Double total;
        if(totalFacturaCliente.isEnabled()){
            total = Double.valueOf(baseImponibleFacturaCliente.getText()) * Double.valueOf(String.valueOf(ivaFacturaCliente.getSelectedItem()));
            totalFacturaCliente.setText(String.valueOf(total));
        }



        JLabel hashFacturaClienteLabel = new JLabel("Hash");
        hashFacturaCliente = new JTextField();
        JLabel qrFacturaClienteLabel = new JLabel("QR");
        qrFacturaCliente = new JTextField();

        JLabel cobradaFacturaLabel = new JLabel("Cobrada");
        String[] cobradaFacturaOptions = {"yes", "no"};
        cobradaFactura = new JComboBox<>(cobradaFacturaOptions);

        JLabel formaCobroFacturaLabel = new JLabel("Forma de cobro");
        formaCobroFactura = new JTextField();
        JLabel fechaCobroFacturaLabel = new JLabel("Fecha de cobro");
        fechaCobroFactura = new JTextField();
        JLabel observacionesLabel = new JLabel("Observaciones");
        observacionesFacturaCliente = new JTextField();




        // Add components to the frame with gbc values
        gbc.gridx = 0; gbc.gridy = 0;
        add(numeroFacturaClienteLabel, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(numeroFacturaCliente, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(fechaFacturaClienteLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(fechaFacturaCliente, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        add(idClienteFacturaLabel, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        add(idClienteFactura, gbc);

        gbc.gridx = 3; gbc.gridy = 0;
        add(baseImponibleFacturaClienteLabel, gbc);
        gbc.gridx = 3; gbc.gridy = 1;
        add (baseImponibleFacturaCliente, gbc);

        gbc.gridx = 4; gbc.gridy = 0;
        add(ivaFacturaClienteLabel, gbc);
        gbc.gridx = 4; gbc.gridy = 1;
        add(ivaFacturaCliente, gbc);

        gbc.gridx = 5; gbc.gridy = 0;
        add(totalFacturaClienteLabel, gbc);
        gbc.gridx = 5; gbc.gridy = 1;
        add(totalFacturaCliente, gbc);

        gbc.gridx = 6; gbc.gridy = 0;
        add(hashFacturaClienteLabel, gbc);
        gbc.gridx = 6; gbc.gridy = 1;
        add(hashFacturaCliente, gbc);

        gbc.gridx = 7; gbc.gridy = 0;
        add(qrFacturaClienteLabel, gbc);
        gbc.gridx = 7; gbc.gridy = 1;
        add(qrFacturaCliente, gbc);

        gbc.gridx = 8; gbc.gridy = 0;
        add(cobradaFacturaLabel, gbc);
        gbc.gridx = 8; gbc.gridy = 1;
        add(cobradaFactura, gbc);

        gbc.gridx = 9; gbc.gridy = 0;
        add(formaCobroFacturaLabel, gbc);
        gbc.gridx = 9; gbc.gridy = 1;
        add(formaCobroFactura, gbc);

        gbc.gridx = 10; gbc.gridy = 0;
        add(fechaCobroFacturaLabel, gbc);
        gbc.gridx = 10; gbc.gridy = 1;
        add(fechaCobroFactura, gbc);

        gbc.gridx = 11; gbc.gridy = 0;
        add(observacionesLabel, gbc);
        gbc.gridx = 11; gbc.gridy = 1;
        add(observacionesFacturaCliente, gbc);

        //add(new JLabel());  Empty cell


        verLineaProductos = new JButton("Ver linea de productos");
        gbc.gridx = 12; gbc.gridy = 3; gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        add(verLineaProductos, gbc);

        submitButton = new JButton("Crear factura");
        gbc.gridx = 13; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        add(submitButton, gbc);

        //listener en el JFrame







        verLineaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String numFactura = numeroFacturaCliente.getText();
                new lineasFacturaFormInputData(numFactura);
            }
        });

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !Objects.equals(totalFacturaCliente.getText(), "") && totalFacturaCliente.isEnabled()){
                    insertFactura();
                }else{
                    //mensaje de error
                }
            }
        });




        this.setVisible(true);
    }


    private void insertFactura() {



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
            pstmt.setString(3, String.valueOf(idClienteFactura.getSelectedItem()));
            pstmt.setString(4, baseImponibleFacturaCliente.getText());
            pstmt.setString(5, String.valueOf(ivaFacturaCliente.getSelectedItem()));
            pstmt.setString(6, totalFacturaCliente.getText());
            pstmt.setString(7, hashFacturaCliente.getText());
            pstmt.setString(8, qrFacturaCliente.getText());
            pstmt.setString(9, String.valueOf(cobradaFactura.getSelectedItem()));
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
        idClienteFactura.setSelectedIndex(0);
        baseImponibleFacturaCliente.setText("");
        ivaFacturaCliente.setSelectedIndex(0);
        totalFacturaCliente.setText("");
        hashFacturaCliente.setText("");
        qrFacturaCliente.setText("");
        cobradaFactura.setSelectedIndex(0);
        formaCobroFactura.setText("");
        fechaCobroFactura.setText("");
        observacionesFacturaCliente.setText("");
    }

}