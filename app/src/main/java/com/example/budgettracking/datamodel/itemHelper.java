package com.example.budgettracking.datamodel;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class itemHelper extends SQLiteOpenHelper {
    public static final String database_name="budgetTracking.db";
    public static final String item_table_name="items";
    public static final String column_item_id="item_id";
    public static final String column_user_id="user_id";
    public static final String column_item_name="item_name";
    public static final String column_date_added="date_added";
    public static final String column_price="item_price";

    public itemHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table items " +
                        "(item_id integer primary key autoincrement, user_id text,item_name text, data_added text,item_price real )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void insert_item(Items item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_date_added,item.getDate_added());
        values.put(column_item_name,item.getItem_name());
        values.put(column_price,item.getPrice());
        values.put(column_user_id,item.getUser_id());
        db.insert(item_table_name,null,values);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Items> getAllItems(users user){
        ArrayList<Items> ItemsList = new ArrayList<Items>();
        String selectQuery =  "select * from items where user_id="+user.getUser_id()+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
               Items item=new Items();
                item.setItem_id(cursor.getInt(cursor.getColumnIndex(column_item_id)));
                item.setUser_id(cursor.getInt(cursor.getColumnIndex(column_user_id)));
                item.setItem_name(cursor.getString(cursor.getColumnIndex(column_item_name)));
                item.setDate_added(cursor.getString(cursor.getColumnIndex(column_date_added)));
                item.setPrice(cursor.getDouble(cursor.getColumnIndex(column_price)));
                ItemsList.add(item);
            } while(cursor.moveToNext());

        }
        return ItemsList;
    }

    @SuppressLint("Range")
    public Items getOneItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items where item_id="+id+"", null );
        Items item=new Items();
        item.setItem_id(res.getInt(res.getColumnIndex(column_item_id)));
        item.setItem_name(res.getString(res.getColumnIndex(column_item_name)));
        item.setPrice(res.getDouble(res.getColumnIndex(column_price)));
        item.setDate_added(res.getString(res.getColumnIndex(column_date_added)));
        item.setUser_id(res.getInt(res.getColumnIndex(column_user_id)));
        return item;
    }

    public void deleteItem(Items item){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete("items",
                "id = ? ",
                new String[] { Integer.toString(item.getItem_id()) });
    }


}
