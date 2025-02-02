import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class ClientInfoTable {

    private DefaultTableModel model;
    private JTable table;

        public ClientInfoTable() {
            // Create a new JFrame
            JFrame frame = new JFrame("Client Information");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());


            //Sample client data
            //String[] columnNames = {"Client ID", "Name", "Email", "Phone"};

            String[] columnNames = {"id", "nombreCliente", "pais", "direccionCliente", "cpCliente", "poblacionCliente", "provinciaCliente",
                    "cifCliente", "telCliente", "emailCliente", "ibanCliente",
                    "riesgoCliente", "descuentoCliente", "observacionesCliente"};


            Object[][] data = {
                    {1, "John Doe", "john@example.com", "123-456-7890"},
                    {2, "Jane Smith", "jane@example.com", "098-765-4321"},
                    {3, "Alice Johnson", "alice@example.com", "555-123-4567"},
                    {4, "Bob Brown", "bob@example.com", "444-987-6543"}
            };


         /*   Clientes clientes_aux = new Clientes();
            List <Clientes> lista_clientes = clientes_aux.obtenerClientes();
            for (Clientes cliente : lista_clientes) { System.out.println(cliente); }
            System.out.println("Tamaño del array bidimensional: "+ lista_clientes.size());

            // Convert List<String> to String[][]
            String[][] clientesArray = new String[lista_clientes.size()][1]; // 2D array with 1 column
            for (int i = 0; i < lista_clientes.size(); i++) {
                clientesArray[i][0] = lista_clientes.get(i).toString(); // Assign each customer to a row
            }*/


            // Create a DefaultTableModel and JTable
            model = new DefaultTableModel(); //clientesArray, columnNames
            table = new JTable(model);


            // Establish database connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion","root", "22_michu");
                 Statement statement = connection.createStatement()) {

                // Execute SELECT query
                String query = "SELECT * FROM clientes"; // Replace with your table name
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




            // Add a TableModelListener to handle edits
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    // Check if the event is for an edit
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        Object newValue = model.getValueAt(row, column);
                        int id = (int) table.getValueAt(row, 0);
                        updateDatabase(id, column, newValue);


                        System.out.println("Row " + row + " Column " + column + " edited. New value: " + newValue);
                        // Trigger any additional action here

                       String query = "UPDATE clientes SET ContactName =" + newValue +",  City = 'Frankfurt'" +
                               "  WHERE CustomerID = 1;";
                    }
                }
            });

            // Add the JTable to a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Set the frame visibility
            frame.setVisible(true);

        }

    private static void updateDatabase(int id, int column, Object newValue) {
        String columnName = "";
        switch (column) {
            case 1:
                columnName = "nombreCliente";
                break;
            case 2:
                columnName = "paisCLiente";
                break;
            case 3:
                columnName = "direccionCliente";
                break;
            case 4:
                columnName = "cpCliente";
                break;
            case 5:
                columnName = "cifCliente";
                break;
            case 6:
                columnName = "emailCliente";
                break;
        }

        String updateQuery = "UPDATE clientes SET " + columnName + " = ? WHERE id = ?"; // Replace with your table name

        try (Connection connection = ConexionDB.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            System.out.println("Updated " + columnName + " for ID " + id + " to " + newValue);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }