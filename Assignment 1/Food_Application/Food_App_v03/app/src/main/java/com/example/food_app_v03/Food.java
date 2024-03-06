package com.example.food_app_v03;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String food_id;
    private String food_name;
    private String food_price;
    private String food_description;
    private String re_id;
    int food_imagePath;

    public Food(String food_id, String food_name, String food_price, String food_description, String re_id, int food_imagePath) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_description = food_description;
        this.re_id = re_id;
        this.food_imagePath = food_imagePath;
    }

    protected Food(Parcel in) {
        food_id = in.readString();
        food_name = in.readString();
        food_price = in.readString();
        food_description = in.readString();
        re_id = in.readString();
        food_imagePath = in.readInt();
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getRe_id() {
        return re_id;
    }

    public void setRe_id(String re_id) {
        this.re_id = re_id;
    }

    public int getFood_imagePath() {
        return food_imagePath;
    }

    public void setFood_imagePath(int food_imagePath) {
        this.food_imagePath = food_imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(food_id);
        parcel.writeString(food_name);
        parcel.writeString(food_price);
        parcel.writeString(food_description);
        parcel.writeString(re_id);
        parcel.writeInt(food_imagePath);
    }
}
