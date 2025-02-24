
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;


public class ProveedoresInfoTable {

    private DefaultTableModel model;
    private JTable table;
    private Object originalValue;

    public ProveedoresInfoTable() {
        // Create a new JFrame
        JFrame frame = new JFrame("Datos de proveedores");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());
        

        Proveedores proveedor = new Proveedores();
        // Create a DefaultTableModel and JTable
        model = proveedor.obtener_proveedores();
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

        // Incluir un boton para eliminar el proveedor
        frame.add(deleteButton, BorderLayout.AFTER_LAST_LINE);

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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is the ID
                    deleteRowFromDatabase(id);
                    model.removeRow(selectedRow); // Remove from the table model
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
                }
            }
        });

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
                columnName = "nombreProveedor";
                break;
            case 2:
                columnName = "direccionProveedor";
                break;
            case 3:
                columnName = "cpProveedor";
                break;
            case 4:
                columnName = "poblacionProveedor";
                break;
            case 5:
                columnName = "provinciaProveedor";
                break;
            case 6:
                columnName = "paisProveedor";
                break;
            case 8:
                columnName = "telProveedor";
                break;
            case 9:
                columnName = "emailProveedor";
                break;
            case 10:
                columnName = "ibanProveedor";
                break;
            case 11:
                columnName = "riesgoProveedor";
                break;
            case 12:
                columnName = "descuentoProveedor";
                break;
            case 13:
                columnName = "observacionesProveedor";
                break;
            default:
                System.out.println("Invalid column index: " + column);
                return; // Exit the method if the column index is invalid

        }

        String updateQuery = "UPDATE proveedores SET " + columnName + " = ? WHERE idProveedor = ?"; // Replace with your table name

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
        String deleteQuery = "DELETE FROM proveedores WHERE idProveedor = ?"; // Replace with your table name

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