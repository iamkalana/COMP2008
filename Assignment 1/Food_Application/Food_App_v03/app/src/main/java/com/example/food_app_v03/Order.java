package com.example.food_app_v03;

public class Order {
    private String item;
    private String restaurant;
    private double unitPrice;
    private int amount;
    private double totalPrice;

    public Order(String item, String restaurant, double unitPrice,int amount, double totalPrice) {
        this.item = item;
        this.restaurant = restaurant;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
