import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;


public class LineasFacturaFormInputData extends JFrame {


    private JComboBox familiasCombo;
    private JComboBox codigosCombo;
    private JButton AgregarLinea;
    private JLabel buscadorFamiliasLabel;
    private JLabel buscadorCodigoArticuloLabel;
    private JLabel numeroFacturaClienteLabel;
    private JLabel numeroFacturaCliente;

    private JLabel familiaArticuloLabel; //select
    private JLabel codigoArticuloLabel; // select

    private JButton backButton;


    //hay que controlar que el id, nombre, codigo, pvp, iva existan en proveedores y articulos

    public LineasFacturaFormInputData(String numFactura) {


        setTitle("Lineas de la factura");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;


        buscadorFamiliasLabel = new JLabel("Indica familia del artículo");
        buscadorCodigoArticuloLabel = new JLabel("Indica el codigo del artículo");
        // Create labels and text fields
        numeroFacturaClienteLabel = new JLabel("Número Factura de cliente:");
        numeroFacturaCliente = new JLabel();
        numeroFacturaCliente.setText(numFactura);


        //String articulo_query = "SELECT idArticulo from articulos WHERE idArticulo ='" + buscadorArticulo.getText() + "';";




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



        // Botones y listeners
        backButton = new JButton("Volver");
        backButton.setPreferredSize(new Dimension(100, 50));

        // Add action listener for the submit button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });


        AgregarLinea = new JButton("Añadir linea de producto");
        AgregarLinea.setPreferredSize(new Dimension(100, 50));

        AgregarLinea.addActionListener(new ActionListener() {
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
        add(AgregarLinea,gbc);

        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);

    }



    private void setCodigosCombo(JComboBox codigosCombo) {

        String familia_selected= (String) familiasCombo.getSelectedItem();
        // Display the selected item
        String query_codigo ="SELECT codigoArticulo FROM articulos a JOIN familiaArticulos f" +
                " ON a.familiaArticulo = f.idFamiliaArticulos WHERE f.denominacionFamilias = ?";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {


            pstmt.setString(1, familia_selected);
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



    public void insertLinea() {


        String idArticulo = "";
        String descripcionArticulo = "";
        String pvpArticulo = "";
        String ivaArticulo = "";
        String proveedorArticulo = "";
        String nombreProveedorArticulo = "";



        String query_codigo = "SELECT idArticulo, descripcionArticulo, pvpArticulo, proveedorArticulo FROM articulos WHERE codigoArticulo = ?;";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_codigo)) {

            pstmt.setString(1, (String) codigosCombo.getSelectedItem().toString());

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





       //---------------------------------------------

        String codigo_selected=(String)codigosCombo.getSelectedItem();
        System.out.println("Codigo selected: "+codigo_selected);



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


        //------------------------------------------------------


        String query_codigo2 = "SELECT ti.idTipoIva FROM tiposIVA ti WHERE ti.idTipoIva = (SELECT fa.tipoIVA FROM familiaArticulos fa " +
                "WHERE fa.idFamiliaArticulos = (SELECT a.familiaArticulo FROM articulos a WHERE a.codigoArticulo = ? ) );";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_codigo2)) {

            pstmt.setString(1, codigo_selected);

            ResultSet rs_codigo = pstmt.executeQuery();

            while(rs_codigo.next()){
                ivaArticulo = rs_codigo.getString("ti.idTipoIva");
                System.out.println("IVA articulo: "+ivaArticulo);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }




        //---------------------------------------------





        String query_codigo3 = "INSERT INTO lineasFacturasClientes (numeroFacturaCliente, idArticulo, descripcionArticulo, codigoArticulo," +
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

            pstmt.setDouble(6, Double.valueOf(ivaArticulo));
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


    protected void clearFields() {
        numeroFacturaCliente.setText("");

    }


    public LineasFacturaFormInputData() {
        // Empty constructor
    }


}
