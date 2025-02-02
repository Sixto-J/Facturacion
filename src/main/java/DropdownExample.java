import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropdownExample {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Dropdown Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create an array of items for the dropdown
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};

        // Create a JComboBox with the items
        JComboBox<String> dropdown = new JComboBox<>(items);

        // Add an ActionListener to handle item selection
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item
                String selectedItem = (String) dropdown.getSelectedItem();
                // Display the selected item
                JOptionPane.showMessageDialog(frame, "You selected: " + selectedItem);
            }
        });

        // Add the dropdown to the frame
        frame.getContentPane().add(dropdown);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
