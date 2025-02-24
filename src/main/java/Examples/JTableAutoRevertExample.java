package Examples;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;


public class JTableAutoRevertExample extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Object[][] data = {
            {"John", 25},
            {"Alice", 30},
            {"Bob", 22}
    };
    private String[] columnNames = {"Name", "Age"};
    private Object originalValue; // To store the original value

    public JTableAutoRevertExample() {
        setTitle("JTable Auto Revert Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the table model and table
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel) {
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                TableCellEditor editor = super.getCellEditor(row, column);
                if (editor != null) {
                    // Store the original value before editing
                    originalValue = getValueAt(row, column);
                }
                return editor;
            }
        };

        // Add a TableModelListener to track changes
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    // Validate the new value
                    validateCell(row, column);
                }
            }
        });

        // Set a custom cell editor for the "Age" column
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                boolean valid = super.stopCellEditing();
                if (!valid) {
                    // If the value is invalid, revert to the original value
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    tableModel.setValueAt(originalValue, row, column);
                }
                return valid;
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void validateCell(int row, int column) {
        // Example validation: Age must be a number between 0 and 120
        if (column == 1) { // Assuming column 1 is "Age"
            try {
                int age = Integer.parseInt(tableModel.getValueAt(row, column).toString());
                if (age < 0 || age > 120) {
                    throw new NumberFormatException("Age must be between 0 and 120.");
                }
            } catch (NumberFormatException e) {
                // Show an error message
                JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                // Revert to the original value
                tableModel.setValueAt(originalValue, row, column);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTableAutoRevertExample example = new JTableAutoRevertExample();
            example.setVisible(true);
        });
    }
}

