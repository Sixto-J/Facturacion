import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LineasFacturaInfoTable {

    private DefaultTableModel model;
    private JTable table;

    public LineasFacturaInfoTable(int idFacturaCliente) {

        JFrame frame = new JFrame("Datos de lineas de facturas");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());



        LineasFactura lineas_factura = new LineasFactura();
        model = lineas_factura.obtener_lineas_factura(idFacturaCliente);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(model);

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set the frame visibility
        frame.setVisible(true);

    }



}
