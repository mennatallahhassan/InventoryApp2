package com.ntl.nineth.inventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    // TextView current_quantity2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        final Product product = (Product) intent.getSerializableExtra("product");
        TextView product_name2 = (TextView) findViewById(R.id.product_name2);
        product_name2.setText(product.getProduct_Name());

        TextView price2 = (TextView) findViewById(R.id.price2);
        price2.setText("Price: " + product.getPrice() + " L.E");

        final TextView current_quantity2 = (TextView) findViewById(R.id.current_quantity2);
        current_quantity2.setText("Quantity: " + product.getCurrent_quantity());

        TextView supplierContact = (TextView) findViewById(R.id.Supplier_Contact);
        supplierContact.setText("Supplier Contact: " + product.getSupplierMobile());

        ImageView picture = (ImageView) findViewById(R.id.picture2);
        picture.setImageURI(Uri.parse("" + product.getPicture()));

        Button deleteBtn = (Button) findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DBHandler dbHandler = new DBHandler(getApplicationContext());


                AlertDialog.Builder alertBox = new AlertDialog.Builder(DetailActivity.this);
                alertBox.setTitle("Deleting Action");
                alertBox.setMessage("Are you sure delete this item?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHandler.deleteProduct(product.getId());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                DetailActivity.this.finish();
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertBox.create();
                alertDialog.show();


            }
        });
        Button orderBtn = (Button) findViewById(R.id.order_btn);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String supplierPhone = product.getSupplierMobile();
                Uri call = Uri.parse("tel: " + supplierPhone);
                Intent phone = new Intent(Intent.ACTION_DIAL, call);
                startActivity(phone);
            }
        });
        ImageButton increaseBtn = (ImageButton) findViewById(R.id.add_button2);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (product.getCurrent_quantity() < 9999) {
                    int increasedQuantity = product.getCurrent_quantity() + 1;
                    product.setCurrent_quantity(increasedQuantity);
                    current_quantity2.setText("Quantity: " + increasedQuantity);

                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    dbHandler.updateProduct(product.getId(), increasedQuantity);

                }
            }
        });


        ImageButton decreaseBtn = (ImageButton) findViewById(R.id.reduce_button2);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getCurrent_quantity() > 0) {
                    int decreasedQuantity = product.getCurrent_quantity() - 1;
                    product.setCurrent_quantity(decreasedQuantity);
                    current_quantity2.setText("Quantity: " + decreasedQuantity);

                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    dbHandler.updateProduct(product.getId(), decreasedQuantity);

                }
            }
        });
    }
}
