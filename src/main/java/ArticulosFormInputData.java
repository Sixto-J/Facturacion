import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
        gbc.weightx = 2;


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
        JLabel nombreProveedorAuxLabel = new JLabel();
        JLabel nombreFamiliaAuxLabel = new JLabel();


        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {


            // Execute SELECT query
            String query_proveedor = "SELECT idProveedor, nombreProveedor FROM proveedores"; // Replace with your table name
            ResultSet resultSet_proveedor = statement.executeQuery(query_proveedor);

            ArrayList<String> proveedores = new ArrayList<>();
            ArrayList<String> nombre_proveedores = new ArrayList<>();


            while (resultSet_proveedor.next()) {
                proveedores.add(String.valueOf(resultSet_proveedor.getInt("idProveedor")));
                nombre_proveedores.add(resultSet_proveedor.getString("nombreProveedor"));

            }

            DefaultComboBoxModel<String> model_proveedores = new DefaultComboBoxModel<>(proveedores.toArray(new String[0]));
            proveedorArticulo = new JComboBox<>(model_proveedores);
            nombreProveedorAuxLabel.setText(nombre_proveedores.getFirst());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Mostramos un dato en JLabel a partir de un listener en un JComboBox que realiza un SELECT
        proveedorArticulo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { // Get the selected item
                    String selectedItem = (String) proveedorArticulo.getSelectedItem(); // Display the selected item
                    String query_codigo = "SELECT nombreProveedor FROM proveedores WHERE idProveedor = ?;";

                    try (ConexionDB cdb = new ConexionDB();
                         Connection connection = cdb.getConnection();
                         PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {

                        pstmt.setString(1, selectedItem);
                        ResultSet rs_codigo = pstmt.executeQuery();

                        ArrayList<String> nombre_proveedores_aux = new ArrayList<>();

                        while(rs_codigo.next()){
                            nombre_proveedores_aux.add(rs_codigo.getString("nombreProveedor"));
                        }
                        String selectedItem_aux = nombre_proveedores_aux.getFirst();
                        nombreProveedorAuxLabel.setText(selectedItem_aux);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });







        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            String query_familia = "SELECT idFamiliaArticulos, denominacionFamilias FROM familiaArticulos";
            ResultSet resultSet_familia = statement.executeQuery(query_familia);

            ArrayList<String> familia_articulo = new ArrayList<>();
            ArrayList<String> nombre_articulo = new ArrayList<>();

            while (resultSet_familia.next()) {
                familia_articulo.add(String.valueOf(resultSet_familia.getInt("idFamiliaArticulos")));
                nombre_articulo.add(resultSet_familia.getString("denominacionFamilias"));
            }

            DefaultComboBoxModel<String> model_familia = new DefaultComboBoxModel<>(familia_articulo.toArray(new String[0]));
            familiaArticulo = new JComboBox<>(model_familia);
            nombreFamiliaAuxLabel.setText(nombre_articulo.getFirst());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






        familiaArticulo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected item
                    String selectedItem = (String) familiaArticulo.getSelectedItem();
                    // Display the selected item
                    String query_codigo = "SELECT denominacionFamilias FROM familiaArticulos WHERE idFamiliaArticulos = ?;";

                    try (ConexionDB cdb = new ConexionDB();
                         Connection connection = cdb.getConnection();
                         PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {


                        pstmt.setString(1, selectedItem);
                        ResultSet rs_codigo = pstmt.executeQuery();

                        ArrayList<String> denominacion_familias_aux = new ArrayList<>();

                        while(rs_codigo.next()){
                            denominacion_familias_aux.add(rs_codigo.getString("denominacionFamilias"));

                        }

                        String selectedItem_aux = denominacion_familias_aux.getFirst();

                        nombreFamiliaAuxLabel.setText(selectedItem_aux);


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        });





        JLabel stockArticuloLabel = new JLabel("stockArticulo");
        stockArticulo = new JTextField();


        JLabel observacionesArticuloLabel = new JLabel("observacionesArticulo");
        observacionesArticulo = new JTextField();


        submitButton = new JButton("Crear artículo");





        // Add components to the frame

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(codigoArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(codigoArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(codigoBarrasArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(codigoBarrasArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(descripcionArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(descripcionArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(costeArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(costeArticulo, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        add(margenComercialArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(margenComercialArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(pvpArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(pvpArticulo, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;
        add(proveedorArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(proveedorArticulo, gbc);

        gbc.gridx=2;
        gbc.gridy=6;
        add(nombreProveedorAuxLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(stockArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(stockArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(observacionesArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(observacionesArticulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(familiaArticuloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(familiaArticulo, gbc);

        gbc.gridx=2;
        gbc.gridy=9;
        add(nombreFamiliaAuxLabel, gbc);

        //add(new JLabel());  Empty cell

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button

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

    // Method to check if a table exists
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
            return resultSet.next(); // Returns true if the table exists
        }
    }

}