import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class lineasFacturaFormInputData extends JFrame {


    private JTextField numeroFacturaCliente; // setted

    private JTextField idArticulo; //select
    private JTextField descripcionArticulo; // select
    private JTextField codigoArticulo; // select
    private JTextField pvpArticulo; // select
    private JTextField ivaArticulo; // select
    private JTextField idProveedorArticulo; // select
    private JTextField nombreProveedorArticulo; // select

    //hay que controlar que el id, nombre, codigo, pvp, iva existan en proveedores y articulos


    private JButton backButton;


    public lineasFacturaFormInputData(String numFactura) {

        setTitle("Lineas de la factura");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(15, 4));






        JLabel buscadorArticuloLabel = new JLabel("Buscar artículo");
        JTextField buscadorArticulo = new JTextField();

        String articulo_query = "SELECT idArticulo from articulos WHERE idArticulo ='" + buscadorArticulo.getText()+"';";










        // Create labels and text fields
        JLabel numeroFacturaClienteLabel = new JLabel("Número Factura de cliente");
        numeroFacturaCliente = new JTextField();
        numeroFacturaCliente.setText(numFactura);
        numeroFacturaCliente.setEnabled(false);

        add(numeroFacturaClienteLabel); // Add components to the frame
        add(numeroFacturaCliente);


        add(new JLabel()); // Empty cell


        backButton = new JButton("Volver");

        // Add action listener for the submit button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                insertLinea();

            }

        });

        add(backButton);



        JButton desplegableLinea;
        desplegableLinea = new JButton("Añadir producto");
        desplegableLinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {







            }
        });


        add(desplegableLinea);



        this.setVisible(true);






    }








    private void insertLinea() {



        String query = "INSERT INTO facturasClientes (idLineaFacturaCliente, numeroFacturaCliente, idArticulo," +
                " descripcionArticulo, codigoArticulo, pvpArticulo, ivaArticulo," +
                " idProveedorArticulo, nombreProveedorArticulo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Integer.valueOf(numeroFacturaCliente.getText()));
            pstmt.setInt(2, Integer.valueOf(idArticulo.getText()));
            pstmt.setString(3, descripcionArticulo.getText());
            pstmt.setString(4, codigoArticulo.getText());
            pstmt.setDouble(5, Double.valueOf(pvpArticulo.getText()));
            pstmt.setDouble(6, Double.valueOf(ivaArticulo.getText()));
            pstmt.setInt(7, Integer.valueOf(idProveedorArticulo.getText()));
            pstmt.setString(8, nombreProveedorArticulo.getText());


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

    }

}