package Examples;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerarXML extends Component {

    private JTextArea textArea;


    private void generateXML() {
        String inputData = textArea.getText();
        String[] lines = inputData.split("\n");

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<ShoppingBill>\n");

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    String itemPrice = parts[1].trim();
                    xmlBuilder.append("    <Item>\n");
                    xmlBuilder.append("        <Name>").append(itemName).append("</Name>\n");
                    xmlBuilder.append("        <Price>").append(itemPrice).append("</Price>\n");
                    xmlBuilder.append("    </Item>\n");
                }
            }
        }


        xmlBuilder.append("</ShoppingBill>");

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("shopping_bill.xml"))) {
            writer.write(xmlBuilder.toString());
            JOptionPane.showMessageDialog(this, "XML file generated successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
        }

    }

    //generateXML();

}
