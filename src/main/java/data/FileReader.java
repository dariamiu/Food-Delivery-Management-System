package data;

import model.BaseProduct;
import model.MenuItem;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * class contains method for reading the initial list of products from the .csv file
 * @author Daria Miu
 */
public class FileReader {

    /**
     * method used to read the products from the .csv file using mainly streams.
     * implemented using HashSet so that the products won't repeat
     * @param inputFilePath the path of the .csv file to read from
     * @return a HashSet of MenuItems
     */
    public HashSet<MenuItem> processInputFile(String inputFilePath) {
            HashSet<MenuItem> inputList = new HashSet<>();
            try{

                File inputF = new File(inputFilePath);
                InputStream inputFS = new FileInputStream(inputF);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

                inputList = br.lines().skip(1).map((line) -> {
                    String[] p = line.split(",");
                    MenuItem item = new BaseProduct(p[0],Double.parseDouble(p[1]),Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]),Integer.parseInt(p[4]),Integer.parseInt(p[5]),Integer.parseInt(p[6]));
                    return item;
                }).collect(Collectors.toCollection(HashSet::new));

                br.close();

            } catch (IOException e) {
                    e.printStackTrace();
            }
            return inputList ;
        }

}
