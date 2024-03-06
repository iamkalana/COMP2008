package com.example.localdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase sqLiteDatabase;
    public void load(Context context){
        this.sqLiteDatabase = new DBHelper(context).getWritableDatabase();
    }

    public void add(Faction faction){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.FactionTable.Cols.ID, faction.getId());
        cv.put(DBSchema.FactionTable.Cols.NAME, faction.getName());
        cv.put(DBSchema.FactionTable.Cols.STRENGTH, faction.getStrength());
        cv.put(DBSchema.FactionTable.Cols.RELATIONSHIP, faction.getRelationship());
        sqLiteDatabase.insert(DBSchema.FactionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Faction> getAllFactions(){
        ArrayList<Faction> factionList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBSchema.FactionTable.TABLE_NAME,null,null,null,null,null,null);
        DBCursor factionDBCursor = new DBCursor(cursor);
        try{
            factionDBCursor.moveToFirst();
            while(!factionDBCursor.isAfterLast()){
                factionList.add(factionDBCursor.getFaction());
                factionDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return factionList;
    }

    public void update(Faction faction){

        ContentValues cv = new ContentValues();
        cv.put(DBSchema.FactionTable.Cols.ID, faction.getId());
        cv.put(DBSchema.FactionTable.Cols.NAME, faction.getName());
        cv.put(DBSchema.FactionTable.Cols.STRENGTH, faction.getStrength());
        cv.put(DBSchema.FactionTable.Cols.RELATIONSHIP, faction.getRelationship());

        String[] whereValue = { String.valueOf(faction.getId()) };
        sqLiteDatabase.update(DBSchema.FactionTable.TABLE_NAME, cv,
                DBSchema.FactionTable.Cols.ID + " = ?", whereValue);

    }

    public void delete(Faction faction){
        String[] whereValue = { String.valueOf(faction.getId()) };
        sqLiteDatabase.delete(DBSchema.FactionTable.TABLE_NAME,
                DBSchema.FactionTable.Cols.ID + " = ?", whereValue);
    }
}
