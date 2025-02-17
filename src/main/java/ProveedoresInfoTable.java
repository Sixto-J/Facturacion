
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



public class ProveedoresInfoTable {

    private DefaultTableModel model;
    private JTable table;

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



        // Add a TableModelListener to handle edits
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Check if the event is for an edit
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 0 | column == 7){

                        JOptionPane.showMessageDialog(null,
                                "No se puede actualizar este campo!\nPrueba a cambiar campos que no sean CIF o el id del proveedor",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);

                    }else {
                        Object newValue = model.getValueAt(row, column);
                        int id = (int) table.getValueAt(row, 0);

                        updateRowDatabase(id, column, newValue);

                        System.out.println("Row " + row + " Column " + column + " edited. New value: " + newValue);
                    }

                }
            }
        });




        // JButton to eliminate row // Create a delete button
        JButton deleteButton = new JButton("Delete Selected Row");
        deleteButton.setPreferredSize(new Dimension(120,50));

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
        
        
        // Incluir un boton para eliminar el proveedor
        frame.add(deleteButton, BorderLayout.AFTER_LAST_LINE);



        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


        // Set the frame visibility
        frame.setVisible(true);

    }

    private static void updateRowDatabase(int id, int column, Object newValue) {
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
        }

        String updateQuery = "UPDATE proveedores SET " + columnName + " = ? WHERE id = ?"; // Replace with your table name

        try (
                ConexionDB cdb = new ConexionDB();
                Connection connection = cdb.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            System.out.println("Updated " + columnName + " for ID " + id + " to " + newValue);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void deleteRowFromDatabase(int id) {
        String deleteQuery = "DELETE FROM proveedores WHERE id = ?"; // Replace with your table name

        try (ConexionDB cdb = new ConexionDB();
             Connection connect = cdb.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted row with ID: " + id);
            } else {
                System.out.println("No row found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}