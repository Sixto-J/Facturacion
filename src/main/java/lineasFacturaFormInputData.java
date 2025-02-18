import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;


public class lineasFacturaFormInputData extends JFrame {


    private JComboBox familiasCombo;
    private JComboBox codigosCombo;
    private JButton desplegableLinea;
    private JLabel buscadorFamiliasLabel;
    private JLabel buscadorCodigoArticuloLabel;
    private JLabel numeroFacturaClienteLabel;
    private JLabel numeroFacturaCliente;

    private JLabel familiaArticuloLabel; //select
    private JLabel codigoArticuloLabel; // select

    //hay que controlar que el id, nombre, codigo, pvp, iva existan en proveedores y articulos


    private JButton backButton;


    public lineasFacturaFormInputData(String numFactura) {

        setTitle("Lineas de la factura");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;




        buscadorFamiliasLabel = new JLabel("Indica familia del artículo");
        buscadorCodigoArticuloLabel = new JLabel("Indica el codigo del artículo");



        //String articulo_query = "SELECT idArticulo from articulos WHERE idArticulo ='" + buscadorArticulo.getText() + "';";


        // Create labels and text fields
        numeroFacturaClienteLabel = new JLabel("Número Factura de cliente:");
        numeroFacturaCliente = new JLabel();
        numeroFacturaCliente.setText(numFactura);





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

            familiasCombo = new JComboBox<>(familiasArticulo);






            codigosCombo = new JComboBox<>();

            setCodigosCombo(codigosCombo);


            // Add an ItemListener to the JComboBox
            familiasCombo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        // Get the selected item
                        setCodigosCombo(codigosCombo);
                    }
                }

            });



        } catch (SQLException e) {
            e.printStackTrace();
        }


        backButton = new JButton("Volver");
        backButton.setPreferredSize(new Dimension(100, 50));

        // Add action listener for the submit button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }

        });






        desplegableLinea = new JButton("Añadir linea de producto");
        desplegableLinea.setPreferredSize(new Dimension(100, 50));

        desplegableLinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                insertLinea();

            }
        });

        // Add components to the frame
        gbc.gridx = 0; gbc.gridy=0;
        add(numeroFacturaClienteLabel,gbc);
        gbc.gridx = 0; gbc.gridy=1;
        add(buscadorFamiliasLabel,gbc);
        gbc.gridx = 0; gbc.gridy=2;
        add(buscadorCodigoArticuloLabel,gbc);

        gbc.gridx = 1; gbc.gridy=0;
        add(numeroFacturaCliente,gbc);
        gbc.gridx = 1; gbc.gridy=1;
        add(familiasCombo,gbc);
        gbc.gridx = 1; gbc.gridy=2;
        add(codigosCombo,gbc);
        gbc.gridx=0; gbc.gridy=3;
        add(backButton, gbc);
        gbc.gridx=1; gbc.gridy=3;
        add(desplegableLinea,gbc);

        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);


    }

    private void setCodigosCombo(JComboBox codigosCombo) {

        String selectedItem = (String) familiasCombo.getSelectedItem();
        // Display the selected item
        String query_codigo ="SELECT codigoArticulo FROM articulos a JOIN familiaArticulos f" +
                " ON a.familiaArticulo = f.idFamiliaArticulos WHERE f.denominacionFamilias = ?";

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


    private void insertLinea() {


        String idArticuloSelected = "";

        String idArticulo = "";
        String descripcionArticulo = "";
        String pvpArticulo = "";
        String proveedorArticulo = "";

        String ivaArticulo ="";
        String nombreProveedorArticulo = "";



        String query_codigo = "SELECT idArticulo, descripcionArticulo, pvpArticulo, proveedorArticulo FROM articulos WHERE idArticulo = ?;";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {

            pstmt.setString(1, idArticuloSelected);

            ResultSet rs_codigo = pstmt.executeQuery();

            while(rs_codigo.next()){
               idArticulo = rs_codigo.getString("idArticulo");
               descripcionArticulo = rs_codigo.getString("descripcionArticulo");
               pvpArticulo = rs_codigo.getString("pvpArticulo");
               proveedorArticulo = rs_codigo.getString("proveedorArticulo");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }




        String codigo_selected=(String)codigosCombo.getSelectedItem();
       //---------------------------------------------





        String query_codigo1 = "SELECT nombreProveedor FROM articulos a INNER JOIN proveedores p" +
                " ON a.proveedorArticulo = p.idProveedor  WHERE a.codigoArticulo = ?";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_codigo1)) {

            pstmt.setString(1, codigo_selected);

            ResultSet rs_codigo = pstmt.executeQuery();

            while(rs_codigo.next()){
                nombreProveedorArticulo = rs_codigo.getString("nombreProveedor");


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }






        //---------------------------------------------





        String query_codigo3 = "INSERT INTO numeroFacturaCliente, idArticulo, descripcionArticulo, codigoArticulo," +
                " pvpArticulo, ivaArticulo, idProveedorArticulo, nombreProveedorArticulo" +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query_codigo3)) {

            pstmt.setInt(1, Integer.valueOf(numeroFacturaCliente.getText()));
            pstmt.setInt(2, Integer.valueOf(idArticulo));
            pstmt.setString(3, descripcionArticulo);
            pstmt.setString(4, codigo_selected);
            pstmt.setDouble(5, Double.valueOf(pvpArticulo));

            pstmt.setDouble(6, Double.valueOf(String.valueOf(ivaArticulo)));
            // query anidada entre tiposIva, familiaArticulo y articulo

            pstmt.setInt(7, Integer.valueOf(proveedorArticulo));

            pstmt.setString(8, nombreProveedorArticulo);
            // query entre articulos y proveedores a partir de clave foranea ProveedorArticulo = idProveedor

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Article added successfully!");
            clearFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding article: " + ex.getMessage());
        }

    }


    private void clearFields() {
        numeroFacturaCliente.setText("");

    }


}
