package data;

import model.MenuItem;
import model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class contains methods for serializing and deserializing objects from files, it has a parameter T, the Class from
 * which objects are going to be serialized/deserialized
 * @author Daria Miu
 */
public class Serializer<T> {
    /**
     * method to serialized objects
     * @param entries the list of objects going to be serialized
     * @param file the name of the file in which to serialize the objects
     */
    public void serialize(List<T> entries, String file){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(entries);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * method to deserialize objects
     * @param file the name of the file from which to deserialize the objects
     * @return the deserialized list of objects
     */
    public List<T> deserialize(String file){
        List<T> entries = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            entries = (List<T>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return null;
        }
        return entries;
    }

    /**
     * a special function to serialize the orders which are stored in a map (does not depend of on the parameter T)
     * @param map a map of Order and ArrayList<MenuItem> to be serialized
     * @param file the file in which to serialize the objects
     */
    public void serializeOrders(Map<Order, ArrayList<MenuItem>> map, String file){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * a special method to deserialize the orders, which where serialized as a map of Order and ArrayList<MenuItem>
     * @param file the file from which to deserialize the map of orders
     * @return the map of orders
     */
    public Map<Order,ArrayList<MenuItem>> deserializeOrders(String file){
        Map<Order,ArrayList<MenuItem>> entries = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            entries = (Map<Order,ArrayList<MenuItem>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return null;
        }
        return entries;
    }
}
