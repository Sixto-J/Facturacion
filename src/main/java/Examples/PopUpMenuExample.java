package Examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUpMenuExample extends JFrame {

    private JTextField fichasField;
    private JPopupMenu popupMenu;

    public PopUpMenuExample() {
        setTitle("Custom Dropdown List Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create a text field
        fichasField = new JTextField(15);
        fichasField.setText("Fichas");
        fichasField.setEditable(false); // Make it non-editable to mimic dropdown behavior

        // Create a popup menu
        popupMenu = new JPopupMenu("PopUp");

        // Add items to the popup menu
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        for (String item : items) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(new MenuItemActionListener());
            popupMenu.add(menuItem);
        }

        // Add mouse listener to the text field to show the popup menu
        fichasField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(fichasField, 0, fichasField.getHeight());
            }
        });

        // Add components to the frame
        add(fichasField);
    }

    private class MenuItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem) e.getSource();
            fichasField.setText(source.getText()); // Set the selected item in the text field
            popupMenu.setVisible(false); // Hide the popup menu
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PopUpMenuExample example = new PopUpMenuExample();
            example.setVisible(true);
        });
    }
}