package validator;

import model.BaseProduct;
import model.MenuItem;
/**
 * class contains methods to validate the inputs of the app
 * @author Daria Miu
 *
 */
public class InputValidator {
    /**
     * method used to validate the existence of a username and a password in the login interface text fields
     * @param s1 username
     * @param s2 password
     */
    public void validateCredentials(String s1, String s2) {
        if (s1.isBlank() || s2.isBlank()) {
            throw new RuntimeException("One of the fields is empty");
        }
    }

    /**
     * method to check if the input is integer
     * @param s the string which needs to be checked if it contains an integer
     * @return the parsed string
     */
    public int validateInt(String s) {
        int number;
        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("field must be an integer");
        }
        return number;

    }
    /**
     * method to check if the input is double
     * @param s the string which needs to be checked if it contains a double value
     * @return the parsed string
     */
    public Double validateDouble(String s) {
        Double number;
        try {
            number = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("rating must be a float number");
        }
        return number;

    }

    /**
     * method to validate the fields of a new MenuItem
     * @param name field name of MenuItem
     * @param rating field rating of MenuItem
     * @param calories field calories of MenuItem
     * @param proteins field proteins of MenuItem
     * @param fat field fat of MenuItems
     * @param sodium field sodium of MenuItem
     * @param price field price of MenuItem
     * @return the new MenuItem object
     */
    public MenuItem validateMenuItem(String name, String rating, String calories, String proteins, String fat, String sodium,
                                     String price) {
        if (name.isBlank() || rating.isBlank() || calories.isBlank() || proteins.isBlank() || fat.isBlank() ||
                sodium.isBlank() || price.isBlank()) {
            throw new RuntimeException("One of the fields is empty");
        }

        MenuItem menuItem = new BaseProduct(name, validateDouble(rating), validateInt(calories), validateInt(proteins),
                validateInt(fat), validateInt(sodium), validateInt(price));

        return menuItem;
    }

    /**
     * method used for preparing the fields of a new MenuItem to search for in the menu
     * if the string is blank it means that the user does not need a specific values for that entry
     * also, throws exception if the parameter is not an integer
     * @param s string to check for properties
     * @return the parsed string or -1
     */
    public int validateSearch(String s) {
        if (s.isBlank()) {
            return -1;
        }else {
            return validateInt(s);
        }
    }
    /**
     * method used for preparing the fields of a new MenuItem to search for in the menu
     * if the string is blank it means that the user does not need a specific values for that entry
     * also, throws exception if the parameter is not a double
     * @param s string to check for properties
     * @return the string or "###"
     */
    public String validateSearchS(String s) {
        if (s.isBlank()) {
            return "###";
        }else {
            return s;
        }
    }
    /**
     * method used for preparing the fields of a new MenuItem to search for in the menu
     * if the string is blank it means that the user does not need a specific values for that entry
     * also, throws exception if the parameter is not a double
     * @param s string to check for properties
     * @return the parsed string or -1
     */
    public Double validateSearchD(String s) {
        if (s.isBlank()) {
            return (double)-1;
        }else {
            return validateDouble(s);
        }
    }
}