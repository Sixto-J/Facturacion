import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;


public class ClientesInfoTable {

    private DefaultTableModel model;
    private JTable table;
    private Object originalValue;


    public ClientesInfoTable() {
        // Create a new JFrame
        JFrame frame = new JFrame("Datos de clientes");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());

        Clientes cliente = new Clientes();
        // Create a DefaultTableModel and JTable
        model = cliente.obtener_clientes();

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Set the model to the table
        table.setModel(model);

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // JButton to eliminate row // Create a delete button
        JButton deleteButton = new JButton("Delete Selected Row");
        deleteButton.setPreferredSize(new Dimension(120,50));

        // Set the frame visibility
        frame.setVisible(true);



        // Add a TableModelListener to handle edits
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Check if the event is for an edit
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if(!isResettingValue) {
                        validateCell(row, column);
                    }

                }
            }
        });

        // Set a custom cell editor for all columns
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean stopCellEditing() {
                    boolean valid = super.stopCellEditing();
                    if (!valid) {
                        // If the value is invalid, revert to the original value
                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();

                        model.setValueAt(originalValue, row, column);
                    }
                    return valid;
                }
            });
        }

    }


    boolean isResettingValue = false; // Flag to prevent re-triggering validation

    private void validateCell(int row, int column) {


        int[] numbers = {1,2,3,4,5,6,8,9,10,11,12,13}; // Example array of numbers
        // The value you want to check

        boolean found = false; // Flag to indicate if the value is found

        // Loop through the array to check for the value
        for (int number : numbers) {
            if (number == column) {
                found = true; // Set the flag to true if a match is found
                break; // Exit the loop early since we found a match
            }
        }

        if(found){

            Object newValue = model.getValueAt(row, column);
            int id = (int) table.getValueAt(row, 0);

            System.out.println("New Value: "+newValue.toString().trim());
            // if(!Objects.equals(((JTextField) newValue).getText(), "")){
            if (!newValue.toString().trim().isEmpty()) {


                int[] digit_columns = {11,12};
                int[] string_columns = {1,2,3,4,5,6,8,9,10,13};

                boolean is_a_digit_column = Arrays.stream(digit_columns).anyMatch(num -> num == column);
                boolean is_a_string_column = Arrays.stream(string_columns).anyMatch(num -> num == column);

                System.out.println("ID: "+id);

                if(is_a_digit_column){

                    if(newValue instanceof String){

                        String stringValue = (String) newValue;

                        try {

                            if (stringValue.matches("^\\d+(\\.\\d+)?$")) {

                                updateRowDatabase(id, column, newValue);

                            }else{
                                throw new NumberFormatException("No has introducido sólamente dígitos y puntos para decimales.");
                            }
                        } catch (NumberFormatException e) {
                            // Show an error message
                            JOptionPane.showMessageDialog(table, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                            // Revert to the original value
                            isResettingValue=true;
                            model.setValueAt(originalValue, row, column);
                            isResettingValue=false;

                        }
                    }
                }


                if(is_a_string_column){
                    updateRowDatabase(id, column, newValue);
                }


            }else{
                JOptionPane.showMessageDialog(null, "No puede quedar el campo vacío",
                        "Information", JOptionPane.INFORMATION_MESSAGE);

                isResettingValue=true;
                model.setValueAt(originalValue, row, column);
                isResettingValue=false;
            }

        }else{
            JOptionPane.showMessageDialog(table,
                    "No se puede actualizar este campo!\nPrueba a cambiar campos que no sean CifCliente o idCliente",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);

            isResettingValue=true;
            model.setValueAt(originalValue, row, column);
            isResettingValue=false;

        }
        //System.out.println("Row " + row + " Column " + column + " edited. New value: " + newValue); // Trigger any additional action here
    }


    private void updateRowDatabase(int id, int column, Object newValue) {
        String columnName = "";
        switch (column) {
            case 1:
                columnName = "nombreCliente";
                break;
            case 2:
                columnName = "direccionCliente";
                break;
            case 3:
                columnName = "cpCliente";
                break;
            case 4:
                columnName = "poblacionCliente";
                break;
            case 5:
                columnName = "provinciaCliente";
                break;
            case 6:
                columnName = "paisCliente";
                break;
            case 8:
                columnName = "telCliente";
                break;
            case 9:
                columnName = "emailCliente";
                break;
            case 10:
                columnName = "ibanCliente";
                break;
            case 11:
                columnName ="riesgoCliente";
                break;
            case 12:
                columnName = "descuentoCliente";
                break;
            case 13:
                columnName = "observacionesCliente";
                break;
            default:
                System.out.println("Invalid column index: " + column);
                return; // Exit the method if the column index is invalid
        }

        String updateQuery = "UPDATE clientes SET " + columnName + " = ? WHERE idCliente = ?"; // Replace with your table name

        try (
                ConexionDB cdb = new ConexionDB();
                Connection connection = cdb.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            System.out.println("Updated " + columnName + " for ID " + id + " to " + newValue);

            JOptionPane.showMessageDialog(table, " Se ha realizado con éxito la modificación",
                    "Information", JOptionPane.INFORMATION_MESSAGE);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteRowFromDatabase(int id) {
        String deleteQuery = "DELETE FROM clientes WHERE id = ?"; // Replace with your table name

        try (ConexionDB cdb = new ConexionDB();
             Connection connect = cdb.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted row with ID: " + id);

                JOptionPane.showMessageDialog(table, " Se ha borrado con éxito el registro",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No row found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

            /*
            Sample client data
            String[] columnNames = {"Client ID", "Name", "Email", "Phone"};

            String[] columnNames = {"id", "nombreCliente", "pais", "direccionCliente", "cpCliente",
             "poblacionCliente", "provinciaCliente",
                    "cifCliente", "telCliente", "emailCliente", "ibanCliente",
                    "riesgoCliente", "descuentoCliente", "observacionesCliente"};
            Object[][] data = {
                    {1, "John Doe", "john@example.com", "123-456-7890"},
                    {2, "Jane Smith", "jane@example.com", "098-765-4321"},
                    {3, "Alice Johnson", "alice@example.com", "555-123-4567"},
                    {4, "Bob Brown", "bob@example.com", "444-987-6543"}
            };
            Clientes clientes_aux = new Clientes();
            List <Clientes> lista_clientes = clientes_aux.obtenerClientes();
            for (Clientes cliente : lista_clientes) { System.out.println(cliente); }
            System.out.println("Tamaño del array bidimensional: "+ lista_clientes.size());

            // Convert List<String> to String[][]
            String[][] clientesArray = new String[lista_clientes.size()][1]; // 2D array with 1 column
            for (int i = 0; i < lista_clientes.size(); i++) {
                clientesArray[i][0] = lista_clientes.get(i).toString(); // Assign each customer to a row
            }

            Trigger any additional action here
            String query = "UPDATE clientes SET ContactName =" + newValue +",  City = 'Frankfurt'" +
            "  WHERE CustomerID = 1;"; */