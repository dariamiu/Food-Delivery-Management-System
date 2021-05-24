package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents an object that implements a real life menu type product which consists of multiple dishes
 * with some specific attributes
 * @author Daria Miu
 */
public class CompositeProduct extends MenuItem {
    private List<MenuItem> items = new ArrayList<>();
    public CompositeProduct(String name, Double rating, int calories, int proteins, int fat, int sodium, int price) {
        super(name, rating, calories, proteins, fat, sodium, price);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void addItem(MenuItem item){
        items.add(item);
    }

    public void removeItem(MenuItem item){
        items.remove(item);
    }


    @Override
    public Integer computePrice(){
        Integer price = 0;
        for(MenuItem menuItem : this.items){
            price+= menuItem.getPrice();
        }
        this.setPrice(price);
        return price;
    }

    @Override
    public Double computeRating() {
        Double rating = (double) 0;
        for(MenuItem menuItem : this.items){
            rating+= menuItem.getRating();
        }
        this.setRating(rating/items.size());
        return rating/items.size();
    }

    @Override
    public Integer computeFats() {
        Integer fats = 0;
        for(MenuItem menuItem : this.items){
            fats+= menuItem.getFat();
        }
        this.setFat(fats);
        return fats;
    }

    @Override
    public Integer computeProteins() {
        Integer proteins = 0;
        for(MenuItem menuItem : this.items){
            proteins+= menuItem.getProteins();
        }
        this.setProteins(proteins);
        return proteins;
    }

    @Override
    public Integer computeCalories() {
        Integer calories = 0;
        for(MenuItem menuItem : this.items){
            calories+= menuItem.getCalories();
        }
        this.setCalories(calories);
        return calories;
    }

    public Integer computeSodium() {
        Integer sodium = 0;
        for(MenuItem menuItem : this.items){
            sodium+= menuItem.getSodium();
        }
        this.setSodium(sodium);
        return sodium;
    }
}
