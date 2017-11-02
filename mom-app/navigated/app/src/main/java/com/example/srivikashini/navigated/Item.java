package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 28/10/17.
 */
public class Item {
    private String name;
    private String cuisine;
    private int price;
    private int thumbnail;

    public Item() {
    }

    public Item(String name, String cuisine, int price, int thumbnail) {
        this.name = name;
        this.cuisine = cuisine;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
