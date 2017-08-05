package com.ntl.nineth.inventoryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {
    Product product;
    EditText product_name;
    EditText price;
    EditText quantity;
    EditText supplierContact;
    String path;
    ImageView pic;
    Uri imagePathURI;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            imagePathURI = data.getData();
            pic.setImageURI(imagePathURI);
            path = imagePathURI.getPath();
            //String path = Uri.parse(imagePathURI);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pic.setVisibility(View.GONE);
        if (path != null) {
            pic.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        product = new Product();

        product_name = (EditText) findViewById(R.id.product_name3);
        price = (EditText) findViewById(R.id.price3);
        quantity = (EditText) findViewById(R.id.current_quantity3);
        pic = (ImageView) findViewById(R.id.pic3);
        supplierContact = (EditText) findViewById(R.id.Supplier_Contact3);

        Button uploadImage_Btn = (Button) findViewById(R.id.uploadImg_Btn);
        uploadImage_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        Button button = (Button) findViewById(R.id.add_button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_name.getText().length() > 0) {
                    if (Integer.parseInt(price.getText().toString()) > 0 && !TextUtils.isEmpty(price.getText())) {
                        if (Integer.parseInt(quantity.getText().toString()) > 0 && !TextUtils.isEmpty(quantity.getText())) {
                            if (Long.parseLong(supplierContact.getText().toString()) > 0 && !TextUtils.isEmpty(supplierContact.getText())) {
                                if (path != null) {
                                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                                    Product product = new Product();
                                    product.setProduct_Name(product_name.getText().toString());
                                    product.setCurrent_quantity(Integer.parseInt(quantity.getText().toString()));
                                    product.setPrice(Integer.parseInt(price.getText().toString()));
                                    product.setSupplierMobile(supplierContact.getText().toString());
                                    product.setPicture(path);
                                    dbHandler.addProduct(product);
                                    Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Picture must be added! ",
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Supplier must contain NUMBERs & > 0! ",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Quantity must contain NUMBERs & > 0 !",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Price must contain NUMBERs & >0 !",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Add the product name please!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
