import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;



public class FacturasInfoTable {

    private DefaultTableModel model;
    private JTable table;

    public FacturasInfoTable() {
        // Create a new JFrame
        JFrame frame = new JFrame("Datos de facturas");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());


 /*       String[] columnNames = { "ID", "NumeroFactura", "FechaFactura", "IDCliente",
                "BaseImponible", "IVA", "Total", "Hash", "QR", "Cobrada", "FormaCobro",
                "FechaCobro","Observaciones"};*/




        // Create a DefaultTableModel and JTable
        Facturas factura = new Facturas();
        model = factura.obtener_facturas();
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
