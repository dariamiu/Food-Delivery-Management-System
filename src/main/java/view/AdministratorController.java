package view;

import bll.DeliveryService;
import model.BaseProduct;

import model.MenuItem;
import validator.InputValidator;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
/**
 * class that controls the AdministratorView
 * @author Daria Miu
 */
public class AdministratorController {

    private DeliveryService deliveryService = new DeliveryService();
    private AdministratorView administratorView;
    private InputValidator inputValidator = new InputValidator();

    AdministratorController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
        administratorView = new AdministratorView();
        administratorView.setMenuItems(deliveryService.getMenu());
        administratorView.initializeTable();
        deliveryService.importData();
        actions();
    }
    /**
     * method to initialize all the action listeners for the buttons from the AdministratorView.
     * method retrieves the data from the text fields in the interface and
     * sends the data to the BLL to be processed after validating them with the methods from InputValidator class
     */
    private void actions(){
        administratorView.addMenuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> itemList = new ArrayList<>();
                try{
                    administratorView.setRows(administratorView.getProducts().getSelectedRows());
                    int[] rows = administratorView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    for(int i : rows){
                        String[] row = administratorView.getRowAt(i,administratorView.getProducts());
                        MenuItem menuItem = deliveryService.getMenu().stream().filter(m -> m.getName().equals(row[0])).
                                collect(Collectors.toList()).get(0);
                        itemList.add(menuItem);
                    }
                    deliveryService.addCompositeProduct(administratorView.getMenuName(),itemList);
                    administratorView.setMenuItems(deliveryService.getMenu());
                    administratorView.initializeTable();
                    administratorView.displayInformationMessage("product was added");
                }catch (RuntimeException exception){
                    administratorView.displayErrorMessage(exception);
                }

            }
        });

        administratorView.addProductActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MenuItem newMenuItem = inputValidator.validateMenuItem(administratorView.getName(),
                            administratorView.getRating(),
                            administratorView.getCalories(), administratorView.getProteins(), administratorView.getFat(),
                            administratorView.getSodium(), administratorView.getPrice());
                    deliveryService.addProduct(newMenuItem);
                    administratorView.setMenuItems(deliveryService.getMenu());
                    administratorView.initializeTable();
                    administratorView.displayInformationMessage("product was added");
                } catch (RuntimeException exception) {
                    administratorView.displayErrorMessage(exception);
                }
            }
        });
        administratorView.updateMenuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    administratorView.setRows(administratorView.getProducts().getSelectedRows());
                    int[] rows = administratorView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    String[] row = administratorView.getRowAt(administratorView.getRows()[0],administratorView.getProducts());
                    if(row[7].equals(" ")){
                        MenuItem menuItem = new BaseProduct(row[0], Double.valueOf(row[1]), Integer.parseInt(row[2]),
                                Integer.parseInt(row[3]), Integer.parseInt(row[4]), Integer.parseInt(row[5]),
                                Integer.parseInt(row[6]));
                        deliveryService.updateProduct(administratorView.getItemsNames().get(administratorView.getRows()[0]),
                                menuItem);
                        administratorView.setMenuItems(deliveryService.getMenu());
                        administratorView.initializeTable();
                    }
                    administratorView.displayInformationMessage("item updated");
                }catch (RuntimeException exception){
                    administratorView.displayErrorMessage(exception);
                }


            }
        });
        administratorView.deleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> itemList = new ArrayList<>();
               try{
                   administratorView.setRows(administratorView.getProducts().getSelectedRows());
                   int[] rows = administratorView.getRows();
                   if (rows.length == 0) throw new RuntimeException("no rows selected!");
                   for(int i : rows){
                       String[] row = administratorView.getRowAt(i,administratorView.getProducts());
                       MenuItem menuItem = deliveryService.getMenu().stream().filter(m -> m.getName().equals(row[0])).
                               collect(Collectors.toList()).get(0);
                       itemList.add(menuItem);
                   }
                   itemList.forEach(m -> deliveryService.deleteProduct(m));
                   administratorView.setMenuItems(deliveryService.getMenu());
                   administratorView.initializeTable();
                   administratorView.displayInformationMessage("item deleted");
               }catch (RuntimeException exception){
                   administratorView.displayErrorMessage(exception);
               }

            }
        });

        administratorView.ordersInTimeReportActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deliveryService.timeIntervalReport(inputValidator.validateInt(administratorView.getMin()),
                            inputValidator.validateInt(administratorView.getMax()));
                    System.out.println(administratorView.getMin() +" "+ administratorView.getMax());
                    administratorView.displayInformationMessage("report generated");
                }catch (RuntimeException ex){
                    administratorView.displayErrorMessage(ex);
                }

            }
        });
        administratorView.clientsReportActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deliveryService.clientsReport(inputValidator.validateInt(administratorView.getValue()),
                            inputValidator.validateInt(administratorView.getTimesClient()));
                    System.out.println(inputValidator.validateInt(administratorView.getValue()) +" "+
                            inputValidator.validateInt(administratorView.getTimesClient()));
                    administratorView.displayInformationMessage("report generated");
                }catch (RuntimeException exception){
                    administratorView.displayErrorMessage(exception);
                }

            }
        });
        administratorView.favouriteProductsReportActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deliveryService.favoriteProductsReport(inputValidator.validateInt(administratorView.getTimes()));
                    System.out.println(inputValidator.validateInt(administratorView.getTimes()));
                    administratorView.displayInformationMessage("report generated");
                }catch (RuntimeException exception){
                    administratorView.displayErrorMessage(exception);
                }

            }
        });
        administratorView.productsInDayReportActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate date = LocalDate.parse(administratorView.getDate(), formatter);
                    deliveryService.productsInDay(date);
                    administratorView.displayInformationMessage("report generated");
                }catch (RuntimeException exception){
                    administratorView.displayInformationMessage("wrong date format");
                }

            }
        });

        administratorView.importProductsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.importProducts();
            }
        });

    }
}
