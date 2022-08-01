package com.example.otp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ommapawon.db";

    //tabel cart
    public static final String TABLE_NAME ="cart";
    public static final String col_1 = "id";
    public static final String col_2 = "rumah_makan_id";
    public static final String col_3 = "menu_id";
    public static final String col_4 = "harga";
    public static final String col_5 = "qty";
    public static final String col_6 = "catatan";
    public static final String col_7 = "nama_makanan";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,rumah_makan_id TEXT,menu_id TEXT,harga Double,qty INTEGER, catatan TEXT, nama_makanan TEXT) "); //tabel chart

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //insert cart
    public boolean insertDataCart(String rumah_makan_id, String menu_id, Double harga, Integer qty, String catatan, String nama_makanan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,rumah_makan_id);
        contentValues.put(col_3,menu_id);
        contentValues.put(col_4,harga);
        contentValues.put(col_5,qty);
        contentValues.put(col_6,catatan);
        contentValues.put(col_7,nama_makanan);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return  true;
    }

    //get all cart
    public Cursor getAllCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ras = db.rawQuery("select * from "+TABLE_NAME,null);
        return ras ;
    }
    //get by id
    public Cursor getByID(String menu_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ras = db.query(TABLE_NAME,null,"id_menu = ?",new String[]{menu_id},null,null,null);
        return ras ;
    }

    //total
    public Cursor getTotal(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ras = db.rawQuery("select harga*qty AS SUB_TOTAL from "+TABLE_NAME,null);
        return ras ;
    }

    //delete one
    public int deleteCart(int id){
        String strID = String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] {strID});
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + TABLE_NAME);
    }

    //update cart
    public Boolean UpdateCart (int qty,String menu_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_5, qty);
        db.update(TABLE_NAME, contentValues, "id_menu = ?", new String[]{menu_id});
        return true;
    }

}
