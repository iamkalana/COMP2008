package com.example.food_app_v03;

public class Restaurant {
    private String r_id;
    private String r_name;
    private int r_imagePath;
    private String address;

    public Restaurant(String r_id, String r_name, int imagePath, String address) {
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_imagePath = imagePath;
        this.address = address;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getR_imagePath() {
        return r_imagePath;
    }

    public void setR_imagePath(int r_imagePath) {
        this.r_imagePath = r_imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
