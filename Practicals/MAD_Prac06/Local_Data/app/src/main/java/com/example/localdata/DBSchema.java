package com.example.localdata;

public class DBSchema {
    public static class FactionTable{
        public static final String TABLE_NAME = "Faction";
        public static class Cols {
            public static final String ID = "f_id";
            public static final String NAME = "f_name";
            public static final String STRENGTH = "f_strength";
            public static final String RELATIONSHIP = "f_relationship";
        }
    }
}
