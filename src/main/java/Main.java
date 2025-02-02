import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main extends JFrame {


    private JTextArea textArea;
    private JButton generateButton, generateClientes, generateArticulos, generateFacturas, generarAyuda;


    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }


    public Main() {
        setTitle("Facturacion");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        generateButton = new JButton("Generar Factura");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                generateXML();
            }
        });

        generateClientes = new JButton("Clientes");
        generateClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ClientInfoTable();

            }
        });

        generateArticulos = new JButton("Articulos");
        generateArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        generateFacturas = new JButton("Facturas");
        generateFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });

        generarAyuda = new JButton("Ayuda");
        generarAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(generateButton);
        panel.add(generateClientes);
        panel.add(generateFacturas);
        panel.add(generateArticulos);
        panel.add(generarAyuda);
        add(panel, BorderLayout.NORTH);
    }


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

    }