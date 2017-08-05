package com.ntl.nineth.inventoryapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

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

        TextView current_quantity2 = (TextView) findViewById(R.id.current_quantity2);
        current_quantity2.setText("Quantity: " + product.getCurrent_quantity());

        TextView supplierContact = (TextView) findViewById(R.id.Supplier_Contact);
        supplierContact.setText("Supplier Contact: " + product.getSupplierMobile());

        ImageView picture = (ImageView) findViewById(R.id.picture2);
        picture.setImageURI(Uri.parse("content://media" + product.getPicture()));

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

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + product.getSupplierMobile()));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

    }
}
