import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ArticulosFormInputData extends JFrame {

    private JTextField codigoArticulo;
    private JTextField codigoBarrasArticulo;
    private JTextField descripcionArticulo;
    private JTextField costeArticulo;
    private JTextField margenComercialArticulo;
    private JTextField pvpArticulo;
    private JComboBox proveedorArticulo;
    private JTextField stockArticulo;
    private JTextField observacionesArticulo;
    private JComboBox familiaArticulo;


    private JButton submitButton;


    public ArticulosFormInputData() {

        setTitle("Formulario de Articulos");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(5, 25, 5, 25);
        gbc.weightx=2;


        // Create labels and text fields
        JLabel codigoArticuloLabel = new JLabel("codigoArticulo");
        codigoArticulo = new JTextField();
        JLabel codigoBarrasArticuloLabel = new JLabel("codigoBarrasArticulo");
        codigoBarrasArticulo = new JTextField();
        JLabel descripcionArticuloLabel = new JLabel("descripcionArticulo");
        descripcionArticulo = new JTextField();
        JLabel costeArticuloLabel = new JLabel("costeArticulo");
        costeArticulo = new JTextField();
        JLabel margenComercialArticuloLabel = new JLabel("margenComercialArticulo");
        margenComercialArticulo = new JTextField();
        JLabel pvpArticuloLabel = new JLabel("pvpArticulo");
        pvpArticulo = new JTextField();




        JLabel proveedorArticuloLabel = new JLabel("proveedorArticulo");
        JLabel familiaArticuloLabel = new JLabel("familiaArticulo");

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            // Execute SELECT query
            String query_proveedor = "SELECT idProveedor, nombreProveedor FROM proveedores"; // Replace with your table name
            ResultSet resultSet_proveedor = statement.executeQuery(query_proveedor);

            ArrayList<String> proveedores = new ArrayList<>();
            ArrayList<String> nombre_proveedores = new ArrayList<>();


            while(resultSet_proveedor.next()){
                proveedores.add(String.valueOf(resultSet_proveedor.getInt("idProveedor")));
                nombre_proveedores.add(resultSet_proveedor.getString("nombreProveedor"));

            }

            DefaultComboBoxModel<String> model_proveedores = new DefaultComboBoxModel<>(proveedores.toArray(new String[0]));
            proveedorArticulo = new JComboBox<>(model_proveedores);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
        String query_familia = "SELECT idFamiliaArticulos FROM familiaArticulos";
        ResultSet resultSet_familia = statement.executeQuery(query_familia);
        ArrayList<String> familia_articulo = new ArrayList<>();
        while(resultSet_familia.next()){
            familia_articulo.add(String.valueOf(resultSet_familia.getInt("idProveedor")));
        }
        DefaultComboBoxModel<String> model_familia = new DefaultComboBoxModel<>(familia_articulo.toArray(new String[0]));
        familiaArticulo = new JComboBox<>(model_familia);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        JLabel stockArticuloLabel = new JLabel("stockArticulo");
        stockArticulo = new JTextField();
        JLabel observacionesArticuloLabel = new JLabel("observacionesArticulo");
        observacionesArticulo = new JTextField();



        submitButton = new JButton("Crear artículo");

        // Add components to the frame

        gbc.gridx = 0; gbc.gridy = 0;
        add(codigoArticuloLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 20;
        codigoArticulo.setPreferredSize(new Dimension(250, 40));
        add(codigoArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(codigoBarrasArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 20;
        codigoBarrasArticulo.setPreferredSize(new Dimension(250, 40));
        add(codigoBarrasArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(descripcionArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 20;
        descripcionArticulo.setPreferredSize(new Dimension(250, 40));
        add(descripcionArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(costeArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 20;
        costeArticulo.setPreferredSize(new Dimension(250, 40));
        add(costeArticulo, gbc);


        gbc.gridx = 0; gbc.gridy = 4;
        add(margenComercialArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 20;
        margenComercialArticulo.setPreferredSize(new Dimension(250, 40));
        add(margenComercialArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(pvpArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 20;
        pvpArticulo.setPreferredSize(new Dimension(250, 40));
        add(pvpArticulo, gbc);


        gbc.gridx = 0; gbc.gridy = 6;
        add(proveedorArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 6; gbc.gridwidth = 20;
        proveedorArticulo.setPreferredSize(new Dimension(250, 40));
        add(proveedorArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(stockArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 7; gbc.gridwidth = 20;
        stockArticulo.setPreferredSize(new Dimension(250, 40));
        add(stockArticulo, gbc);


        gbc.gridx = 0; gbc.gridy = 8;
        add(observacionesArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 8; gbc.gridwidth = 20;
        observacionesArticulo.setPreferredSize(new Dimension(250, 40));
        add(observacionesArticulo, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        add(familiaArticuloLabel,gbc);
        gbc.gridx = 1; gbc.gridy = 9; gbc.gridwidth = 20;
        familiaArticulo.setPreferredSize(new Dimension(250, 40));
        add(familiaArticulo, gbc);

        add(new JLabel()); // Empty cell

        gbc.gridx = 2; gbc.gridy = 10; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        submitButton.setPreferredSize(new Dimension(150, 40));
        add(submitButton, gbc);



        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertArticulo();
            }
        });

        this.setVisible(true);
    }




    private void insertArticulo() {

        String query = "INSERT INTO codigoArticulo, codigoBarrasArticulo, descripcionArticulo, costeArticulo, margenComercialArticulo," +
                " pvpArticulo, proveedorArticulo, stockArticulo, observacionesArticulo, familiaArticulo," +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, codigoArticulo.getText());
            pstmt.setString(2, codigoBarrasArticulo.getText());
            pstmt.setString(3, descripcionArticulo.getText());
            pstmt.setDouble(4, Double.valueOf(costeArticulo.getText()));
            pstmt.setDouble(5, Double.valueOf(margenComercialArticulo.getText()));
            pstmt.setDouble(6, Double.valueOf(pvpArticulo.getText()));
            pstmt.setInt(7, Integer.valueOf(String.valueOf(proveedorArticulo.getSelectedItem())));
            pstmt.setInt(8, Integer.valueOf(stockArticulo.getText()));
            pstmt.setString(9, observacionesArticulo.getText());
            pstmt.setInt(10, Integer.valueOf(String.valueOf(familiaArticulo.getSelectedItem())));


            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Article added successfully!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding article: " + ex.getMessage());
        }
    }

    private void clearFields() {
        codigoArticulo.setText("");
        codigoBarrasArticulo.setText("");
        descripcionArticulo.setText("");
        costeArticulo.setText("");
        margenComercialArticulo.setText("");
        pvpArticulo.setText("");
        proveedorArticulo.setSelectedIndex(0);
        stockArticulo.setText("");
        observacionesArticulo.setText("");
        familiaArticulo.setSelectedIndex(0);
    }

}