import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;


public class FacturasFormInputData extends JFrame {

    private JTextField numeroFacturaCliente;

    private JComboBox idClienteFactura;
    private JSpinner fechaFacturaCliente;
    private JTextField baseImponibleFacturaCliente;
    private JTextField ivaFacturaCliente;
    private JTextField totalFacturaCliente;
    private JTextField hashFacturaCliente;
    private JTextField qrFacturaCliente;
    private JComboBox cobradaFactura;
    private JTextField formaCobroFactura;
    private JSpinner fechaCobroFactura;
    private JTextField observacionesFacturaCliente;

    private JButton submitButton;
    private JButton verLineaProductos;

    LocalDate currentDate = LocalDate.now();


    public FacturasFormInputData() {

        setTitle("Datos de la factura");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //getContentPane().setLayout(new GridBagLayout());
        setLayout(new BorderLayout());

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



        double base_imponible = 0;
        double suma_iva_articulos = 0;

        JLabel baseImponibleFacturaClienteLabel = new JLabel("Base Imponible");
        baseImponibleFacturaCliente = new JTextField();
        baseImponibleFacturaCliente.setEnabled(false);
        if(submitButton.isEnabled()){
            // bucle sobre array de  objetos de linea de factura para sumar pvpArticulo a la base_imponible
            baseImponibleFacturaCliente.setText(String.valueOf(base_imponible));
        }



        JLabel ivaFacturaClienteLabel = new JLabel("IVA");
        ivaFacturaCliente = new JTextField();
        ivaFacturaCliente.setEnabled(false);
        if(submitButton.isEnabled()){
            // bucle sobre array de  objetos de linea de factura para sumar ivaArticulo a la variable suma_iva_articulos
            ivaFacturaCliente.setText(String.valueOf(suma_iva_articulos);
        }



        JLabel totalFacturaClienteLabel = new JLabel("Total");
        totalFacturaCliente = new JTextField();
        totalFacturaCliente.setEnabled(false);

        Double total;
        if (totalFacturaCliente.isEnabled()) {
            total = Double.valueOf(baseImponibleFacturaCliente.getText()) + Double.valueOf(String.valueOf(ivaFacturaCliente.getText()));
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
        JLabel observacionesLabel = new JLabel("Observaciones");
        observacionesFacturaCliente = new JTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        // Add components to the frame with gbc values
        gbc.insets = new Insets(5, 10, 2, 10); // Add padding around components

        JPanel formPanel = new JPanel(new GridBagLayout());



        settingGrBLayout(formPanel,gbc, 0, 0,numeroFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 1,fechaFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 2,idClienteFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 3,baseImponibleFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 4,ivaFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 5,totalFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 6,hashFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 7,qrFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 8,cobradaFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 9,formaCobroFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 10,fechaCobroFacturaLabel);
        settingGrBLayout(formPanel,gbc, 0, 11,observacionesLabel);



        gbc.ipadx=200;
        gbc.ipady=15;
        settingGrBLayout(formPanel,gbc, 1, 0,numeroFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 1,fechaFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 2,idClienteFactura);
        settingGrBLayout(formPanel,gbc, 1, 3,baseImponibleFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 4,ivaFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 5,totalFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 6,hashFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 7,qrFacturaCliente);
        settingGrBLayout(formPanel,gbc, 1, 8,cobradaFactura);
        settingGrBLayout(formPanel,gbc, 1, 9,formaCobroFactura);
        settingGrBLayout(formPanel,gbc, 1, 10,fechaCobroFactura);
        settingGrBLayout(formPanel,gbc, 1, 11,observacionesFacturaCliente);
        gbc.ipadx=0;
        gbc.ipady=0;

        add(formPanel,BorderLayout.WEST);

        //add(new JLabel());  Empty cell


        verLineaProductos = new JButton("Ver linea de productos");
        submitButton = new JButton("Crear factura");


        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(verLineaProductos);

        buttonPanel.add(submitButton);

        add(buttonPanel,BorderLayout.SOUTH);



        verLineaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String numFactura = numeroFacturaCliente.getText();
                new LineasFacturaFormInputData(numFactura);
            }
        });

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Objects.equals(totalFacturaCliente.getText(), "") && totalFacturaCliente.isEnabled()) {
                   insertFactura();
                   LineasFacturaFormInputData lf = new LineasFacturaFormInputData();
                   lf.insertLinea();
                   lf.clearFields();

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
                " baseImponibleFacturaCliente, ivaFacturaCliente, totalFacturaCliente, hasFacturaCliente," +
                " qrFacturaCliente, cobradaFactura, formaCobroFactura, fechaCobroFactura," +
                " observacionesFacturaCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, numeroFacturaCliente.getText());
            pstmt.setDate(2, (Date) fechaFacturaCliente.getValue());
            pstmt.setString(3, String.valueOf(idClienteFactura.getSelectedItem()));
            pstmt.setString(4, baseImponibleFacturaCliente.getText());
            pstmt.setString(5, String.valueOf(ivaFacturaCliente.getText()));
            pstmt.setString(6, totalFacturaCliente.getText());
            pstmt.setString(7, hashFacturaCliente.getText());
            pstmt.setString(8, qrFacturaCliente.getText());
            pstmt.setString(9, String.valueOf(cobradaFactura.getSelectedItem()));
            pstmt.setString(10, formaCobroFactura.getText());
            pstmt.setDate(11, (Date) fechaCobroFactura.getValue());
            pstmt.setString(12, observacionesFacturaCliente.getText());


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
        fechaFacturaCliente.setValue(currentDate);
        idClienteFactura.setSelectedIndex(0);
        baseImponibleFacturaCliente.setText("");
        ivaFacturaCliente.setText("");
        totalFacturaCliente.setText("");
        hashFacturaCliente.setText("");
        qrFacturaCliente.setText("");
        cobradaFactura.setSelectedIndex(0);
        formaCobroFactura.setText("");
        fechaCobroFactura.setValue(currentDate);
        observacionesFacturaCliente.setText("");
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