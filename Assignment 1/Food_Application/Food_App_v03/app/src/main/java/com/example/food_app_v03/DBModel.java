package com.example.food_app_v03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.food_app_v03.DBSchema.UserTable;
import com.example.food_app_v03.DBSchema.RestaurantTable;
import com.example.food_app_v03.DBSchema.FoodTable;
import com.example.food_app_v03.DBSchema.OrderHistoryTable;

public class DBModel {
    SQLiteDatabase db;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.NAME, user.getU_name());
        cv.put(UserTable.Cols.EMAIL, user.getU_email());
        cv.put(UserTable.Cols.ADDRESS, user.getU_address());
        cv.put(UserTable.Cols.PHONE, user.getU_phone());
        cv.put(UserTable.Cols.PASSWORD, user.getU_password());
        db.insert(UserTable.TABLENAME, null, cv);
    }

    public User getLoggedUser(String email, String password) {
        User loggedUser;
        String[] selectionArgs = {email, password};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", selectionArgs);
        DBCursor dbCursor = new DBCursor(cursor);

        if (cursor != null && cursor.getCount() > 0) {
            try {
                dbCursor.moveToFirst();
                loggedUser = dbCursor.getUser();
            } finally {
                cursor.close();
            }
        } else loggedUser = null;

        return loggedUser;
    }

    public void addRestaurant(Restaurant restaurant) {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantTable.Cols.RES_ID, restaurant.getR_id());
        cv.put(RestaurantTable.Cols.RES_NAME, restaurant.getR_name());
        cv.put(RestaurantTable.Cols.RES_IMAGE_PATH, restaurant.getR_imagePath());
        cv.put(RestaurantTable.Cols.RES_ADDRESS, restaurant.getAddress());
        db.insert(RestaurantTable.TABLENAME, null, cv);
    }

    public void addRestaurantList() {
        Data data = new Data();
        ArrayList<Restaurant> restaurantList = data.makeRestaurantList();
        for (int i = 0; i < restaurantList.size(); i++) {
            addRestaurant(restaurantList.get(i));
        }

    }

    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        Cursor cursor = db.query(RestaurantTable.TABLENAME, null, null, null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                restaurantList.add(dbCursor.getRestaurant());
                dbCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return restaurantList;
    }

    public Restaurant getRestaurantByID(String resID) {
        Restaurant restaurant;
        String[] selectionArgs = {resID};
        Cursor cursor = db.query(RestaurantTable.TABLENAME, null, RestaurantTable.Cols.RES_ID + " = '" + resID + "'", null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        if (cursor != null && cursor.getCount() > 0) {
            try {
                dbCursor.moveToFirst();
                restaurant = dbCursor.getRestaurant();
            } finally {
                cursor.close();
            }
        } else restaurant = null;

        return restaurant;
    }

    public ArrayList<Food> getRestaurantFoods(String restaurant) {
        ArrayList<Food> foodList = new ArrayList<>();
        Cursor cursor = db.query(FoodTable.TABLENAME, null, FoodTable.Cols.RES_ID + "='" + restaurant + "'", null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                foodList.add(dbCursor.getFoodItem());
                dbCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return foodList;
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foodList = new ArrayList<>();
        Cursor cursor = db.query(FoodTable.TABLENAME, null, null, null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                foodList.add(dbCursor.getFoodItem());
                dbCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return foodList;
    }

    public Food getFoodByID(String foodID) {
        Food food;
        String[] selectionArgs = {foodID};
        Cursor cursor = db.query(FoodTable.TABLENAME, null, FoodTable.Cols.FOOD_ID + " = '" + foodID + "'", null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        if (cursor != null && cursor.getCount() > 0) {
            try {
                dbCursor.moveToFirst();
                food = dbCursor.getFoodItem();
            } finally {
                cursor.close();
            }
        } else food = null;

        return food;
    }


    public void addFoodItem(Food food) {
        ContentValues cv = new ContentValues();
        cv.put(FoodTable.Cols.FOOD_ID, food.getFood_id());
        cv.put(FoodTable.Cols.FOOD_NAME, food.getFood_name());
        cv.put(FoodTable.Cols.FOOD_PRICE, food.getFood_price());
        cv.put(FoodTable.Cols.FOOD_DESC, food.getFood_description());
        cv.put(FoodTable.Cols.RES_ID, food.getRe_id());
        cv.put(FoodTable.Cols.FOOD_IMAGE_PATH, food.getFood_imagePath());
        db.insert(FoodTable.TABLENAME, null, cv);
    }

    public void addFoodList() {
        Data data = new Data();
        ArrayList<Food> foodList = data.makeFoodList();
        for (int i = 0; i < foodList.size(); i++) {
            addFoodItem(foodList.get(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addOrderItem(Order order, String userID) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm");

        ContentValues cv = new ContentValues();
        cv.put(OrderHistoryTable.Cols.USER_ID, userID);
        cv.put(OrderHistoryTable.Cols.FOOD_ITEM, order.getItem());
        cv.put(OrderHistoryTable.Cols.RES_ID, order.getRestaurant());
        cv.put(OrderHistoryTable.Cols.UNIT_PRICE, order.getUnitPrice());
        cv.put(OrderHistoryTable.Cols.AMOUNT, order.getAmount());
        cv.put(OrderHistoryTable.Cols.TOTAL_PRICE, order.getTotalPrice());
        cv.put(OrderHistoryTable.Cols.DATE, String.valueOf(LocalDateTime.now().format(format)));
        db.insert(OrderHistoryTable.TABLENAME, null, cv);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addOrderList(String userID) {
        for (int i = 0; i < MainActivity.orderList.size(); i++) {
            addOrderItem(MainActivity.orderList.get(i), userID);
        }
    }

    public ArrayList<History> getUserHistory(String userEmail) {
        ArrayList<History> historyList = new ArrayList<>();
        Cursor cursor = db.query(OrderHistoryTable.TABLENAME, null, OrderHistoryTable.Cols.USER_ID + " = '" + userEmail + "'", null, null, null, null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                historyList.add(dbCursor.getHistoryItem());
                dbCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return historyList;
    }
}
