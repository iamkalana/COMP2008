package com.example.food_app_v03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.food_app_v03.DBSchema.UserTable;
import com.example.food_app_v03.DBSchema.RestaurantTable;
import com.example.food_app_v03.DBSchema.FoodTable;
import com.example.food_app_v03.DBSchema.OrderHistoryTable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "food_app.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + UserTable.TABLENAME +
                "(" + UserTable.Cols.EMAIL + " Text PRIMARY KEY, " +
                UserTable.Cols.NAME + " Text, " +
                UserTable.Cols.ADDRESS + " Text, " +
                UserTable.Cols.PHONE + " Text, " +
                UserTable.Cols.PASSWORD + " Text);");

        sqLiteDatabase.execSQL("create table " +
                RestaurantTable.TABLENAME +
                "(" + RestaurantTable.Cols.RES_ID + " Text PRIMARY KEY, " +
                RestaurantTable.Cols.RES_NAME + " Text," +
                RestaurantTable.Cols.RES_ADDRESS + " Text," +
                RestaurantTable.Cols.RES_IMAGE_PATH + " INTEGER);");

        sqLiteDatabase.execSQL("create table " + FoodTable.TABLENAME +
                "(" + FoodTable.Cols.FOOD_ID + " Text, " +
                FoodTable.Cols.FOOD_NAME + " Text, " +
                FoodTable.Cols.FOOD_PRICE + " Text, " +
                FoodTable.Cols.FOOD_DESC + " Text, " +
                FoodTable.Cols.RES_ID + " Text,  " +
                FoodTable.Cols.FOOD_IMAGE_PATH + " INTEGER, " +
                "FOREIGN KEY (" + FoodTable.Cols.RES_ID + ") REFERENCES " + RestaurantTable.TABLENAME + " (" + RestaurantTable.Cols.RES_ID +
                "), PRIMARY KEY (" + FoodTable.Cols.FOOD_ID + ", " + FoodTable.Cols.RES_ID + ") );");

        sqLiteDatabase.execSQL("create table " + OrderHistoryTable.TABLENAME +
                "(" + OrderHistoryTable.Cols.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OrderHistoryTable.Cols.USER_ID + " Text, " +
                OrderHistoryTable.Cols.FOOD_ITEM + " Text, " +
                OrderHistoryTable.Cols.RES_ID + " Text, " +
                OrderHistoryTable.Cols.UNIT_PRICE + " Text," +
                OrderHistoryTable.Cols.AMOUNT + " Text," +
                OrderHistoryTable.Cols.TOTAL_PRICE + " Text," +
                OrderHistoryTable.Cols.DATE + " Date, " +
                "FOREIGN KEY (" + OrderHistoryTable.Cols.RES_ID + ") REFERENCES " + RestaurantTable.TABLENAME + " (" + RestaurantTable.Cols.RES_ID + ")," +
                "FOREIGN KEY (" + OrderHistoryTable.Cols.USER_ID + ") REFERENCES " + UserTable.TABLENAME + " (" + UserTable.Cols.EMAIL +
                ") );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
