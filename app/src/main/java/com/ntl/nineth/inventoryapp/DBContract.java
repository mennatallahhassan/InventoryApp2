package com.ntl.nineth.inventoryapp;

import android.provider.BaseColumns;

/**
 * Created by boody 2 on 28/07/2017.
 */


public class DBContract {
    public static final int DB_version = 18;
    public static final String DB_name = "database.db";

    public class Product_Table implements BaseColumns {

        public static final String Table_Name = "product";
        public static final String Id = "id";
        public static final String Product_Name = "productName";
        public static final String Picture = "picture";
        public static final String Current_Quantity = "currentQuantity";
        public static final String Price = "price";
        public static final String Supplier_Mobile = "supplierMobile";

        public static final String createTable =
                "Create table " + Table_Name + " (" + Id + " integer primary key, " + Product_Name + " Text, " + Current_Quantity + " integer, " + Price + " integer, " + Supplier_Mobile + " Text, " + Picture + " text)";
        public static final String deleteTable = "Drop table if exists " + Table_Name;
    }
}
