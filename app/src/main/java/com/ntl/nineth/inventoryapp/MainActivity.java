package com.ntl.nineth.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList = new ArrayList<>();
    Product product;
    ///meeting
    ProductAdapter productAdapter;
    ListView listView;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(getApplicationContext());

        if (productArrayList.size() > 0) {
            TextView noContent = (TextView) findViewById(R.id.noContent);
            noContent.setVisibility(View.GONE);
            noContent.setVisibility(View.INVISIBLE);
            productArrayList = dbHandler.getAllProduct();

            productAdapter = new ProductAdapter(getApplicationContext(), R.layout.row_item, productArrayList);

            listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(productAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    product = productArrayList.get(position);
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                }
            });

        } else {


        }

        Button addd_product = (Button) findViewById(R.id.add_button);
        addd_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                // intent.putExtra("product", product);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView noContent = (TextView) findViewById(R.id.noContent);
        noContent.setVisibility(View.GONE);
        noContent.setVisibility(View.INVISIBLE);
        productArrayList = dbHandler.getAllProduct();
        if (productArrayList.size() > 0) {


            productAdapter = new ProductAdapter(getApplicationContext(), R.layout.row_item, productArrayList);

            listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(productAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    product = productArrayList.get(position);
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                }
            });
            productAdapter.notifyDataSetChanged();
        }
    }
}
