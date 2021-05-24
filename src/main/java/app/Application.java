package app;

import data.FileReader;
import data.Serializer;
import model.MenuItem;
import model.Order;
import model.User;
import view.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main class of the app, starts the app
 * @author Daria Miu
 */
public class Application {
    public static void main(String[] args){

       // new LoginController();
       // new EmployeeController();
        //new ClientView();

         /*  List<String> columns = new ArrayList<>();
        columns = fileReader.readFromFile();
        for (String s: columns) {
            System.out.println(s);
        }*/ /* FileReader fileReader = new FileReader();

        List<MenuItem> menuItems = new ArrayList<>();

        menuItems = fileReader.processInputFile("products.csv");

        serializer.serialize(menuItems, file);
        //new AdministratorView();
        Serializer<MenuItem> serializer = new Serializer();
        List<MenuItem> menuItems2 = new ArrayList<>();
        String file = "./menuItems2.ser";
        menuItems2 = serializer.deserialize(file);
        for(MenuItem menuItem : menuItems2){
            System.out.println(menuItem.getName() + menuItem.getRating() + " " + menuItem.getCalories() + " " + menuItem.getProteins() + " " + menuItem.getFat() + " " + menuItem.getSodium() + " " + menuItem.getPrice());
        }

        Serializer<User> userSerializer = new Serializer<>();
        List<User> users = new ArrayList<>();
        users.add(new User("admin", "admin","admin"));
        users.add(new User("emp","emp","employee"));
        users.add(new User("client","client","client"));
        String userFile = "./users2.ser";
        userSerializer.serialize(users,userFile);
        List<User> users1 = new ArrayList<>();
        users1 = userSerializer.deserialize(userFile);
        for(User user : users1){
            System.out.println(user.getUsername() + user.getPassword() + user.getType());
        }*/


        //new AdministratorView();

       /* Serializer<User> userSerializer = new Serializer<>();
        Serializer<Integer> idSerializer = new Serializer<>();
        List<User> users = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        Integer id = 0;

        users.add(new User(id++,"admin", "admin","admin"));
        users.add(new User(id++,"emp","emp","employee"));
        users.add(new User(id++,"client","client","client"));
        ids.add(id);
        ids.add(0);
        String userFile = "./users2.ser";
        String file = "./currentIds.ser";
        //idSerializer.serialize(ids,file);
        //userSerializer.serialize(users,userFile);
        List<User> users1 = new ArrayList<>();
        List<Integer> idss = new ArrayList<>();
        idss = idSerializer.deserialize(file);
        users1 = userSerializer.deserialize(userFile);
        for(User user : users1){
            System.out.println(user.getUsername() + user.getPassword() + user.getType() + user.getUserId());
        }
        System.out.println(idss.get(0) + " " + idss.get(1));*/


       new LoginController();
       /*Serializer<MenuItem> serializer = new Serializer();
        String file = "./orders.ser";
        Map<Order, ArrayList<MenuItem>> orders = new HashMap<>();
        orders = serializer.deserializeOrders(file);
       // menuItems2 = serializer.deserialize(file);

        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            System.out.println(entry.getKey().getOrderId()+" : "+entry.getValue().get(0).getName());
        }*/
        //new ClientView();

    }
}
