package bll;

import data.FileReader;
import data.MyFileWriter;
import data.Serializer;
import model.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class that stores all the data for the application and implements the application functionalities, the logic
 * behind the application
 * @invariant isWellFormed
 * @author Daria Miu
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private List<MenuItem> menu = new ArrayList<>();
    private Map<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    private List<User> users = new ArrayList<>();
    private Serializer<MenuItem> serializer = new Serializer();
    private Serializer<User> serializerU = new Serializer();
    private Serializer<Integer> serializerI = new Serializer();
    private FileReader fileReader = new FileReader();
    private MyFileWriter fileWriter = new MyFileWriter();
    private String menuFile = "menuItems3.ser";
    private String userFile = "users2.ser";
    private String orderFile = "orders3.ser";
    private String idFile = "currentIds.ser";
    private List<Integer> ids = new ArrayList<>();


    @Override
    public void importProducts() {
        assert isWellFormed();
        HashSet<MenuItem> products = fileReader.processInputFile("products.csv");
        menu = new ArrayList<>(products);
        serializer.serialize(menu, menuFile);
        assert !menu.isEmpty();

    }

    @Override
    public void importData(){
        assert isWellFormed();
        menu = serializer.deserialize(menuFile);
        users = serializerU.deserialize(userFile);
        ids = serializerI.deserialize(idFile);
        orders = serializer.deserializeOrders(orderFile);
        assert !menu.isEmpty() && !users.isEmpty() && !ids.isEmpty() && !orders.isEmpty();
    }


    @Override
    public void addProduct(MenuItem menuItem) {
        assert isWellFormed();
        assert !menuItem.getName().isEmpty() && menuItem.getRating() > 0 && menuItem.getCalories() >0 &&
                menuItem.getProteins() >0
                && menuItem.getFat() > 0 && menuItem.getSodium() > 0 && menuItem.getPrice() > 0 ;
        assert  menu.stream().noneMatch(menuItem1 -> menuItem1.getName().equals(menuItem.getName()));


        menu.add(menuItem);


        assert wasAdded(menuItem);
        serializer.serialize(menu, menuFile);
    }
    @Override
    public void addCompositeProduct(String name, List<MenuItem> components){
        assert isWellFormed();
        assert !components.isEmpty();
        assert !name.isEmpty();
        assert menu.stream().noneMatch(m -> m.getName().equals(name));

        CompositeProduct newCompositeProduct = new CompositeProduct(name, (double)0, 0,
                0, 0, 0, 0);
        newCompositeProduct.setItems(components);
        newCompositeProduct.computeCalories();
        newCompositeProduct.computeFats();
        newCompositeProduct.computePrice();
        newCompositeProduct.computeProteins();
        newCompositeProduct.computeRating();
        newCompositeProduct.computeSodium();
        menu.add(newCompositeProduct);

        assert newCompositeProduct.getCalories() != 0 && newCompositeProduct.getFat() != 0 &&
                newCompositeProduct.getPrice() != 0 && newCompositeProduct.getRating() != 0 &&
                newCompositeProduct.getProteins() != 0 && newCompositeProduct.getSodium() != 0 ;
        assert !newCompositeProduct.getItems().isEmpty();
        assert wasAdded(newCompositeProduct);
        serializer.serialize(menu, menuFile);

    }

    @Override
    public void deleteProduct(MenuItem menuItem) {
        assert isWellFormed();
        assert menuItem !=null;
        assert menu.stream().anyMatch(m->m.equals(menuItem));

        menu.remove(menuItem);

        assert wasDeleted(menuItem);
        serializer.serialize(menu, menuFile);

    }

    @Override
    public void updateProduct(String productToModify, MenuItem menuItem) {
        assert isWellFormed();
        assert !productToModify.isBlank();
        assert menuItem != null;
        assert menu.stream().anyMatch(m -> m.getName().equals(productToModify));
        menu.stream().forEach(menuItem1 -> {
            if (menuItem1.getName().equals(productToModify)){
            menuItem1.setName(menuItem.getName());
            menuItem1.setPrice(menuItem.getPrice());
            menuItem1.setRating(menuItem.getRating());
            menuItem1.setCalories(menuItem.getCalories());
            menuItem1.setFat(menuItem.getFat());
            menuItem1.setSodium(menuItem.getSodium());}
        });
        menu.stream().forEach(menuItem1 -> {
            if(menuItem1 instanceof CompositeProduct){
                if(((CompositeProduct) menuItem1).getItems().stream().anyMatch(menuItem2 -> menuItem2.equals(menuItem))){
                    menuItem1.computeCalories();
                    menuItem1.computeFats();
                    menuItem1.computePrice();
                    menuItem1.computeProteins();
                    menuItem1.computeRating();
                    menuItem1.computeSodium();
                }
            }
        });
        assert (menu.stream().anyMatch(m -> m.equals(menuItem)));
        serializer.serialize(menu,menuFile);
    }


    @Override
    public List<MenuItem> search(String name, Double rating, Integer calories, Integer proteins,
                                 Integer fat, Integer sodium, Integer price){

        assert isWellFormed();
        assert name != null && rating != null && calories != null && proteins != null && fat != null && sodium != null
                && price != null;

        List<MenuItem> result = new ArrayList<>();
        result  = menu.stream().filter(!name.equals("###") ? m-> m.getName().contains(name) : m -> true ).
                filter(!price.equals(-1)? m -> m.getPrice().equals(price) : m -> true).
                filter(!calories.equals(-1)? m -> m.getCalories().equals(calories) : m -> true).
                filter(!proteins.equals(-1)? m -> m.getProteins().equals(proteins) : m -> true).
                filter(!fat.equals(-1)? m -> m.getFat().equals(fat) : m -> true).
                filter(!sodium.equals(-1)? m -> m.getSodium().equals(sodium) : m -> true).
                filter(!rating.equals(-1.0)? m -> m.getRating().equals(rating) : m -> true).collect(Collectors.toList());

        return result;
    }

    @Override
    public void addOrder(Order order, ArrayList<MenuItem> items) {
        assert isWellFormed();
        assert order.getOrderDate() != null && order.getClientId() >0 && order.getOrderId() >0;
        assert !items.isEmpty();
        orders.put(order,items);
        String[] orderDetails = orderToString(order,items);
        fileWriter.generateBill(orderDetails);
        setChanged();
        notifyObservers(orderDetails);
        assert wasAddedHash(order);
        serializer.serializeOrders(orders,orderFile);
        serializerI.serialize(ids,idFile);
    }

    @Override
    public void timeIntervalReport(int minH, int maxH) {
        assert minH >= 0 && maxH <= 24;
        Set<Order> result = orders.keySet().stream().filter(order -> order.getOrderDate().getHour() >= minH &&
                order.getOrderDate().getHour() < maxH).collect(Collectors.toSet());
        List<String[]> strings = result.stream().map(order -> orderToString(order,orders.get(order))).
                collect(Collectors.toList());
        fileWriter.timeIntervalReport(strings);
    }

    @Override
    public void favoriteProductsReport(int times) {
        assert times>0;
        Stream<MenuItem> stream = Stream.of();
        for (Order key : orders.keySet()) {
            stream = Stream.concat(stream,orders.get(key).stream());
        }
        List<MenuItem> result = stream.collect(Collectors.toList());
        fileWriter.favouriteProductsReport(result.stream().distinct().filter(menuItem ->
                Collections.frequency(result,menuItem) >= times).collect(Collectors.toList()));
    }

    @Override
    public void clientsReport(int value, int idCount) {
        assert idCount > 0 && value >0;
        List<Integer> ids = new ArrayList<>();
       orders.keySet().stream().filter(entry -> computePriceOfOrder(orders.get(entry)) >= value).
               collect(Collectors.toSet()).forEach(order -> ids.add(order.getClientId()));
       List<Integer> result = ids.stream().filter(i -> Collections.frequency(ids,i) >= idCount).collect(Collectors.toList());
       List<User> resultToPrint = users.stream().filter(user -> result.stream().anyMatch(idd ->idd.equals(user.getUserId()))).
               collect(Collectors.toList());
       fileWriter.clientsReport(resultToPrint);
    }



    @Override
    public void productsInDay(LocalDate date) {
        assert date.isBefore(LocalDate.now().plusDays(1));
        Set<Order> result = orders.keySet().stream().filter(order -> equalsDate(order.getOrderDate(),date)).
                collect(Collectors.toSet());
        Stream<MenuItem> stream = Stream.of();
        for (Order key : result) {
            stream = Stream.concat(stream,orders.get(key).stream());
        }
        List<MenuItem> items = stream.collect(Collectors.toList());
        Map<MenuItem,Long> itemsCount = items.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        fileWriter.productsInDayReport(itemsCount);
    }




    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Serializer<MenuItem> getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer<MenuItem> serializer) {
        this.serializer = serializer;
    }

    public Serializer<User> getSerializerU() {
        return serializerU;
    }

    public void setSerializerU(Serializer<User> serializerU) {
        this.serializerU = serializerU;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    /**
     * @param a variable of type LocalDateTime to be compared wuth
     * @param b variable of type LocalDate based on their dates
     * @return if they are equal
     */
    private boolean equalsDate(LocalDateTime a, LocalDate b){
        if(a.getDayOfMonth() == b.getDayOfMonth() &&
                a.getYear() == b.getYear() &&
                a.getMonth() == b.getMonth()) return true;
        return false;
    }

    /**
     * method to parse the information about an order in an array of strings ( used for displaying)
     * @param order the order
     * @param items the products from the order
     * @return the parsed array of string
     */
    private String[] orderToString(Order order, ArrayList<MenuItem> items){
        String[] orderDetails = new String[5];
        orderDetails[0] = String.valueOf(order.getOrderId());
        orderDetails[1] =String.valueOf(order.getClientId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        String orderDate = order.getOrderDate().format(formatter);
        orderDetails[2] = orderDate;

        StringBuilder sb = new StringBuilder();
        items.forEach(menuItem -> sb.append(menuItem.getName()).append(","));
        orderDetails[3] = StringUtils.chop(sb.toString());

        orderDetails[4] = String.valueOf(computePriceOfOrder(items));
        return orderDetails;
    }

    /**
     * method to compute the price of an order by summing the prices of all the products from that orede
     * @param items the list of products
     * @return the price
     */
    private int computePriceOfOrder(ArrayList<MenuItem> items){
        int sum = items.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getPrice(),
                Integer::sum);
        return sum;
    }

    /**
     * method to check if an order was added to the map of orders
     * @param order the order to look for
     * @return if it exists or not
     */
    private boolean wasAddedHash(Order order){
        return orders.containsKey(order);
    }

    /**
     * method to check if an order was added to the array of products
     * @param menuItem the menu item to look for
     * @return if it exists or not
     */
    private boolean wasAdded(MenuItem menuItem){
        return menu.stream().anyMatch( m -> m.equals(menuItem));
    }

    /**
     * method to check if an order was deleted from the array of products
     * @param menuItem the menu item to look for
     * @return if it exists or not
     */
    private boolean wasDeleted(MenuItem menuItem){
        return menu.stream().noneMatch(m -> m.equals(menuItem));
    }

    /**
     * method to check if a file exists
     * @param path the path to the file
     * @return if it exists or not
     */
    private boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return true;
        }
        return false;
    }

    /**
     * method to determine if the class is well formed
     * @invariant
     * @return is th class is valid or not
     */
    protected boolean isWellFormed() {
        if(menu == null) return false;
        if(users == null) return false;
        if(orders == null) return false;
        if(ids == null) return false;
        if(fileReader == null) return false;
        if(isValidPath(userFile)) return false;
        if(isValidPath(orderFile)) return false;
        if(isValidPath(menuFile)) return false;
        if(isValidPath(idFile)) return false;
        return true;
    }



}
