import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;



public class FacturasInfoTable {

    private DefaultTableModel model;
    private JTable table;

    public FacturasInfoTable() {
        // Create a new JFrame
        JFrame frame = new JFrame("Facturas");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());


        String[] columnNames = { "ID", "NumeroFactura", "FechaFactura", "IDCliente",
                "BaseImponible", "IVA", "Total", "Hash", "QR", "Cobrada", "FormaCobro",
                "FechaCobro","Observaciones"};



        // Create a DefaultTableModel and JTable
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Establish database connection
        try (ConexionDB cdb = new ConexionDB();
             Connection connection = cdb.getConnection();
             Statement statement = connection.createStatement()) {

            // Execute SELECT query
            String query = "SELECT * FROM facturasClientes"; // Replace with your table name
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata to retrieve column names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Add column names to the model
            Vector<String> column_Names = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                column_Names.add(metaData.getColumnName(i));
            }
            model.setColumnIdentifiers(column_Names);



            // Add rows to the model
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set the frame visibility
        frame.setVisible(true);

    }

}
