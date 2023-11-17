package com.example.budgettracking.datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class userHelper extends SQLiteOpenHelper {

public static final String database_name="budget.db";
public static final String user_table_name="Allusers";
public static final String column_id="user_id";
public static final String column_name="name";
public static final String column_email="email";
public static final String column_password="password";
public static final String column_username="username";

    public userHelper( Context context) {
        super(context, database_name, null, 1);
        Log.d("========>", "userHelper: calling constguu");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("oncreatemethod", "create tableeeeeeeee: calling constguu");
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + user_table_name + " " +
                        "(" +
                        column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        column_name + " TEXT, " +
                        column_email + " TEXT, " +
                        column_password + " TEXT, " +
                        column_username + " TEXT" +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+user_table_name);
        onCreate(db);
    }

    public void insert_user(users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_name,user.getName());
        values.put(column_password,user.getPassword());
        values.put(column_email,user.getEmail());
       db.insert(user_table_name,null,values);
       db.close();
    }
    @SuppressLint("Range")
    public ArrayList<users> getAllUser(){
        ArrayList<users> userList = new ArrayList<users>();
        String selectQuery = "SELECT  * FROM " + user_table_name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
              users a_user=new users();
              a_user.setUser_id(cursor.getInt(cursor.getColumnIndex(column_id)));
              a_user.setName(cursor.getString(cursor.getColumnIndex(column_name)));
              a_user.setEmail(cursor.getString(cursor.getColumnIndex(column_email)));
              a_user.setPassword(cursor.getString(cursor.getColumnIndex(column_password)));

              userList.add(a_user);
            } while(cursor.moveToNext());

        }
        return userList;
    }

    public void deleteUser(users user){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete("Allusers",
                "user_id = ? ",
                new String[] { Integer.toString(user.getUser_id()) });
    }

}
