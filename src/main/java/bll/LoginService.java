package bll;

import data.Serializer;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class that implements methods for the login functionalities
 * @author Daria Miu
 */
public class LoginService {

    /**
     * method checks if there exists account with the specified credentials
     * @param user the username
     * @param password the password
     * @param type the type of user
     * @throws RuntimeException if the user does not exist
     * @return the id of the account
     */
    public int findAccount(String user, String password, String type){
        Serializer<User> userSerializer = new Serializer<>();
        List<User> users = new ArrayList<>();
        String userFile = "./users2.ser";

        users = userSerializer.deserialize(userFile);

        if(users.stream().noneMatch(user2 -> user2.getUsername().equals(user) && user2.getPassword().equals(password)
                && user2.getType().equals(type))){
            throw new RuntimeException("No "+type+" account with these credentials");
        }

        return users.stream().filter(user1 -> user1.getUsername().equals(user)).
                collect(Collectors.toList()).get(0).getUserId();

    }

    /**
     * method for registering a new account in the app
     * the account will be added to the list of users and serialized in the file for users info,also the id for users
     * in the list of ids is incremented
     * @param user the username
     * @param password the password
     * @param type the type of account
     * @param secret the secret code for creating admin or employee accounts
     * @throws RuntimeException if the username is taken
     */
    public void createAccount(String user, String password, String type, String secret){

        if(type.equals("admin") || type.equals("employee")){
            if(!secret.equals("secret")){
                throw new RuntimeException("Incorrect code");
            }
        }

        Serializer<User> userSerializer = new Serializer<>();
        List<User> users = new ArrayList<>();
        String userFile = "./users2.ser";
        String idsFile = "./currentIds.ser";
        users = userSerializer.deserialize(userFile);
        List<Integer> ids = new ArrayList<>();
        Serializer<Integer> idSerializer = new Serializer<>();
        ids = idSerializer.deserialize(idsFile);

        if(users.stream().noneMatch(user2 -> user2.getUsername().equals(user))){
            users.add(new User(ids.get(0) + 1, user, password,type));
            Integer newId = ids.get(0);
            newId++;
            ids.set(0,newId);

        }else{
            throw new RuntimeException("Username taken");
        }
        userSerializer.serialize(users, userFile);
        idSerializer.serialize(ids,idsFile);


    }
}
