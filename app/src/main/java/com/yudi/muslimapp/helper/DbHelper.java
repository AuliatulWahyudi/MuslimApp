package com.yudi.muslimapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "digitalent.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "nomorhp";
    public static final String COLUMN_GENDER= "gender";
    public static final String COLUMN_LATITUDE= "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE "+ TABLE_SQLite +"( "+
                COLUMN_ID + " INTEGER  PRIMARY KEY autoincrement, "+
                COLUMN_NAME + " TEXT NOT NULL, "+
                COLUMN_ADDRESS + " TEXT NOT NULL, "+
                COLUMN_PHONE + " TEXT NOT NULL, "+
                COLUMN_GENDER + " TEXT NOT NULL, "+
                COLUMN_LATITUDE + " TEXT NOT NULL, "+
                COLUMN_LONGITUDE + " TEXT NOT NULL "+
                " )";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<HashMap<String, String >> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_ADDRESS, cursor.getString(2));
                map.put(COLUMN_PHONE, cursor.getString(3));
                map.put(COLUMN_GENDER, cursor.getString(4));
                map.put(COLUMN_LATITUDE, cursor.getString(5));
                map.put(COLUMN_LONGITUDE, cursor.getString(6));
                wordList.add(map);
            }while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String name, String address, String phone, String gen, String lat, String longi){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO "+ TABLE_SQLite + " (name, address, nomorhp, gender, latitude, longitude) "+
                "VALUES ('"+ name +"', '"+ address + "', '"+ phone + "', '"+ gen + "', '"+ lat + "', '"+ longi + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String address, String phone, String gen, String lat, String longi){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE "+ TABLE_SQLite +" SET "+
                COLUMN_NAME + "= '" + name + "', " +
                COLUMN_ADDRESS + "= '" + address + "' "+
                COLUMN_PHONE + "= '" + phone + "' "+
                COLUMN_GENDER + "= '" + gen + "' "+
                COLUMN_LATITUDE + "= '" + lat + "' "+
                COLUMN_LONGITUDE + "= '" + longi + "' "+
                " WHERE "+ COLUMN_ID + "= '" + id + "'";
        Log.e("update sqlite", "" + updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM "+ TABLE_SQLite +" WHERE "+ COLUMN_ID +"= '"+ id +"'";
        Log.e("delete sqlite","" + updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
