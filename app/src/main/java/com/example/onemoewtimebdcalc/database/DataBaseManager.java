package com.example.onemoewtimebdcalc.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private Context context;
    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

   public DataBaseManager(Context context)
   {
       this.context = context;
       databaseHelper = new DatabaseHelper( context );

   }
   public void openDb()
   {
       db = databaseHelper.getWritableDatabase();
   }
   public void insertDb(String result)
   {
       ContentValues values = new ContentValues();
       values.put( CreatorDb.RESULT, result );
       db.insert( CreatorDb.TABLE_NAME , null , values );
   }

   public List<String> readFromDb()
   {
       List<String> array = new ArrayList<>();

       @SuppressLint("Recycle") Cursor cursor = db.query( CreatorDb.TABLE_NAME,null,null,
               null,null,null,null);

       while (cursor.moveToNext()){
           @SuppressLint("Range") String title = cursor.getString( cursor.getColumnIndex( CreatorDb.RESULT ) );
            array.add( title );
       }


       return array;

   }
   public void deleteFromDb(String text) {

       String select = CreatorDb.RESULT + " LIKE ?";
       String[] delete = { text };
       db.delete( CreatorDb.TABLE_NAME , select, delete );
   }
   public void CloseDb()
   {
       databaseHelper.close();
   }
}
