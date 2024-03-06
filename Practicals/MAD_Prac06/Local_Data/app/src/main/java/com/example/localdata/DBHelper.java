package com.example.localdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.localdata.DBSchema.FactionTable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DBNAME = "faction.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + FactionTable.TABLE_NAME + "("+
                FactionTable.Cols.ID+" Text, "+
                FactionTable.Cols.NAME + " Text, "+
                FactionTable.Cols.STRENGTH + " Text, "+
                FactionTable.Cols.RELATIONSHIP + " Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
