package com.example.myserviceshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "ProductData.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ProductDetails(fieldNamaBarang TEXT primary key, fieldJumlahStock TEXT, fieldHargaJual TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists ProductDetails");
    }

    public Boolean insertProductData (String fieldNamaBarang, String fieldJumlahStock, String fieldHargaJual){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fieldNamaBarang", fieldNamaBarang);
        contentValues.put("fieldJumlahStock", fieldJumlahStock);
        contentValues.put("fieldHargaJual", fieldHargaJual);

        long result=DB.insert("ProductDetails", null, contentValues);
        if (result==1){
            return false;
        } else {
            return true;

        }
    }

    public Boolean updateProductData (String fieldNamaBarang, String fieldJumlahStock, String fieldHargaJual){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fieldJumlahStock", fieldJumlahStock);
        contentValues.put("fieldHargaJual", fieldHargaJual);
        Cursor cursor = DB.rawQuery("Select * from ProductDetails where fieldNamaBarang=?", new String[]{fieldNamaBarang});
        if (cursor.getCount()>0) {
            long result = DB.update("ProductDetails", contentValues, "fieldNamaBarang=?", new String[]{fieldNamaBarang});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }
        else {
                return false;
            }
        }


    public Boolean deleteProductData (String fieldNamaBarang){
        SQLiteDatabase DB= this.getWritableDatabase();
         Cursor cursor = DB.rawQuery("Select * from ProductDetails where fieldNamaBarang=?", new String[]{fieldNamaBarang});
        if (cursor.getCount()>0) {
            long result = DB.delete("ProductDetails", "fieldNamaBarang=?", new String[]{fieldNamaBarang});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getdata (){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ProductDetails", null);
        return cursor;
    }

}



