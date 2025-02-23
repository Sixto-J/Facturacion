import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;


public class FacturasFormInputData extends JFrame {

    private JTextField numeroFacturaCliente;

    private JComboBox idClienteFactura;
    private JSpinner fechaFacturaCliente;
    private JTextField baseImponibleFacturaCliente;
    private JTextField ivaFacturaCliente;
    private JTextField totalFacturaCliente;
    //private JTextField hashFacturaCliente;
    //private JTextField qrFacturaCliente;
    private JComboBox cobradaFactura;
    private JComboBox formaCobroFactura;
    private JSpinner fechaCobroFactura;
    private JTextField observacionesFacturaCliente;

    private JButton CrearFacturaButton = new JButton("Crear factura");
    private JButton verLineasButton;

    LocalDate currentDate = LocalDate.now();
    java.util.Date currentTime = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    ArrayList<LineasFactura> llf;

    double base_imponible = 0;
    double suma_iva_articulos = 0;
    double total_factura;

    String aux_nfc = "";


    public FacturasFormInputData() {

        setTitle("Datos de la factura");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //getContentPane().setLayout(new GridBagLayout());
        setLayout(new BorderLayout());



        CrearFacturaButton.setEnabled(false);



        // Create labels and text fields


        JLabel numeroFacturaClienteLabel = new JLabel("Número Factura de cliente");
        numeroFacturaCliente = new JTextField();


        JLabel fechaFacturaClienteLabel = new JLabel("Fecha factura de cliente");
        // Create a Spinner for date input
        SpinnerDateModel model1 = new SpinnerDateModel();
        fechaFacturaCliente = new JSpinner(model1);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaFacturaCliente, "yyyy-MM-dd");
        fechaFacturaCliente.setEditor(editor);


        JLabel fechaCobroFacturaLabel = new JLabel("Fecha de cobro");
        SpinnerDateModel model2 = new SpinnerDateModel();
        fechaCobroFactura = new JSpinner(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(fechaCobroFactura, "yyyy-MM-dd");
        fechaCobroFactura.setEditor(editor2);


        JLabel idClienteFacturaLabel = new JLabel("Id Cliente");
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
            // Execute SELECT query
            String query_cliente = "SELECT idCliente FROM clientes"; // Replace with your table name
            ResultSet resultSet_cliente = statement.executeQuery(query_cliente);

            ArrayList<String> idClientes = new ArrayList<>();

            while (resultSet_cliente.next()) {
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
        ivaFacturaCliente = new JTextField();
        ivaFacturaCliente.setEnabled(false);

        JLabel totalFacturaClienteLabel = new JLabel("Total");
        totalFacturaCliente = new JTextField();
        totalFacturaCliente.setEnabled(false);

/*
        JLabel hashFacturaClienteLabel = new JLabel("Hash");
        hashFacturaCliente = new JTextField();
        JLabel qrFacturaClienteLabel = new JLabel("QR");
        qrFacturaCliente = new JTextField();*/


        JLabel cobradaFacturaLabel = new JLabel("Cobrada");
        String[] cobradaFacturaOptions = {"yes", "no"};
        cobradaFactura = new JComboBox<>(cobradaFacturaOptions);


        JLabel formaCobroFacturaLabel = new JLabel("Forma de cobro");

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
            // Execute SELECT query
            String query_cliente = "SELECT DISTINCT tipoFormaPago FROM formaPago"; // Replace with your table name
            ResultSet resultSet_formaPago = statement.executeQuery(query_cliente);

            ArrayList<String> formaPagoList = new ArrayList<>();

            while (resultSet_formaPago.next()) {
                formaPagoList.add(String.valueOf(resultSet_formaPago.getString("tipoFormaPago")));

            }
            DefaultComboBoxModel<String> model_forma_pago = new DefaultComboBoxModel<>(formaPagoList.toArray(new String[0]));
            formaCobroFactura = new JComboBox<>(model_forma_pago);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        JLabel observacionesLabel = new JLabel("Observaciones");
        observacionesFacturaCliente = new JTextField();








        // Add components to the frame with gbc values
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 2, 10); // Add padding around components

        JPanel formPanel = new JPanel(new GridBagLayout());

        settingGrBLayout(formPanel,gbc, 0, 0,numeroFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 1,fechaFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 2,idClienteFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 3,baseImponibleFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 4,ivaFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 5,totalFacturaClienteLabel);
        //settingGrBLayout(formPanel,gbc, 0, 6,hashFacturaClienteLabel);
        //settingGrBLayout(formPanel,gbc, 0, 7,qrFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 6,cobradaFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 7,formaCobroFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 8,fechaCobroFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 9,observacionesLabel);


        gbc.ipadx=200;
        gbc.ipady=15;
        settingGrBLayout(formPanel,gbc, 1, 0,numeroFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 1,fechaFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 2,idClienteFactura);
        settingGrBLayout(formPanel,gbc, 1, 3,baseImponibleFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 4,ivaFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 5,totalFacturaCliente);
        //settingGrBLayout(formPanel,gbc, 1, 6,hashFacturaCliente);
        //settingGrBLayout(formPanel,gbc, 1, 7,qrFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 6,cobradaFactura);
        settingGrBLayout(formPanel,gbc, 1, 7,formaCobroFactura);
        settingGrBLayout(formPanel,gbc, 1, 8,fechaCobroFactura);
        settingGrBLayout(formPanel,gbc, 1, 9,observacionesFacturaCliente);
        gbc.ipadx=0;
        gbc.ipady=0;

        add(formPanel,BorderLayout.WEST);

        //add(new JLabel());  Empty cell






        verLineasButton = new JButton("Ver linea de productos");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(verLineasButton);
        buttonPanel.add(CrearFacturaButton);
        add(buttonPanel,BorderLayout.SOUTH);

        verLineasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //String numFactura = numeroFacturaCliente.getText(); variable usada inicialmente

                String nf = numeroFacturaCliente.getText();
                boolean digit = nf.matches("\\d+");

                if(nf.isEmpty()){
                    JOptionPane.showMessageDialog(FacturasFormInputData.this, "Campo numeroFacturaCliente no puede estar vacío!");
                }else if(!digit){
                    JOptionPane.showMessageDialog(FacturasFormInputData.this, "Campo numeroFacturaCliente debe tener sólo dígitos!");
                }else {
                    new LineasFacturaFormInputData(FacturasFormInputData.this);
                }
            }
        });



        // Add action listener for the submit button
        CrearFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(totalFacturaCliente.getText(), "")) {
                    if(CrearFacturaButton.isEnabled()){
                        insertFactura();
                        insertLinea(llf);
                        clearFields();
                    }
                } else {
                    //mensaje de error
                }
            }
        });


        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }



    private void insertFactura() {

        String query = "INSERT INTO facturasClientes (numeroFacturaCliente, fechaFacturaCliente, idClienteFactura," +
                " baseImponibleFacturaCliente, ivaFacturaCliente, totalFacturaCliente, cobradaFactura, formaCobroFactura, fechaCobroFactura," +
                " observacionesFacturaCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // hasFacturaCliente," + " qrFacturaCliente,

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setString(1, numeroFacturaCliente.getText());

            aux_nfc = numeroFacturaCliente.getText();

            //System.out.println("Valor de fecha elegida 1: "+fechaFacturaCliente.getValue());
            //System.out.println("Tipo del valor fechaFacturaCliente: " + fechaFacturaCliente.getValue().getClass());


            java.util.Date selectedDate1 = (java.util.Date)fechaFacturaCliente.getValue();
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate1 = new java.sql.Date(selectedDate1.getTime());

            pstmt.setDate(2, sqlDate1);

            pstmt.setString(3, String.valueOf(idClienteFactura.getSelectedItem()));

            pstmt.setDouble(4, Double.parseDouble(baseImponibleFacturaCliente.getText()));
            pstmt.setDouble(5, Double.parseDouble(ivaFacturaCliente.getText()));
            pstmt.setDouble(6, Double.parseDouble(totalFacturaCliente.getText()));

            //pstmt.setString(7, hashFacturaCliente.getText());
            //pstmt.setString(8, qrFacturaCliente.getText());

            pstmt.setBoolean(7, Boolean.parseBoolean(String.valueOf(cobradaFactura.getSelectedItem())));
            pstmt.setString(8, (String) formaCobroFactura.getSelectedItem());

            java.util.Date selectedDate2 = (java.util.Date) fechaCobroFactura.getValue();
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate2 = new java.sql.Date(selectedDate2.getTime());

            pstmt.setDate(9, sqlDate2);

            pstmt.setString(10, observacionesFacturaCliente.getText());


            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Bill added successfully!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding bill: " + ex.getMessage());
        }
    }


    public void insertLinea(ArrayList<LineasFactura> llf) {

        for (LineasFactura linea : llf) {

            System.out.println("LINEAS: " + linea);

            String query = "INSERT INTO lineasFacturasClientes (numeroFacturaCliente, idArticulo, descripcionArticulo, codigoArticulo," +
                    " pvpArticulo, ivaArticulo, idProveedorArticulo, nombreProveedorArticulo" +
                    " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (
                    ConexionDB cdb = new ConexionDB();
                    Connection conn = cdb.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(aux_nfc));
                pstmt.setInt(2, linea.getIdArticulo());
                pstmt.setString(3, linea.getDescripcionArticulo());
                pstmt.setString(4, linea.getCodigoArticulo());
                pstmt.setDouble(5, linea.getPvpArticulo());
                pstmt.setDouble(6, linea.getIvaArticulo());

                // necesita una query anidada entre tiposIva, familiaArticulo y articulo
                pstmt.setInt(7, linea.getIdProveedorArticulo());
                pstmt.setString(8, linea.getNombreProveedorArticulo());
                // query entre articulos y proveedores a partir de clave foranea ProveedorArticulo = idProveedor

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Article added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No Article added!");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding article: " + ex.getMessage());
            }
        }
    }


    public void settingGrBLayout(JPanel formPanel, GridBagConstraints constraints, int valorx, int valory, Object o) {

        // int ancho, int alto      constraints.fill = GridBagConstraints.BOTH;     Fill both
        constraints.gridx = valorx;
        constraints.gridy = valory;
        if (o instanceof JTextField) {
            // ((JTextField) o).setPreferredSize(new Dimension(ancho, alto));
            formPanel.add((JTextField) o, constraints);
        } else if (o instanceof JComboBox<?>) {
            //((JComboBox<?>) o).setPreferredSize(new Dimension(ancho, alto));
            formPanel.add((JComboBox<?>) o, constraints);
        } else if (o instanceof JLabel) {
            //((JLabel) o).setPreferredSize(new Dimension(ancho, alto));
            formPanel.add((JLabel) o, constraints);
        } else if(o instanceof JSpinner){
            formPanel.add((JSpinner) o, constraints);
        }
        //constraints.gridwidth=1;

    }


    public String getNumeroFacturaCliente() {
        return numeroFacturaCliente.getText();
    }


    public void habilitarFactura(ArrayList<LineasFactura> lineasFacturaList){


        // Evaluamos la condicion de si hay lineas de producto
        System.out.println("lista longitud "+lineasFacturaList.size());

        if(lineasFacturaList.size() > 0){
            CrearFacturaButton.setEnabled(true);

        }

        llf = lineasFacturaList;
        setearImportesFactura();

    }


    private void setearImportesFactura() {

        double total = 0.0;
        double totalIVA = 0.0;

        // DecimalFormat df = new DecimalFormat("#.###"); Clase no usada, por consultar para aplicar correctamente

        if(CrearFacturaButton.isEnabled()){
            // bucle sobre array de  objetos de linea de factura para sumar pvpArticulo a la base_imponible

            for (int i = 0; i < llf.size() ; i++) {

              LineasFactura lf = llf.get(i);

              double precio_articulo = lf.getPvpArticulo();

              total+=precio_articulo;

              double iva_articulo=lf.getIvaArticulo();

              iva_articulo=iva_articulo/100;
                System.out.println("IVA: "+iva_articulo);


              totalIVA+=precio_articulo*iva_articulo;
                System.out.println("Se ha seteado un valor total de iva: "+totalIVA);


            }


            //base_imponible= Double.parseDouble(df.format(total).replaceAll("[^\\d.]", ""));
            //suma_iva_articulos= Double.valueOf(df.format(base_imponible*0.21).replaceAll("[^\\d.]", ""));
            //total_factura = Double.valueOf(df.format(base_imponible+suma_iva_articulos).replaceAll("[^\\d.]", ""));
            //total = Double.valueOf(baseImponibleFacturaCliente.getText()) + Double.valueOf(String.valueOf(ivaFacturaCliente.getText()));

            base_imponible=total;
            base_imponible = Math.floor(base_imponible * 100) / 100;
            baseImponibleFacturaCliente.setText(String.valueOf(base_imponible));

            suma_iva_articulos=totalIVA;
            suma_iva_articulos = Math.floor(suma_iva_articulos * 100) / 100;
            ivaFacturaCliente.setText(String.valueOf(suma_iva_articulos));


            total_factura = base_imponible + suma_iva_articulos;
            total_factura = Math.floor(total_factura * 100) / 100;
            totalFacturaCliente.setText(String.valueOf(total_factura));

        }
    }

    private void clearFields() {
        numeroFacturaCliente.setText("");
        fechaFacturaCliente.setValue(currentTime);
        idClienteFactura.setSelectedIndex(0);
        baseImponibleFacturaCliente.setText("");
        ivaFacturaCliente.setText("");
        totalFacturaCliente.setText("");
        //hashFacturaCliente.setText("");
        //qrFacturaCliente.setText("");
        cobradaFactura.setSelectedIndex(0);
        formaCobroFactura.setSelectedIndex(0);
        fechaCobroFactura.setValue(currentTime);
        observacionesFacturaCliente.setText("");
    }


}

       /*  dropdown de IVA no usado
        try (ConexionDB cdb = new ConexionDB();
            Connection connection = cdb.getConnection();
            Statement statement = connection.createStatement()) {
            //Execute SELECT query
            String query_iva = "SELECT idTipoIva FROM tiposIVA"; // Replace with your table name
            ResultSet resultSet_iva = statement.executeQuery(query_iva);
            ArrayList<String> tiposIva = new ArrayList<>();
            while (resultSet_iva.next()) {
                tiposIva.add(String.valueOf(resultSet_iva.getInt("idTipoIva")));
            }
            DefaultComboBoxModel<String> model_iva = new DefaultComboBoxModel<>(tiposIva.toArray(new String[0]));
            ivaFacturaCliente = new JComboBox<>(model_iva);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       */


       /* Tabla no usada
        LineasFactura lineas_factura = new LineasFactura();
        DefaultTableModel model = lineas_factura.obtener_lineas_factura();
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Set the model to the table
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 3;
        gbc.gridy = 12;
        gbc.ipadx = 500;
        gbc.ipady = 80;
        scrollPane.setPreferredSize(new Dimension(750, 332));
        add(scrollPane, BorderLayout.EAST);
       */