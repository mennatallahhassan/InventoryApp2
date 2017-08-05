package com.ntl.nineth.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by boody 2 on 28/07/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, DBContract.DB_name, null, DBContract.DB_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Product_Table.createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
            db.execSQL(DBContract.Product_Table.deleteTable);
        onCreate(db);
    }


    public Long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Product_Table.Product_Name, product.getProduct_Name());
        contentValues.put(DBContract.Product_Table.Current_Quantity, product.getCurrent_quantity());
        contentValues.put(DBContract.Product_Table.Price, product.getPrice());
        contentValues.put(DBContract.Product_Table.Supplier_Mobile, product.getSupplierMobile());
        contentValues.put(DBContract.Product_Table.Picture, product.getPicture());

        Long id;
        id = db.insert(DBContract.Product_Table.Table_Name, null, contentValues);
        return id;
    }
   public ArrayList<Product> getAllProduct() {
        ArrayList<Product> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + DBContract.Product_Table.Table_Name, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setProduct_Name(cursor.getString(1));
                product.setCurrent_quantity(Integer.parseInt(cursor.getString(2)));
                product.setPrice(Integer.parseInt(cursor.getString(3)));
                product.setSupplierMobile(cursor.getString(4));
                product.setPicture(cursor.getString(5));
                arrayList.add(product);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "Delete from " + DBContract.Product_Table.Table_Name + " where id = " + id;
        db.execSQL(delete);
        db.close();
    }


}
