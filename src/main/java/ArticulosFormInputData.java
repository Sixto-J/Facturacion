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
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(15, 4));


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
        JLabel familiaArticuloLabel = new JLabel("familiaArticulo:");

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
        add(codigoArticuloLabel);
        add(codigoArticulo);
        add(codigoBarrasArticuloLabel);
        add(codigoBarrasArticulo);
        add(descripcionArticuloLabel);
        add(descripcionArticulo);
        add(costeArticuloLabel);
        add(costeArticulo);
        add(margenComercialArticuloLabel);
        add(margenComercialArticulo);
        add(pvpArticuloLabel);
        add(pvpArticulo);
        add(proveedorArticuloLabel);
        add(proveedorArticulo);
        add(stockArticuloLabel);
        add(stockArticulo);
        add(observacionesArticuloLabel);
        add(observacionesArticulo);
        add(familiaArticuloLabel);
        add(familiaArticulo);


        add(new JLabel()); // Empty cell
        add(submitButton);

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