package model;

import java.io.Serializable;
import java.util.Objects;
/**
 * Class represents an object that implements a real life user with some specific
 * attributes
 * @author Daria Miu
 */
public class User implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private String type;

    public User(Integer userId, String username, String password, String type) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(type, user.type);
    }

}
