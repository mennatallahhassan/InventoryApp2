package com.ntl.nineth.inventoryapp;

import java.io.Serializable;

/**
 * Created by boody 2 on 15/07/2017.
 */

public class Product implements Serializable {
    private int id;
    private String product_Name;
    private String picture;
    private int current_quantity;
    private int price;
    private String supplierMobile;


    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public int getCurrent_quantity() {
        return current_quantity;
    }

    public void setCurrent_quantity(int current_quantity) {
        this.current_quantity = current_quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }
}
