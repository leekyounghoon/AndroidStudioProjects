package com.leekh13.exerciserecord;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by leekh on 2015-05-08.
 */
public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "CREATE TABLE EXER_LIST( ID INTEGER PRIMARY KEY AUTOINCREMENT, exer_name TEXT);" );
        db.execSQL("CREATE TABLE EXER_RECORD( ID INTEGER PRIMARY KEY AUTOINCREMENT, exer_date TEXT, exer_name TEXT, exer_kg INTEGER, exer_count INTEGER, exer_intensity INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert( String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update( String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete( String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    /*
    public void select( String _query){
       SQLiteDatabase db = getReadableDatabase();
       Cursor cursor = db.rawQuery("", null);
       while( cursor.moveToNext() ){
           str += cursor.getInt(0) + cursor.getString(1);
       }

    }
    */

    public void select_EXER_LIST( ArrayList<String> entries ){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT exer_name FROM EXER_LIST ", null);

        while( cursor.moveToNext() ){
           entries.add( cursor.getString(0) );
        }

        db.close();
    }

    public void select_EXER_RECORD( ArrayList<ListData> record ){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  ID, exer_date , exer_name , exer_kg , exer_count , exer_intensity FROM EXER_RECORD ", null);

        while( cursor.moveToNext() ){
            ListData data = new ListData();
            data.ID = cursor.getInt(0);
            data.mDate = cursor.getString(1);
            data.mName = cursor.getString(2);
            data.mKg = cursor.getString(3);
            data.mCount = cursor.getString(4);

            record.add(data);

        }
        db.close();
    }

}
