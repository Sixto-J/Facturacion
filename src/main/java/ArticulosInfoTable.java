
import javax.swing.*;
        import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ArticulosInfoTable {

    private DefaultTableModel model;
    private JTable table;

    public ArticulosInfoTable() {

        // Create a new JFrame
        JFrame frame = new JFrame("Datos de artículos");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());

/*
        String[] columnNames = {"idArticulo", "codigoArticulo", "codigoBarrasArticulo", "descripcionArticulo",
                "costeArticulo", "margenComercialArticulo", "pvpArticulo", "proveedorArticulo",
                "stockArticulo", "observacionesArticulo", "familiaArticulo"};*/





        Articulos articulo = new Articulos();
        model = articulo.obtener_articulos();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Set the model to the table
        table.setModel(model);

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


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


                    int[] numbers = {3, 4, 5, 6, 8, 9}; // Example array of numbers
                    int valueToCheck = column; // The value you want to check

                    boolean found = false; // Flag to indicate if the value is found

                    // Loop through the array to check for the value
                    for (int number : numbers) {
                        if (number == valueToCheck) {
                            found = true; // Set the flag to true if a match is found
                            break; // Exit the loop early since we found a match
                        }
                    }


                       if(found){

                           Object newValue = model.getValueAt(row, column);
                           int id = (int) table.getValueAt(row, 0);

                           updateRowDatabase(id, column, newValue);


                       }else{
                           JOptionPane.showMessageDialog(null,
                                   "No se puede actualizar este campo!\nPrueba a cambiar el stock, pvp, observaciones o coste",
                                   "Information",
                                   JOptionPane.INFORMATION_MESSAGE);
                       }

                    //System.out.println("Row " + row + " Column " + column + " edited. New value: " + newValue);
                    // Trigger any additional action here

                }
            }
        });



        // Create a delete button // JButton to eliminate row
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
        frame.add(deleteButton, BorderLayout.AFTER_LAST_LINE);

    }

    private static void updateRowDatabase(int id, int column, Object newValue) {
        String columnName = "";
        switch (column) {
            case 1:
                columnName = "codigoArticulo";
                break;
            case 2:
                columnName = "codigoBarrasArticulo";
                break;
            case 3:
                columnName = "descripcionArticulo";
                break;
            case 4:
                columnName = "costeArticulo";
                break;
            case 5:
                columnName = "margenComercialArticulo";
                break;
            case 6:
                columnName = "pvpArticulo";
                break;
            case 7:
                columnName = "proveedorArticulo";
                break;
            case 8:
                columnName = "stockArticulo";
                break;
            case 9:
                columnName = "observacionesArticulo";
                break;
            case 10:
                columnName = "familiaArticulo";
        }

        String updateQuery = "UPDATE articulos SET " + columnName + " = ? WHERE id = ?"; // Replace with your table name

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
        String deleteQuery = "DELETE FROM articulos WHERE id = ?"; // Replace with your table name

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
