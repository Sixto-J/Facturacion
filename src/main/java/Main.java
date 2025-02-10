import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main extends JFrame {


    private JTextArea textArea;
    private JButton generateFactura, verInfoClientes, verInfoArticulos, verFacturas,
            generarFormularioClientes, generarFormularioArticulos;


    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }


    public Main() {
        setTitle("Facturacion");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        generateFactura = new JButton("Generar Factura");
        generateFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //generateXML();
                new FacturasFormInputData();
            }
        });

        verInfoClientes = new JButton("InfoClientes");
        verInfoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ClientesInfoTable();

            }
        });

        verInfoArticulos = new JButton("InfoArticulos");
        verInfoArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ArticulosInfoTable();

            }
        });

        verFacturas = new JButton("InfoFacturas");
        verFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new FacturasInfoTable();
            }
        });

        generarFormularioClientes = new JButton("Crear Clientes");
        generarFormularioClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ClientesFormInputData();

            }
        });

        generarFormularioArticulos= new JButton("Crear Articulos");
        generarFormularioArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ArticulosFormInputData();

            }
        });


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(generateFactura);
        panel.add(verFacturas);
        panel.add(generarFormularioClientes);
        panel.add(verInfoClientes);
        panel.add(generarFormularioArticulos);
        panel.add(verInfoArticulos);

        add(panel, BorderLayout.CENTER);
    }


}