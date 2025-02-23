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



        JLabel numeroRectificativaClienteLabel = new JLabel("Número Rectificativa de cliente");
        numeroRectificativa = new JTextField();
        checkListener(numeroRectificativa);


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
        checkListener(baseImponibleRectificativa);


        JLabel ivaFacturaClienteLabel = new JLabel("IVA");
        ivaRectificativa = new JTextField();
        checkListener(ivaRectificativa);


        JLabel totalRectificativaClienteLabel = new JLabel("Total");
        totalRectificativa = new JTextField();
        checkListener(totalRectificativa);

        JLabel observacionesRectificativaLabel = new JLabel("Observaciones");
        observacionesRectificativa = new JTextField();
        checkListener(observacionesRectificativa);



        // Add components to the frame with gbc values
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 2, 10); // Add padding around components

        JPanel formPanel = new JPanel(new GridBagLayout());

        settingGrBLayout(formPanel,gbc, 0, 0,numeroRectificativaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 1,fechaRectificativaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 2,buscadorCifClientesLabel);
        settingGrBLayout(formPanel,gbc, 0, 3,buscadornumeroFacturasLabel);
        settingGrBLayout(formPanel,gbc, 0, 4,baseImponibleFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 5,ivaFacturaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 6,totalRectificativaClienteLabel);
        settingGrBLayout(formPanel,gbc, 0, 7,observacionesRectificativaLabel);


        gbc.ipadx=200;
        gbc.ipady=15;
        settingGrBLayout(formPanel,gbc, 1, 0, numeroRectificativa);
        settingGrBLayout(formPanel,gbc, 1, 1, fechaRectificativa);
        settingGrBLayout(formPanel,gbc, 1, 2, cifClienteRectificativaCombo);
        settingGrBLayout(formPanel,gbc, 1, 3, numeroRectificativaCombo);
        settingGrBLayout(formPanel,gbc, 1, 4, baseImponibleRectificativa);
        settingGrBLayout(formPanel,gbc, 1, 5, ivaRectificativa);
        settingGrBLayout(formPanel,gbc, 1, 6, totalRectificativa);
        settingGrBLayout(formPanel,gbc, 1, 7, observacionesRectificativa);
        gbc.ipadx=0;
        gbc.ipady=0;

        add(formPanel,BorderLayout.WEST);





        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(CrearRectificativaButton);
        add(buttonPanel,BorderLayout.SOUTH);


        // Add action listener for the submit button
        CrearRectificativaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !Objects.equals(numeroRectificativa.getText(), "") &&
                        !Objects.equals(baseImponibleRectificativa.getText(), "") &&
                                !Objects.equals(ivaRectificativa.getText(), "") &&
                                      !Objects.equals(totalRectificativa.getText(), "")) {
                    insertRectificativa();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(RectificativasFormInputData.this, "Los campos deben estar cumplimentados!");

                }
            }
        });


        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void checkListener(JTextField jtf) {

        // Add a DocumentListener to the JTextField
        jtf.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is not used for plain text fields, but must be implemented
                // You can leave it empty
            }

            private void checkInput() {
                String text = jtf.getText();
                // Check if the text contains only digits
                if (!text.matches("\\d*")) { // Matches zero or more digits
                    SwingUtilities.invokeLater(() -> jtf.setText(""));
                }
            }
        });
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


            //System.out.println("Valor de fecha elegida 1: "+fechaFacturaCliente.getValue());
            //System.out.println("Tipo del valor fechaFacturaCliente: " + fechaFacturaCliente.getValue().getClass());


            java.util.Date selectedDate1 = (java.util.Date) fechaRectificativa.getValue();
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate1 = new java.sql.Date(selectedDate1.getTime());

            pstmt.setDate(2, sqlDate1);



            pstmt.setString(3, String.valueOf(cifClienteRectificativaCombo.getSelectedItem()));
            pstmt.setString(3, String.valueOf(numeroRectificativaCombo.getSelectedItem()));

            pstmt.setDouble(4, Double.parseDouble(baseImponibleRectificativa.getText()));
            pstmt.setDouble(5, Double.parseDouble(ivaRectificativa.getText()));
            pstmt.setDouble(6, Double.parseDouble(totalRectificativa.getText()));


            pstmt.setString(7, observacionesRectificativa.getText());


            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Rectificated bill added successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding Rectificated bill: " + ex.getMessage());
        }
    }



    private void setFacturasCombo(JComboBox numeroRectificativaCombo) {

        String cliente_selected= (String) cifClienteRectificativaCombo.getSelectedItem();
        // Display the selected item
        String query_num_factura ="SELECT numeroFacturaCliente FROM facturasClientes fc JOIN clientes c" +
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
        } else if(o instanceof JSpinner){
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