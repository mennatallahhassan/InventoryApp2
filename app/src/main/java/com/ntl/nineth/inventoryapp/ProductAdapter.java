package com.ntl.nineth.inventoryapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by boody 2 on 15/07/2017.
 */

public class ProductAdapter extends ArrayAdapter {

    ArrayList<Product> productArrayList;
    Context context;


    public ProductAdapter(Context context, int resource, ArrayList<Product> productArrayList) {
        super(context, resource, productArrayList);
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ProductViewHolder viewHolder;


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
            viewHolder = new ProductViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProductViewHolder) convertView.getTag();
        }
        if (productArrayList.size() > 0) {
            final Product product = productArrayList.get(position);
            viewHolder.picture.setImageURI(Uri.parse("content://media" + product.getPicture()));
            viewHolder.product_name.setText("" + product.getProduct_Name().toString());
            viewHolder.price.setText("Price: " + product.getPrice() + " L.E");
            viewHolder.current_quantity.setText("Quantity: " + product.getCurrent_quantity());
            viewHolder.sale_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (product.getCurrent_quantity() > 0) {
                        int reducedQuantity = product.getCurrent_quantity() - 1;
                        product.setCurrent_quantity(reducedQuantity);
                        viewHolder.current_quantity.setText("Quantity: " + reducedQuantity);
                    }
                }
            });
            viewHolder.sale_button.setFocusable(false);
        }
        return convertView;
    }


}
