package bll;

import model.MenuItem;
import model.Order;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * interface of functionalities for the administrator and for the client
 * @author Daria Miu
 */
public interface IDeliveryServiceProcessing {

    /**
     * method to import the items from the .csv file
     * @post !menu.isEmpty()
     */
    void importProducts();

    /**
     * method to import the data from file at each run of the application
     * @post !menu.isEmpty() && !users.isEmpty() && !ids.isEmpty() && !orders.isEmpty()
     */
    void importData();

    /**
     * method that adds new product to list of products
     * @pre  !menuItem.getName().isEmpty() && menuItem.getRating() > 0 && menuItem.getCalories() >0 &&
     *                 menuItem.getProteins() >0
     *                 && menuItem.getFat() > 0 && menuItem.getSodium() > 0 && menuItem.getPrice() > 0
     * @pre menu.stream().noneMatch(menuItem1 -> menuItem1.getName().equals(menuItem.getName()))
     * @post wasAdded(menuItem);
     * @param menuItem item to be inserted in the list of products
     */
    void addProduct(MenuItem menuItem);

    /**
     * method that adds new menu to the list of MenuItems
     * @pre !components.isEmpty()
     * @pre !name.isEmpty()
     * @pre menu.stream().noneMatch(m -> m.getName().equals(name))
     * @post newCompositeProduct.getCalories() != 0 && newCompositeProduct.getFat() != 0 &&
     *                 newCompositeProduct.getPrice() != 0 && newCompositeProduct.getRating() != 0 &&
     *                 newCompositeProduct.getProteins() != 0 && newCompositeProduct.getSodium() != 0
     * @post !newCompositeProduct.getItems().isEmpty()
     * @post wasAdded(newCompositeProduct)
     * @param name the name of the product to insert
     * @param components the components of the product to insert
     */
    void addCompositeProduct(String name, List<MenuItem> components);

    /**
     * method that deletes the specified menuItem
     * @pre menuItem !=null;
     * @pre menu.stream().anyMatch(m->m.equals(menuItem))
     * @post wasDeleted(menuItem)
     * @param menuItem item to be deleted
     */
    void deleteProduct(MenuItem menuItem);

    /**
     * method that updates the menuItem with the given name
     * @pre !productToModify.isBlank();
     * @pre menuItem != null;
     * @pre menu.stream().anyMatch(m -> m.getName().equals(productToModify))
     * @post (menu.stream().anyMatch(m -> m.equals(menuItem)))
     * @param productToModify the name of the product to modify
     * @param menuItem a menuItem containing all the updated fields for the product to be modified
     */
    void updateProduct(String productToModify, MenuItem menuItem);

    /**
     * method that searches for the menuItems that correspond to the specified criteria
     * (if the name is "###" it means that the name is not needed if other fields are -1 it means that they are not needed)
     * @pre name != null && rating != null && calories != null && proteins != null && fat != null && sodium != null
     *                 && price != null;
     * @param name of product
     * @param rating of product
     * @param calories of product
     * @param proteins of product
     * @param fat of product
     * @param sodium of product
     * @param price of product
     * @return list of menuItems
     */
    List<MenuItem> search(String name, Double rating, Integer calories, Integer proteins, Integer fat,
                          Integer sodium, Integer price);

    /**
     * method to add an order to the hashmap of orders
     * @pre order.getOrderDate() != null && order.getClientId() >0 && order.getOrderId() >0
     * @pre !items.isEmpty()
     * @post wasAddedHash(order)
     * @param order the order details of the new order that has to be added to the hashmap of orders
     * @param items the items from th order
     */
    void addOrder(Order order, ArrayList<MenuItem> items);

    /**
     * method that generates the report about products orderd in a time interval
     * @pre minH >= 0 && maxH <= 24
     * @param minH minim hour
     * @param maxH maxim hour
     */
    void timeIntervalReport(int minH, int maxH);

    /**
     * method that generates the report about the products that have been ordered more than a number of time
     * @pre  times>0
     * @param times the nr of time th product should have been ordered at least
     */
    void favoriteProductsReport(int times);

    /**
     * method generates report about the clients that have ordered more than idCount time an order with price > value
     * @pre idCount > 0 && value >0
     * @param value price of order
     * @param idCount number of times the client has ordered
     */
    void clientsReport(int value, int idCount);

    /**
     * method that generates report about products ordered in a day and the number of times they were ordered
     * @pre date.isBefore(LocalDate.now().plusDays(1))
     * @param date the date in which the products were ordered
     */
    void productsInDay(LocalDate date);





}
