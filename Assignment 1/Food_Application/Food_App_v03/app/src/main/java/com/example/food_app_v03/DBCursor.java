package com.example.food_app_v03;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.food_app_v03.DBSchema.UserTable;
import com.example.food_app_v03.DBSchema.RestaurantTable;
import com.example.food_app_v03.DBSchema.FoodTable;
import com.example.food_app_v03.DBSchema.OrderHistoryTable;

public class DBCursor extends CursorWrapper {
    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String name = getString(getColumnIndex(UserTable.Cols.NAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String address = getString(getColumnIndex(UserTable.Cols.ADDRESS));
        String phone = getString(getColumnIndex(UserTable.Cols.PHONE));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        return new User(name, email, address, phone, password);
    }

    public Restaurant getRestaurant(){
        String res_id = getString(getColumnIndex(RestaurantTable.Cols.RES_ID));
        String res_name = getString(getColumnIndex(RestaurantTable.Cols.RES_NAME));
        int res_image = getInt(getColumnIndex(RestaurantTable.Cols.RES_IMAGE_PATH));
        String res_address = getString(getColumnIndex(RestaurantTable.Cols.RES_ADDRESS));
        return new Restaurant(res_id, res_name, res_image, res_address);
    }

    public Food getFoodItem(){
        String food_id = getString(getColumnIndex(FoodTable.Cols.FOOD_ID));
        String food_name = getString(getColumnIndex(FoodTable.Cols.FOOD_NAME));
        String food_price = getString(getColumnIndex(FoodTable.Cols.FOOD_PRICE));
        String food_desc = getString(getColumnIndex(FoodTable.Cols.FOOD_DESC));
        String res_id = getString(getColumnIndex(FoodTable.Cols.RES_ID));
        int food_image = getInt(getColumnIndex(FoodTable.Cols.FOOD_IMAGE_PATH));
        return new Food(food_id, food_name, food_price, food_desc, res_id, food_image);
    }

    public History getHistoryItem(){
        String date = getString(getColumnIndex(OrderHistoryTable.Cols.DATE));
        String orderID = getString(getColumnIndex(OrderHistoryTable.Cols.ORDER_ID));
        String userID = getString(getColumnIndex(OrderHistoryTable.Cols.USER_ID));
        String itemName = getString(getColumnIndex(OrderHistoryTable.Cols.FOOD_ITEM));
        String restaurant = getString(getColumnIndex(OrderHistoryTable.Cols.RES_ID));
        String unitPrice = getString(getColumnIndex(OrderHistoryTable.Cols.UNIT_PRICE));
        String amount = getString(getColumnIndex(OrderHistoryTable.Cols.AMOUNT));
        String totalPrice = getString(getColumnIndex(OrderHistoryTable.Cols.TOTAL_PRICE));
        return new History(date, orderID, userID, itemName, restaurant, unitPrice, amount, totalPrice);
    }
}
