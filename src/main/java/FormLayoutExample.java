import javax.swing.*;
import java.awt.*;

public class FormLayoutExample extends JFrame {

    public FormLayoutExample() {
        setTitle("Form Layout Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for labels and text fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Create and add labels and text fields
        for (int i = 0; i < 12; i++) {
            JLabel label = new JLabel("Label " + (i + 1));
            JTextField textField = new JTextField(15); // 15 columns wide

            gbc.gridx = 0; // Column for label
            gbc.gridy = i; // Row for label
            formPanel.add(label, gbc);

            gbc.gridx = 1; // Column for text field
            formPanel.add(textField, gbc);
        }

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        // Create a table
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
                {"Data 1", "Data 2", "Data 3"},
                {"Data 4", "Data 5", "Data 6"},
                {"Data 7", "Data 8", "Data 9"},
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Add components to the main frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(tableScrollPane, BorderLayout.EAST); // You can adjust the position as needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormLayoutExample example = new FormLayoutExample();
            example.setVisible(true);
        });
    }
}