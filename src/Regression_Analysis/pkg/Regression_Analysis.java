package Regression_Analysis.pkg;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Vector;

public class Regression_Analysis extends JFrame {

    public static Regression_Analysis Regression_Analysis;
    private JPanel contentPane;
    private JButton btnGenerate;
    private JButton buttonCancel;
    public JTable tblDataset;
    private JFormattedTextField tfXaxis;
    private JFormattedTextField tfYaxis;
    private JButton btnAdd_Entry;
    public JComboBox<String> cmbDataset;
    private JButton btnAddSeries;
    private DefaultTableModel model;
    private String Combo_Option;
    public static Vector<Vector<Object>> list;
    public Object[][] table;
    public Vector<String> Datasetlist;


    public Regression_Analysis() {
        super("Regression Analysis");

        setContentPane(contentPane);
        setResizable(false);
        tblDataset.getTableHeader().setReorderingAllowed(false);
        createtable();
//        contentPane.setPreferredSize(new Dimension(500, 500));
        getRootPane().setDefaultButton(btnAdd_Entry);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/Image/007-boar.png")));
        setIconImage(icon.getImage());

        //ACTION LISTENERS
        btnGenerate.addActionListener(e -> onGenerate());
        buttonCancel.addActionListener(e -> onCancel());
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnAdd_Entry.addActionListener(e -> onAdd_Entry());

        cmbDataset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cmbDataset_x = (JComboBox) e.getSource();
                Combo_Option = (String) cmbDataset_x.getSelectedItem();

//                JOptionPane.showMessageDialog(Regression_Analysis,"What is this "+option);
            }
        });

        list = new Vector<>();
        list.add(new Vector<Object>());

// Adding 3 to R0 created above x(R0, C0)
//        where column and row starts at 0
//        x.get(row).add(column, element to insert);
//        new ArrayList<>(Arrays.asList(array));


    }

    public void OnAddtable() {


    }


    private void createtable() {

        String[] column = new String[]{"X", "Y"};
        this.model = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDataset.setModel(model);

        TableColumnModel columns = tblDataset.getColumnModel();
        columns.getColumn(0).setMinWidth(100);
        columns.getColumn(1).setMinWidth(100);

        new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer;
        centerRenderer = (DefaultTableCellRenderer) tblDataset.getTableHeader().getDefaultRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setHorizontalAlignment(JLabel.HORIZONTAL);

        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);


    }

    private void onGenerate() {
       /*
        try {
            DriverManager.registerDriver(new JDBC());
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Databases/Database.db");
//            Statement statement = conn.createStatement();

//            statement.execute("Create table IF NOT EXISTS (?)" +
//                    "(_id INTEGER PRIMARY KEY,  Column_1 INTEGER not null, Column_2 INTEGER not null)");


//IF NOT EXISTS
            *//*for (String i : Datasetlist) {
                String query = "Create table " + i + " (_id INTEGER PRIMARY KEY,  Column_1 INTEGER not null, Column_2 INTEGER not null)";
                Statement stmt = conn.createStatement();
                stmt.execute(query);
            }*//*


            DefaultTableModel tblMODEL = (DefaultTableModel) tblDataset.getModel();

            if (tblMODEL.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table must not be empty", "Empty Table", JOptionPane.ERROR_MESSAGE);
            } else {
                String x, y, z;

                for (int i = 0; i < tblMODEL.getRowCount(); i++) {

                    x = tblMODEL.getValueAt(i, 0).toString();
                    y = tblMODEL.getValueAt(i, 1).toString();

                    String query = "insert into Series(Series_Name,Column_1,Column_2) values(?,?,?)";

                    PreparedStatement prepareStatement = conn.prepareStatement(query);
                    prepareStatement.setString(2, x);
                    prepareStatement.setString(3, y);
                    prepareStatement.execute();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Chart_Title chart_title = new Chart_Title(Regression_Analysis);
        chart_title.pack();
        chart_title.setLocationRelativeTo(Regression_Analysis);
        chart_title.setVisible(true);
    }

    public void CreateComboBox() {


    }

    private void onCancel() {
        int choice = JOptionPane.showConfirmDialog(Regression_Analysis, "Delete Table?", "Erase Table", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == 0) {
            this.model.setRowCount(0);
        }

    }

    private void onAdd_Entry() {
        if (tfXaxis.getText().isEmpty() || tfYaxis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Regression_Analysis, "Enter number in fields", "Enter Data", JOptionPane.ERROR_MESSAGE);
        } else {

            Object[] row = new Object[]{Double.parseDouble(tfXaxis.getText()), Double.parseDouble(tfYaxis.getText())};
            model.addRow(row);
            tfXaxis.setText("0");
            tfYaxis.setText("0");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JDialog.setDefaultLookAndFeelDecorated(true);
        Regression_Analysis = new Regression_Analysis();
        Regression_Analysis.pack();
        Regression_Analysis.setLocationRelativeTo(null);
        Regression_Analysis.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Regression_Analysis.setVisible(true);
    }

    public void createUIComponents() {


        String[] petStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
//        Chart_Title Series_information = new Chart_Title();

//        String[] obj = new String[]{};
        Datasetlist = new Vector<>();

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amountFormat.setGroupingUsed(false);
        tfXaxis = new JFormattedTextField(amountFormat);
        tfXaxis.setHorizontalAlignment(JTextField.CENTER);
        tfYaxis = new JFormattedTextField(amountFormat);
        tfYaxis.setHorizontalAlignment(JTextField.CENTER);
        cmbDataset = new JComboBox<>(Datasetlist);
//        cmbDataset.setModel(new DefaultComboBoxModel<String>());


//        cmbDataset.setSelectedIndex(1);
//        JOptionPane.showMessageDialog(Regression_Analysis,"what does this do"+ obj[cmbDataset.getSelectedIndex()]);


//        cmbDataset = new JComboBox(Series_information.Chart_Information.toArray());
//        cmbDataset.setSelectedIndex(4);


    }

}









