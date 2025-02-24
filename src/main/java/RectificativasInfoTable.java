import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;



public class RectificativasInfoTable{

    private DefaultTableModel model;
    private JTable table;


    public RectificativasInfoTable() {
        // Create a new JFrame
        JFrame frame = new JFrame("Datos de rectificativas");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());


        // Create a DefaultTableModel and JTable
        Rectificativas rectificativa = new Rectificativas();
        model = rectificativa.obtener_rectificativas();
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