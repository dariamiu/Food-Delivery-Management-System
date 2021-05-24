package view;

import bll.DeliveryService;
import model.MenuItem;
import model.Order;
import validator.InputValidator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class that controls the ClientView
 * @author Daria Miu
 */
public class ClientController {

    private DeliveryService deliveryService = new DeliveryService();
    private ClientView clientView;
    private InputValidator inputValidator = new InputValidator();
    private ArrayList<MenuItem> itemsOrdered = new ArrayList<>();
    private int idClient;
    ClientController(DeliveryService deliveryService, int idClient){
        this.idClient =idClient;
        this.deliveryService = deliveryService;
        clientView = new ClientView();
        deliveryService.importData();
        clientView.setMenuItems(deliveryService.getMenu());
        clientView.initializeTable(clientView.getMenuItems());
        actions();
    }

    /**
     * method to initialize all the action listeners for the buttons from the ClientView.
     * method retrieves the data from the text fields in the interface and
     * sends the data to the BLL to be processed after validating them with the methods from InputValidator class
     */
    private void actions(){
        clientView.addToOrderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    clientView.setRows(clientView.getProducts().getSelectedRows());
                    int[] rows = clientView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    for(int i : rows){
                        String[] row = clientView.getRowAt(i,clientView.getProducts());
                        MenuItem menuItem = deliveryService.getMenu().stream().filter(m -> m.getName().equals(row[0])).
                                collect(Collectors.toList()).get(0);
                        itemsOrdered.add(menuItem);
                    }
                    clientView.displayInformationMessage("item added");
                }catch (RuntimeException exception){
                    clientView.displayErrorMessage(exception);
                }

            }
        });
        clientView.finalizeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (itemsOrdered.isEmpty()) throw new RuntimeException("no items selected");
                    int id = deliveryService.getIds().get(1) + 1;
                    Order order = new Order(id,idClient, LocalDateTime.now());
                    deliveryService.getIds().set(1,id);
                    deliveryService.addOrder(order,itemsOrdered);
                    itemsOrdered.clear();
                    clientView.displayInformationMessage("order created, bill generated");
                }catch (RuntimeException exception){
                    clientView.displayErrorMessage(exception);
                }


            }
        });
        clientView.viewAllActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientView.initializeTable(clientView.getMenuItems());
                } catch (RuntimeException exception) {
                    clientView.displayErrorMessage(exception);
                }
            }
        });
        clientView.viewSortedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    List<MenuItem> menuItems = deliveryService.search(inputValidator.validateSearchS(clientView.getName()),
                            inputValidator.validateSearchD(clientView.getRating()),
                            inputValidator.validateSearch(clientView.getCalories()),
                            inputValidator.validateSearch(clientView.getProteins()),
                            inputValidator.validateSearch(clientView.getFat()),
                            inputValidator.validateSearch(clientView.getSodium()),
                            inputValidator.validateSearch(clientView.getPrice())
                            );
                    menuItems.forEach(m -> System.out.println(m.getName()));
                    clientView.initializeTable(menuItems);
                } catch (RuntimeException exception) {
                    clientView.displayErrorMessage(exception);
                }
            }
        });
    }
}
