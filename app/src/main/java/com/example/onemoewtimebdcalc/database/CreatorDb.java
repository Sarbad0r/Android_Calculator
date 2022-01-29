package com.example.onemoewtimebdcalc.database;

import android.provider.BaseColumns;

public class CreatorDb {


    static String TABLE_NAME = "CalcTable";
    static String RESULT = "result";

    public static String DATABASE_NAME = "Calculator.db";
    public static int DATABASE_VERSION = 1;

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
            RESULT + " TEXT)";

    public static String DELETE_SQL_TABLE = "DROP TABLE IF EXIST " + TABLE_NAME;

}
