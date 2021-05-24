package view;



import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import model.CompositeProduct;
import model.MenuItem;
import org.apache.commons.lang3.StringUtils;

public class AdministratorView extends AppFrame{

    private JTable products;
    private JScrollPane pane;
    private int[] rows;


    private JButton reportClients =  new JButton("show report");
    private JButton reportProductsInDay = new JButton("show report");
    private JButton reportFavouriteProducts = new JButton("show report");
    private JButton reportOrdersInTime = new JButton("show report");

    private JButton addProduct = new JButton("ADD");
    private JButton addMenu = new JButton("ADD MENU");

    private JButton deleteProduct = new JButton("DELETE");
    private JButton updateProduct = new JButton("UPDATE");

    private JButton importProducts = new JButton("Import products");

    private JTextField nameText = new JTextField();
    private JTextField caloriesText = new JTextField();
    private JTextField proteinsText = new JTextField();
    private JTextField fatText = new JTextField();
    private JTextField sodiumText = new JTextField();
    private JTextField ratingText = new JTextField();
    private JTextField priceText = new JTextField();
    private JTextField menuName = new JTextField();

    private JTextField min = new JTextField();
    private JTextField max = new JTextField();
    private JTextField times = new JTextField();
    private JTextField timesClient = new JTextField();
    private JTextField valuesClient = new JTextField();
    private JTextField date = new JTextField();

    private List<MenuItem> menuItems = new ArrayList<>();

    private List<String> itemsNames = new ArrayList<>();


    public AdministratorView(){
        this.setTitle("Administrator View");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        initializeForm(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);

    }
    /**
     * Method to initialize all the components in the panel, set their sizes, positions and color
     * @param panel the panel in the JFrame
     */
    private void initializeForm(JPanel panel){
        pane = new JScrollPane();
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setBounds(15,50,770,500);
        panel.add(pane);
        JLabel name = new JLabel("name");
        name.setBounds(800,50,60,21);
        panel.add(name);
        JLabel calories = new JLabel("calories");
        calories.setBounds(900,110,60,21);
        panel.add(calories);
        JLabel proteins = new JLabel("proteins");
        proteins.setBounds(800,110,60,21);
        panel.add(proteins);
        JLabel fat = new JLabel("fats");
        fat.setBounds(800,170,60,21);
        panel.add(fat);
        JLabel sodium = new JLabel("sodium");
        sodium.setBounds(900,170,60,21);
        panel.add(sodium);
        JLabel rating = new JLabel("rating");
        rating.setBounds(800,230,60,21);
        panel.add(rating);
        JLabel price = new JLabel("price");
        price.setBounds(900,230,60,21);
        panel.add(price);

        nameText.setBounds(800,80,80,27);
        panel.add(nameText);
        caloriesText.setBounds(800,140,80,27);
        panel.add(caloriesText);
        proteinsText.setBounds(900,140,80,27);
        panel.add(proteinsText);
        fatText.setBounds(800,200,80,27);
        panel.add(fatText);
        sodiumText.setBounds(900,200,80,27);
        panel.add(sodiumText);
        ratingText.setBounds(800,260,80,27);
        panel.add(ratingText);
        priceText.setBounds(900,260,80,27);
        panel.add(priceText);

        JLabel timeInt = new JLabel("time interval");
        JLabel productsOrdered = new JLabel("products ordered");
        JLabel timeAndValue = new JLabel("times and value");
        JLabel productsInDay = new JLabel("products on date");
        timeInt.setBounds(100,580,110,21);
        panel.add(timeInt);
        productsOrdered.setBounds(310,580,110,21);
        panel.add(productsOrdered);
        timeAndValue.setBounds(570,580,110,21);
        panel.add(timeAndValue);
        productsInDay.setBounds(800,580,110,21);
        panel.add(productsInDay);

        min.setBounds(115,626,80,27);
        panel.add(min);
        max.setBounds(115,673,80,27);
        panel.add(max);
        times.setBounds(325,626,80,27);
        panel.add(times);
        timesClient.setBounds(585,626,80,27);
        panel.add(timesClient);
        valuesClient.setBounds(585,673,80,27);
        panel.add(valuesClient);
        date.setBounds(815,626,80,27);
        panel.add(date);


        addProduct.setBounds(800,300,150,27);
        panel.add(addProduct);
        updateProduct.setBounds(800,350,150,27);
        panel.add(updateProduct);
        deleteProduct.setBounds(800,400,150,27);
        panel.add(deleteProduct);
        menuName.setBounds(800,450,150,27);
        panel.add(menuName);
        addMenu.setBounds(800,500,150,27);
        panel.add(addMenu);

        importProducts.setBounds(800,550,150,27);
        panel.add(importProducts);

        reportOrdersInTime.setBounds(108, 725,94,27);
        panel.add(reportOrdersInTime);
        reportFavouriteProducts.setBounds(318,725,94,27);
        panel.add(reportFavouriteProducts);
        reportClients.setBounds(578,725,94,27);
        panel.add(reportClients);
        reportProductsInDay.setBounds(808,725,94,27);
        panel.add(reportProductsInDay);

    }

    /**
     * returns a row from a table in the form of a String[]
     * @param row the nr of the row
     * @param table the table
     * @return the row in a String[]
     */
    public String[] getRowAt(int row, JTable table) {
        String[] result = new String[8];

        for (int i = 0; i < 8; i++) {
            result[i] = (String) table.getValueAt(row,i);
        }
        return result;

    }

    /**
     * method to initialize and update the table with data
     */
    public void initializeTable(){
        String[] columnNames = {"Name","Rating", "Calories", "Proteins","Fat", "Sodium", "Price", "Components"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for(MenuItem menuItem: menuItems){
            String[] row;
            row = new String[]{menuItem.getName(), String.valueOf(menuItem.getRating()), String.valueOf(menuItem.getCalories()), String.valueOf(menuItem.getProteins()), String.valueOf(menuItem.computeFats()), String.valueOf(menuItem.getSodium()), String.valueOf(menuItem.getPrice()), " "};
            if(menuItem instanceof CompositeProduct){
                StringBuilder sb = new StringBuilder();
                ((CompositeProduct) menuItem).getItems().forEach(m -> sb.append(m.getName()).append(","));
                String result = StringUtils.chop(sb.toString());
                row[7] = result;
            }
            model.addRow(row);
        }
        products = new JTable(model);
        products.setRowSelectionAllowed(true);
        pane.getViewport().add(products);
        products.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if(column == 7){
                        new ComponentsItemFrame(getRowAt(row, products)[7]);
                    }

                }
            }
        });
    }


    public void addProductActionListener(ActionListener actionListener){
        addProduct.addActionListener(actionListener);
    }

    public void addMenuActionListener(ActionListener actionListener){
        addMenu.addActionListener(actionListener);
    }

    public void deleteActionListener(ActionListener actionListener){
        deleteProduct.addActionListener(actionListener);
    }

    public void updateMenuActionListener(ActionListener actionListener){
        updateProduct.addActionListener(actionListener);
    }

    public void clientsReportActionListener(ActionListener actionListener){
        reportClients.addActionListener(actionListener);
    }

    public void ordersInTimeReportActionListener(ActionListener actionListener){
        reportOrdersInTime.addActionListener(actionListener);
    }
    public void productsInDayReportActionListener(ActionListener actionListener){
        reportProductsInDay.addActionListener(actionListener);
    }
    public void favouriteProductsReportActionListener(ActionListener actionListener){
        reportFavouriteProducts.addActionListener(actionListener);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.itemsNames = menuItems.stream().map(MenuItem::getName).collect(Collectors.toList());
    }

    public void importProductsActionListener(ActionListener actionListener){
        importProducts.addActionListener(actionListener);
    }

    public int[] getRows() {
        return rows;
    }

    public List<String> getItemsNames() {
        return itemsNames;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }

    public JTable getProducts() {
        return products;
    }

    public String getMenuName(){
        return menuName.getText();
    }

    public String getName(){
        return nameText.getText();
    }

    public String getCalories(){
        return caloriesText.getText();
    }

    public String getProteins(){
        return  proteinsText.getText();
    }

    public  String getPrice(){
        return priceText.getText();
    }

    public String getRating(){
        return ratingText.getText();
    }

    public String getSodium() {
        return sodiumText.getText();
    }

    public String getFat(){
        return fatText.getText();
    }

    public String getMin(){
        return min.getText();
    }

    public String getMax(){
        return max.getText();
    }

    public String getValue(){
        return valuesClient.getText();
    }

    public String getTimesClient(){
        return timesClient.getText();
    }

    public String getTimes(){
        return times.getText();
    }

    public  String getDate(){
        return date.getText();
    }
}

