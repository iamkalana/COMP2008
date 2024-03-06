package com.example.localdata;

import android.database.Cursor;
import android.database.CursorWrapper;

public class DBCursor extends CursorWrapper {
    public DBCursor(Cursor cursor) {
        super(cursor);
    }

    public Faction getFaction(){
        int id = Integer.parseInt(getString(getColumnIndex(DBSchema.FactionTable.Cols.ID)));
        String name = getString(getColumnIndex(DBSchema.FactionTable.Cols.NAME));
        int strength = Integer.parseInt(getString(getColumnIndex(DBSchema.FactionTable.Cols.STRENGTH)));
        int relationship = Integer.parseInt(getString(getColumnIndex(DBSchema.FactionTable.Cols.RELATIONSHIP)));
        return new Faction(id,name,strength,relationship);
    }

}
