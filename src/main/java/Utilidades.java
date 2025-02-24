import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Utilidades {


    public Utilidades(){

    }

    /**
     * Metodo que establece un tipo de Font object para todos los elementos hijo respecto el componente indicado:
     * JFrame..
     * @param component
     * @param font
     */
    // Method to set font recursively for all components
    public void setFontRecursively(Component component, Font font) {
        component.setFont(font); // Set the font for the current component

        // If the component is a container, set the font for its children
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setFontRecursively(child, font); // Recursive call for child components
            }
        }
    }


    public void CheckInputListener(JTextField jtf) {

        // Add a DocumentListener to the JTextField
        jtf.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is not used for plain text fields, but must be implemented
                // You can leave it empty
            }

            private void checkInput() {
                String text = jtf.getText();
                // Check if the text contains only digits
                if (!text.matches("\\d*")) { // Matches zero or more digits
                    SwingUtilities.invokeLater(() -> jtf.setText(""));
                }
            }
        });
    }


    public boolean areFieldsFilled(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                // Show a message dialog if any field is empty
                JOptionPane.showMessageDialog(null, "Los campos deben estar cumplimentados!", "Input Error", JOptionPane.WARNING_MESSAGE);
                //Please fill in all fields!
                return false; // Return false if any field is empty
            }
        }
        return true; // Return true if all fields are filled
    }


}
