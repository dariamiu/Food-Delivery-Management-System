package model;

import java.io.Serializable;
import java.util.Objects;
/**
 * Class represents an object that implements a real life product from a menu with some specific
 * attributes
 * @author Daria Miu
 */
public abstract class MenuItem implements Serializable {
    private String name;
    private Double rating;
    private Integer calories;
    private Integer proteins;
    private Integer fat;
    private Integer sodium;
    private Integer price;

    public MenuItem(String name, Double rating, Integer calories, Integer proteins, Integer fat, Integer sodium, Integer price) {
        this.name = name;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }
    public MenuItem(){

    }

    public String getName() {
        return name;
    }

    public void setPrice(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * abstract method for computing the price, created especially for the CompositeProduct class,
     * returns only the getPrice() in BaseProduct
     * @return the price
     */
    public abstract Integer computePrice();
    /**
     * abstract method for computing the rating, created especially for the CompositeProduct class,
     * returns only the getRating() in BaseProduct
     * @return the rating
     */
    public abstract Double computeRating();
    /**
     * abstract method for computing the fat, created especially for the CompositeProduct class,
     * returns only the getFat() in BaseProduct
     * @return the fats value
     */
    public abstract Integer computeFats();
    /**
     * abstract method for computing the proteins, created especially for the CompositeProduct class,
     * returns only the getProteins() in BaseProduct
     * @return number of proteins
     */
    public abstract Integer computeProteins();
    /**
     * abstract method for computing the calories, created especially for the CompositeProduct class,
     * returns only the getCalories() in BaseProduct
     * @return the number of calories
     */
    public abstract Integer computeCalories();
    /**
     * abstract method for computing the sodium value, created especially for the CompositeProduct class,
     * returns only the getSodium() in BaseProduct
     * @return the sodium value
     */
    public abstract Integer computeSodium();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}

