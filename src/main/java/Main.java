import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class Main extends JFrame {

    private JButton generateFactura, verInfoClientes, verInfoArticulos, verFacturas, generarFormularioClientes, generarFormularioArticulos;

    private JTextArea textArea;
    private JTextField fichasField = null;
    private JTextField FacturasField = null;
    private JTextField RectificativasField= null;
    private JTextField ListadosField =null;
    private JTextField ConfiguracionField=null;
    private JTextField AyudaField=null;

    private JPopupMenu popupMenuFichas = null;
    private JPopupMenu popupMenuFacturas = null;
    private JPopupMenu popupMenuRectificativas = null;
    private JPopupMenu popupMenuListados = null;
    private JPopupMenu popupMenuConfiguracion = null;
    private JPopupMenu popupMenuAyuda = null;

    private String[] nombresFichas = {"Fichas","Facturas","Rectificativas","Listados","Configuracion","Ayuda"};
    private JTextField[] textFields = {fichasField,FacturasField,RectificativasField,ListadosField,ConfiguracionField,AyudaField};
    private JPopupMenu[] popupMenus = {popupMenuFichas,popupMenuFacturas,popupMenuRectificativas,popupMenuListados,popupMenuConfiguracion,popupMenuAyuda};

    private String[] itemsFichas = {"Clientes", "Articulos", "Proveedores"}; //"TiposIVA", "FamiliaProductos",
    private String[] itemsFacturas = {"Crear Factura","Ver Factura"};
    private String[] itemsRectificativas = {"Crear Rectificativa","Ver Rectificativa"};
    private String[] itemsListados = {"ListadoClientes","ListadoArticulos","ListadoProveedores"}; //"ListadoTiposIVA", "ListadoFamiliaProductos",
    private String[] itemsConfiguracion = {"Datos de la empresa"};
    private String[] itemsAyuda = {"Manual de usuario","Acerca de"};

    private ArrayList<String[]> items_lista = new ArrayList<>(Arrays.asList(itemsFichas,itemsFacturas,itemsRectificativas,itemsListados,itemsConfiguracion,itemsAyuda));




    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }


    public Main() {

        JPanel panel = new JPanel(); // Add components to the frame

        panel.setLayout(new FlowLayout());

        setTitle("Facturacion");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);



        // Loop para crear los menus desplegables con su respectivas cabeceras, elementos desplegables, a su vez con listeners para cada elemento
        for (int i = 0; i < nombresFichas.length; i++) {


            // Create a text field for Fichas
            textFields[i] = new JTextField(15);
            textFields[i].setText(nombresFichas[i]);
            textFields[i].setEditable(false); // Make it non-editable to mimic dropdown behavior
            // Create a popup menu
            popupMenus[i] = new JPopupMenu("");
            // Add items to the popup menu


            String[] posicion_lista=items_lista.get(i);

                for (String item: posicion_lista ){
                    JMenuItem menuItem = new JMenuItem(item);
                    menuItem.addActionListener(new MenuItemActionListener(popupMenus[i]));
                    popupMenus[i].add(menuItem);
                }


            final int index = i;
            // Add mouse listener to the text field to show the popup menu
            textFields[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    popupMenus[index].show(textFields[index], 0, textFields[index].getHeight());
                }
            });

            panel.add(textFields[i]);


        }

        add(panel, BorderLayout.CENTER);
        pack();
    }

    class MenuItemActionListener implements ActionListener {

        private JPopupMenu popupMenu;

        // Constructor to accept the popup menu
        public MenuItemActionListener(JPopupMenu popupMenu) {
            this.popupMenu = popupMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem) e.getSource();

            if(source.getText().equals("Clientes")){
                new ClientesFormInputData();
            }
            if(source.getText().equals("Articulos")){
                new ArticulosFormInputData();
            }
            if(source.getText().equals("Proveedores")){
                new ProveedoresFormInputData();
            }
            if(source.getText().equals("ListadoClientes")){
                new ClientesInfoTable();
            }
            if(source.getText().equals("ListadoArticulos")){
                new ArticulosInfoTable();
            }
            if(source.getText().equals("ListadoProveedores")){
                new ProveedoresInfoTable();
            }
            if(source.getText().equals("Ver Factura")){
                new FacturasInfoTable();
            }
            if(source.getText().equals("Crear Factura")){
                new FacturasFormInputData();
            }
            if(source.getText().equals("Ver Rectificativa")){
                new RectificativasInfoTable();
            }
            if(source.getText().equals("Crear Rectificativa")){
                new RectificativasFormInputData();
            }
            if(source.getText().equals("TiposIVA")){
                //new TiposIVAFormInputData();
            }
            if(source.getText().equals("FamiliaProductos")){
                //new FamiliaProductosFormInputData();
            }
            if(source.getText().equals("ListadoTiposIVA")){
                //new TiposIVAInfoTable();
            }
            if(source.getText().equals("ListadoFamiliaProductos")){
                //new FamiliaProductosInfoTable();
            }

            popupMenu.setVisible(false); // Hide the popup menu

            //fichasField.setText(source.getText());
            // Set the selected item in the text field
        }
    }

}


//generarFormularioClientes = new JButton("Crear Clientes");
//generarFormularioArticulos= new JButton("Crear Articulos");
//verInfoClientes = new JButton("InfoClientes");
//verInfoArticulos = new JButton("InfoArticulos");
//verFacturas = new JButton("InfoFacturas");
//generateFactura = new JButton("Generar Factura");


/*  panel.add(generateFactura); panel.add(verFacturas); panel.add(generarFormularioClientes);
    panel.add(verInfoClientes); panel.add(generarFormularioArticulos); panel.add(verInfoArticulos);*/