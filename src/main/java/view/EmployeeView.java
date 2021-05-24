package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * class that implements the Observer interface to represent the view of an employee that needs to be notified
 * each time an order is placed
 */
public class EmployeeView extends AppFrame implements Observer {
    private String[] columns = {"order id","client id", "date","products","price"};
    private DefaultTableModel model = new DefaultTableModel(columns,0);
    private JTable table ;
    private JScrollPane pane;
    private JButton done = new JButton("done");

    @Override
    public void update(Observable o, Object orderDetails) {
        String[] string = (String[]) orderDetails;
        model.addRow(string);
        updateTable();
    }

    public EmployeeView(){
        this.setTitle("Employee View");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(false);
    }

    /**
     * Method to initialize all the components in the panel, set their sizes, positions and color
     * @param panel the panel in the JFrame
     */
    private void initializeForm(JPanel panel){
        pane = new JScrollPane();
        pane.setBounds(15,50,770,300);
        panel.add(pane);
        done.setBounds(380,380,70,30);
        panel.add(done);
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();
                for(int i :rows)
                model.removeRow(i);
                updateTable();
            }
        });
    }

    /**
     * method called in the update method to add the new order to the JTable
     */
    private void updateTable(){
        table = new JTable(model);
        table.setRowSelectionAllowed(true);
        pane.getViewport().add(table);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if(column == 3){
                        new ComponentsItemFrame((String) table.getValueAt(row,column));
                    }

                }
            }
        });
    }


    public  void start(){
        this.setVisible(true);
    }

}
