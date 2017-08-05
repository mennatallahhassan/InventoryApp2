package com.ntl.nineth.inventoryapp;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by boody 2 on 15/07/2017.
 */

public class ProductViewHolder {

    TextView product_name;
    TextView price;
    TextView current_quantity;
    ImageButton sale_button;
    ImageView picture;


    public ProductViewHolder(View itemView) {
        this.product_name = (TextView) itemView.findViewById(R.id.product_name);
        this.price = (TextView) itemView.findViewById(R.id.price);
        this.current_quantity = (TextView) itemView.findViewById(R.id.current_quantity);
        this.sale_button = (ImageButton) itemView.findViewById(R.id.sale_button);
        this.picture = (ImageView) itemView.findViewById(R.id.picture1);
    }
}
