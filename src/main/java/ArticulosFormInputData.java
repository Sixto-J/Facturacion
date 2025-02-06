import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticulosFormInputData extends JFrame {

    private JTextField codigoArticulo;
    private JTextField codigoBarrasArticulo;
    private JTextField descripcionArticulo;
    private JTextField costeArticulo;
    private JTextField margenComercialArticulo;
    private JTextField pvpArticulo;
    private JTextField proveedorArticulo;
    private JTextField stockArticulo;
    private JTextField observacionesArticulo;
    private JTextField familiaArticulo;


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
        proveedorArticulo = new JTextField();
        JLabel stockArticuloLabel = new JLabel("stockArticulo");
        stockArticulo = new JTextField();
        JLabel observacionesArticuloLabel = new JLabel("observacionesArticulo");
        observacionesArticulo = new JTextField();
        JLabel familiaArticuloLabel = new JLabel("familiaArticulo:");
        familiaArticulo = new JTextField();



        submitButton = new JButton("Submit");

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
                insertCustomer();
            }
        });

        this.setVisible(true);
    }




    private void insertCustomer() {



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
            pstmt.setString(4, costeArticulo.getText());
            pstmt.setString(5, margenComercialArticulo.getText());
            pstmt.setString(6, pvpArticulo.getText());
            pstmt.setString(7, proveedorArticulo.getText());
            pstmt.setString(8, stockArticulo.getText());
            pstmt.setString(9, observacionesArticulo.getText());
            pstmt.setString(10, familiaArticulo.getText());


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
        proveedorArticulo.setText("");
        stockArticulo.setText("");
        observacionesArticulo.setText("");
        familiaArticulo.setText("");
    }

}