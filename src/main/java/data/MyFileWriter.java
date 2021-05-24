package data;

import model.MenuItem;
import model.Order;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * class which contains methods for writing in files and creating the reports
 * @author Daria Miu
 */
public class MyFileWriter {

    /**
     * method that writes into a file the details of an order
     * @param strings array of strings which represents the order parsed in a form in which it an be displayed
     */
    public void generateBill(String[] strings){
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER : ").append(strings[0]).append("\n");
        sb.append("CLIENT : ").append(strings[1]).append("\n");
        sb.append("DATE&TIME : ").append(strings[2]).append("\n");
        String products = strings[3].replaceAll(",","\n");
        sb.append(products).append("\n");
        sb.append("PRICE : ").append(strings[4]);
        System.out.println(sb.toString());
        try {
            FileWriter fw = new FileWriter(createFile(strings[0], strings[1]));
            fw.write(sb.toString());
            fw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * method creates new file with new name of the form "billorderId_clientId.txt"
     * @param orderId the id of an order
     * @param clientId the id of a client
     * @return the created file name
     */
    public String createFile(String orderId, String clientId){
        StringBuilder fileName = new StringBuilder();
        fileName.append("./bill");
        fileName.append(orderId + "_");
        fileName.append(clientId);
        fileName.append(".txt");
        File file = new File(fileName.toString());
        boolean result;
        try
        {
            result = file.createNewFile();
            if(result)
            {
                System.out.println("file created "+file.getCanonicalPath());
            }
            else
            {
                System.out.println("File already exist at location: "+file.getCanonicalPath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileName.toString();
    }

    /**
     * method generates the report for orders placed in a specific time interval(writes them in a file)
     * @param result a list of orders parsed as arrays af strings
     */
    public void timeIntervalReport(List<String[]> result){
        StringBuilder  sb = new StringBuilder();
        sb.append(String.format("%20s %20s %20s %100s %20s \r\n","id order","id client","date","products","price"));
        result.forEach(strings -> {
            sb.append(String.format("%20s %20s %20s %100s %20s \r\n",
                    strings[0],strings[1],strings[2],strings[3],strings[4]));
            sb.append("\n");
        });
        System.out.println(sb.toString());
        try {
            FileWriter fw = new FileWriter("time_interval_report.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * method generates the report for the clients which have ordered from the restaurants more than x times an order of
     * value grater than y
     * @param result a list of users that correspond to the condition for creating this report
     */
    public void clientsReport(List<User> result){
        StringBuilder  sb = new StringBuilder();
        sb.append(String.format("%20s %20s \r\n","id","username"));
        result.forEach(user -> {
            sb.append(String.format("%20s %20s \r\n",
                    user.getUserId(),user.getUsername()));
            sb.append("\n");
        });
        System.out.println(sb.toString());
        try {
            FileWriter fw = new FileWriter("clients_report.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * method to generate the report of products ordered more than a specific number of time
     * @param result a list of MenuItems that correspond to the criteria of generating this report
     */
    public void favouriteProductsReport(List<MenuItem> result){
        StringBuilder  sb = new StringBuilder();
        sb.append(String.format("%100s %20s %20s \r\n","name","calories","price"));
        result.forEach(menuItem -> {
            sb.append(String.format("%100s %20s %20s\r\n",
                    menuItem.getName(),menuItem.getCalories(),menuItem.getPrice()));
            sb.append("\n");
        });
        System.out.println(sb.toString());
        try {
            FileWriter fw = new FileWriter("products_report1.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * which will be printed in the report about the products from a specific day and the number of times
     * they were ordered
     * @param result a map of MenuItem and Long that contains values that correspond to the criteria of this report
     */
    public void productsInDayReport(Map<MenuItem,Long> result){
        StringBuilder  sb = new StringBuilder();
        sb.append(String.format("%100s %20s\r\n","name","times"));
        result.entrySet().stream().forEach(e -> {
            sb.append(String.format("%100s %20s \r\n", e.getKey().getName(), e.getValue()));
            sb.append("\n"); });
        System.out.println(sb.toString());
        try {
            FileWriter fw = new FileWriter("products_report2.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
