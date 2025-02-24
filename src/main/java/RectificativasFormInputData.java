import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;



public class RectificativasFormInputData extends JFrame {

    private JTextField numeroRectificativa;
    private JComboBox cifClienteRectificativaCombo;
    private JComboBox numeroRectificativaCombo;
    private JSpinner fechaRectificativa;
    private JTextField baseImponibleRectificativa;
    private JTextField ivaRectificativa;
    private JTextField totalRectificativa;
    private JTextField observacionesRectificativa;
    private JLabel buscadorCifClientesLabel;
    private JLabel buscadornumeroFacturasLabel;

    private JButton CrearRectificativaButton = new JButton("Crear Rectificativa");

    LocalDate currentDate = LocalDate.now();
    java.util.Date currentTime = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


    public RectificativasFormInputData() {

        setTitle("Datos de la rectificativa");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());


        Utilidades u = new Utilidades();

        JLabel numeroRectificativaClienteLabel = new JLabel("Número Rectificativa de cliente");
        numeroRectificativa = new JTextField();

        u.CheckInputListener(numeroRectificativa);


        JLabel fechaRectificativaClienteLabel = new JLabel("Fecha factura de cliente");
        // Create a Spinner for date input
        SpinnerDateModel model1 = new SpinnerDateModel();
        fechaRectificativa = new JSpinner(model1);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaRectificativa, "yyyy-MM-dd");
        fechaRectificativa.setEditor(editor);


        buscadorCifClientesLabel = new JLabel("CIF Cliente:");
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {
            // Execute SELECT query
            String query_cliente = "SELECT cifCliente FROM clientes"; // Replace with your table name
            ResultSet resultSet_clientes = statement.executeQuery(query_cliente);

            ArrayList<String> lista_clientes = new ArrayList<>();

            while (resultSet_clientes.next()) {
                lista_clientes.add(String.valueOf(resultSet_clientes.getString("cifCliente")));
            }
            DefaultComboBoxModel<String> clientes = new DefaultComboBoxModel<>(lista_clientes.toArray(new String[0]));
            cifClienteRectificativaCombo = new JComboBox<>(clientes);


            buscadornumeroFacturasLabel = new JLabel("Factura cliente:");
            numeroRectificativaCombo = new JComboBox<>();
            setFacturasCombo(numeroRectificativaCombo);
            // Add an ItemListener to the JComboBox
            cifClienteRectificativaCombo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        // Get the selected item
                        setFacturasCombo(numeroRectificativaCombo);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }


        JLabel baseImponibleFacturaClienteLabel = new JLabel("Base Imponible");
        baseImponibleRectificativa = new JTextField();
        u.CheckInputListener(baseImponibleRectificativa);


        JLabel ivaFacturaClienteLabel = new JLabel("IVA");
        ivaRectificativa = new JTextField();
        u.CheckInputListener(ivaRectificativa);


        JLabel totalRectificativaClienteLabel = new JLabel("Total");
        totalRectificativa = new JTextField();
        u.CheckInputListener(totalRectificativa);

        JLabel observacionesRectificativaLabel = new JLabel("Observaciones");
        observacionesRectificativa = new JTextField();


        // Add components to the frame with gbc values
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 2, 10); // Add padding around components

        JPanel formPanel = new JPanel(new GridBagLayout());

        settingGrBLayout(formPanel, gbc, 0, 0, numeroRectificativaClienteLabel);
        settingGrBLayout(formPanel, gbc, 0, 1, fechaRectificativaClienteLabel);
        settingGrBLayout(formPanel, gbc, 0, 2, buscadorCifClientesLabel);
        settingGrBLayout(formPanel, gbc, 0, 3, buscadornumeroFacturasLabel);
        settingGrBLayout(formPanel, gbc, 0, 4, baseImponibleFacturaClienteLabel);
        settingGrBLayout(formPanel, gbc, 0, 5, ivaFacturaClienteLabel);
        settingGrBLayout(formPanel, gbc, 0, 6, totalRectificativaClienteLabel);
        settingGrBLayout(formPanel, gbc, 0, 7, observacionesRectificativaLabel);


        gbc.ipadx = 200;
        gbc.ipady = 15;
        settingGrBLayout(formPanel, gbc, 1, 0, numeroRectificativa);
        settingGrBLayout(formPanel, gbc, 1, 1, fechaRectificativa);
        settingGrBLayout(formPanel, gbc, 1, 2, cifClienteRectificativaCombo);
        settingGrBLayout(formPanel, gbc, 1, 3, numeroRectificativaCombo);
        settingGrBLayout(formPanel, gbc, 1, 4, baseImponibleRectificativa);
        settingGrBLayout(formPanel, gbc, 1, 5, ivaRectificativa);
        settingGrBLayout(formPanel, gbc, 1, 6, totalRectificativa);
        settingGrBLayout(formPanel, gbc, 1, 7, observacionesRectificativa);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        add(formPanel, BorderLayout.WEST);


        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(CrearRectificativaButton);
        add(buttonPanel, BorderLayout.SOUTH);


        // Add action listener for the submit button
        CrearRectificativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (u.areFieldsFilled(numeroRectificativa,baseImponibleRectificativa,ivaRectificativa,totalRectificativa)) {
                    insertRectificativa();
                    clearFields();
                }

            }
        });


        /**
         * Establecemos una fuente de tipo Font, con UIManager podemos ver datos sobre qué Look and Feel usa nuestro
         * sistema, En el objeto Font damos valores tipo, tamaño...
         */

        Font defaultFont = UIManager.getFont("Label.font");
        System.out.println("Default Font: " + defaultFont);
        Font font = new Font("Lucida Grande", Font.PLAIN, 14);
        u.setFontRecursively(RectificativasFormInputData.this, font);


        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void insertRectificativa() {

        String query = "INSERT INTO rectificativasClientes (numeroRectificativa, fechaRectificativa, idClienteRectificativa, idFacturaRectificativa," +
                " baseImponibleRectificativa, ivaRectificativa, totalRectificativa," +
                " observacionesRectificativa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                ConexionDB cdb = new ConexionDB();
                Connection conn = cdb.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setString(1, numeroRectificativa.getText());


            java.util.Date selectedDate1 = (java.util.Date) fechaRectificativa.getValue();
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate1 = new java.sql.Date(selectedDate1.getTime());
            pstmt.setDate(2, sqlDate1);


            // Get the selected CIF Cliente from the JComboBox
            String selectedCifCliente = (String) cifClienteRectificativaCombo.getSelectedItem();
            String query_cliente = "SELECT idCliente FROM clientes WHERE cifCliente=?";
            int idCliente;
            try(PreparedStatement pstmt2 = conn.prepareStatement(query_cliente)){
                pstmt2.setString(1,selectedCifCliente);
                ResultSet resultSet_cliente = pstmt2.executeQuery();

                if (resultSet_cliente.next()) {
                    idCliente = resultSet_cliente.getInt("idCliente");
                    System.out.println("El ID cliente devuelto: "+idCliente);
                }else {
                    JOptionPane.showMessageDialog(this, "CIF Cliente not found.");
                    return; // Exit if no client is found
                }
            }
            pstmt.setInt(3, idCliente);





            String selectedNumFactura = (String) numeroRectificativaCombo.getSelectedItem();
            String query_factura = "SELECT idFacturaCliente FROM facturasClientes WHERE numeroFacturaCliente=?";
            int idFacturaCliente;
            try(PreparedStatement pstmt3 = conn.prepareStatement(query_factura)){
                pstmt3.setString(1,selectedNumFactura);
                ResultSet resultSet_factura = pstmt3.executeQuery();

                if (resultSet_factura.next()) {
                    idFacturaCliente = resultSet_factura.getInt("idFacturaCliente");
                    System.out.println("El ID cliente devuelto: "+idFacturaCliente);
                }else {
                    JOptionPane.showMessageDialog(this, "CIF Cliente not found.");
                    return; // Exit if no client is found
                }
            }
            pstmt.setInt(4, idFacturaCliente);




            pstmt.setDouble(5, Double.parseDouble(baseImponibleRectificativa.getText()));
            pstmt.setDouble(6, Double.parseDouble(ivaRectificativa.getText()));
            pstmt.setDouble(7, Double.parseDouble(totalRectificativa.getText()));
            pstmt.setString(8, observacionesRectificativa.getText());


            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Rectificated bill added successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding Rectificated bill: " + ex.getMessage());
        }

    }


    private void setFacturasCombo(JComboBox numeroRectificativaCombo) {

        String cliente_selected = (String) cifClienteRectificativaCombo.getSelectedItem();
        // Display the selected item
        String query_num_factura = "SELECT numeroFacturaCliente FROM facturasClientes fc JOIN clientes c" +
                " ON fc.idClienteFactura = c.idCliente WHERE c.cifCliente = ?";

        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query_num_factura)) {


            pstmt.setString(1, cliente_selected);
            ResultSet rs_codigo = pstmt.executeQuery();

            ArrayList<String> numeroRectificativa = new ArrayList<>();

            while (rs_codigo.next()) {
                numeroRectificativa.add(String.valueOf(rs_codigo.getString("numeroFacturaCliente")));
            }

            DefaultComboBoxModel<String> numeroRectificativaModel = new DefaultComboBoxModel<>(numeroRectificativa.toArray(new String[0]));


            numeroRectificativaCombo.setModel(numeroRectificativaModel);


        } catch (SQLException ex) {
            ex.printStackTrace();
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
        } else if (o instanceof JSpinner) {
            formPanel.add((JSpinner) o, constraints);
        }
        //constraints.gridwidth=1;

    }


    private void clearFields() {
        numeroRectificativa.setText("");
        fechaRectificativa.setValue(currentTime);
        cifClienteRectificativaCombo.setSelectedIndex(0);
        numeroRectificativaCombo.setSelectedIndex(0);
        baseImponibleRectificativa.setText("");
        ivaRectificativa.setText("");
        totalRectificativa.setText("");
        observacionesRectificativa.setText("");
    }


}


