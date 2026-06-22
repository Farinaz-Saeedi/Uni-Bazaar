package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper.ResourceMapper;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Product;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.product.ActivityProductList;

import java.util.ArrayList;

public class AdapterProductList extends ActivityProductList<Product> {

    public AdapterProductList(Context context, ArrayList<Product> list) {
        super(context, list);
    }

    public static class ProductViewHolder extends ViewHolder {

        TextView txtTitle;
        TextView txtDis;
        TextView txtPrice;
        ImageView imgProduct;
    }

    @Override
    public ViewHolder assign(View convertView) {
        ProductViewHolder viewHolder = new ProductViewHolder();

        viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txt_price);
        viewHolder.txtDis = (TextView) convertView.findViewById(R.id.txt_dis);
        viewHolder.imgProduct = (ImageView) convertView.findViewById(R.id.img_product);
        return viewHolder;
    }

    @Override
    public void fill(ViewHolder upcastedViewHolder, Product item) {
        ProductViewHolder viewHolder = (ProductViewHolder) upcastedViewHolder;

        viewHolder.txtTitle.setText(item.getTitle());
        viewHolder.txtDis.setText(item.getDis());
        viewHolder.txtPrice.setText(item.getPrice());

        viewHolder.txtPrice.setTypeface(G.fontTitle);
        viewHolder.txtTitle.setTypeface(G.font2);
        viewHolder.txtDis.setTypeface(G.font1);

        viewHolder.imgProduct.setImageResource(ResourceMapper.getDrawableResourceByNameWithContext(G.getContext(),item.getImage()));
    }
}

