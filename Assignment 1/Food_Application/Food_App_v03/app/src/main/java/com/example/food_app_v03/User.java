package com.example.food_app_v03;

public class User {
    private String u_name;
    private String u_email;
    private String u_address;
    private String u_phone;
    private String u_password;

    public User(String u_name, String u_email, String u_address, String u_phone, String u_password) {
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_address = u_address;
        this.u_phone = u_phone;
        this.u_password = u_password;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }
}
