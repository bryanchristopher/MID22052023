package com.example.mid_202102276;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "mid_202102276.db";

    public DBHelper(@Nullable Context context) {
        super(context,"mid_202102276.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table buku(kodebuku TEXT primary key, judulbuku TEXT, pengarang TEXT, penerbit TEXT, nomorisbn TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists buku");
    }
    public Boolean insertData (String kodebuku,String judulbuku, String pengarang, String penerbit, String nomorisbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kodebuku",kodebuku);
        values.put("judulbuku",judulbuku);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("nomorisbn", nomorisbn);
        long result = db.insert("buku", null,values);
        if (result==1) return false;
        else
            return true;
    }
    public Boolean checkkodebuku (String kodebuku){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from buku where kodebuku=?", new String[] {kodebuku});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Boolean checkusernamepassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
