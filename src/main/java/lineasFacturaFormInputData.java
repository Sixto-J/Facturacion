import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;


public class lineasFacturaFormInputData extends JFrame {


    private JTextField numeroFacturaCliente; // setted

    private JTextField idArticulo; //select
    private JTextField descripcionArticulo; // select
    private JTextField codigoArticulo; // select
    private JTextField pvpArticulo; // select
    private JTextField ivaArticulo; // select
    private JTextField idProveedorArticulo; // select
    private JTextField nombreProveedorArticulo; // select

    private JLabel familiaArticuloLabel; //select
    private JLabel codigoArticuloLabel; // select

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






        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            // Execute SELECT query
            String query_familia = "SELECT denominacionFamilias FROM familiaArticulos"; // Replace with your table name
            ResultSet resultSet_familia = statement.executeQuery(query_familia);

            ArrayList<String> tiposFamilia = new ArrayList<>();

            while (resultSet_familia.next()) {
                tiposFamilia.add(String.valueOf(resultSet_familia.getString("denominacionFamilias")));
            }

            DefaultComboBoxModel<String> familiasArticulo = new DefaultComboBoxModel<>(tiposFamilia.toArray(new String[0]));
            JComboBox familiasCombo = new JComboBox<>(familiasArticulo);





            JComboBox codigosCombo = new JComboBox<>();

            // Add an ItemListener to the JComboBox
            familiasCombo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        // Get the selected item
                        String selectedItem = (String) familiasCombo.getSelectedItem();
                        // Display the selected item
                        String query_codigo = "SELECT codigoArticulo FROM articulos WHERE familiaArticulos IN(" +
                                " SELECT idFamiliaArticulos FROM familiaArticulos WHERE denominacionFamilias = ?);";

                        try (ConexionDB cdb = new ConexionDB();
                             Connection connection = cdb.getConnection();
                             PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {


                            pstmt.setString(1, selectedItem);
                            ResultSet rs_codigo = pstmt.executeQuery();

                            ArrayList<String> codigoArticulos = new ArrayList<>();

                            while (rs_codigo.next()) {
                                codigoArticulos.add(String.valueOf(rs_codigo.getString("codigoArticulo")));
                            }

                            DefaultComboBoxModel<String> codigoArticulosModel = new DefaultComboBoxModel<>(codigoArticulos.toArray(new String[0]));


                           codigosCombo.setModel(codigoArticulosModel);



                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            });

            add(familiasCombo);
            add(codigosCombo);




        }catch (SQLException e) {
         e.printStackTrace();
        }




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
