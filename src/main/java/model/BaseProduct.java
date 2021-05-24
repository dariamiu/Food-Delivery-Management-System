package model;

/**
 * Class represents an object that implements a real life product from a menu
 * with some specific attributes
 * @author Daria Miu
 */
public class BaseProduct extends MenuItem {
    public BaseProduct(String name, Double rating, int calories, int proteins, int fat, int sodium, int price) {
        super(name, rating, calories, proteins, fat, sodium, price);
    }
    public BaseProduct(){

    }

    @Override
    public Integer computePrice() {
        return this.getPrice();
    }

    @Override
    public Double computeRating() {
        return this.getRating();
    }

    @Override
    public Integer computeFats() {
        return this.getFat();
    }

    @Override
    public Integer computeProteins() {
        return this.getProteins();
    }

    @Override
    public Integer computeCalories() {
        return this.getCalories();
    }

    @Override
    public Integer computeSodium() {
        return this.getSodium();
    }
}
