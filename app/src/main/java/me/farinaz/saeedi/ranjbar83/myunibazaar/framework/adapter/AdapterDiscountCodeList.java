package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.DiscountCode;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.dicount.ActivityDiscountCodeList;

import java.util.ArrayList;

public class AdapterDiscountCodeList extends ActivityDiscountCodeList<DiscountCode> {

    public AdapterDiscountCodeList(Context context, ArrayList<DiscountCode> list) {
        super(context, list);
    }

    public static class DiscountCodeViewHolder extends ActivityDiscountCodeList.ViewHolder {

        TextView txtPrice;
        TextView txtScope;
    }

    @Override
    public ActivityDiscountCodeList.ViewHolder assign(View convertView) {
        DiscountCodeViewHolder viewHolder = new DiscountCodeViewHolder();

        viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txt_price);
        viewHolder.txtScope = (TextView) convertView.findViewById(R.id.txt_scope);
        return viewHolder;
    }

    @Override
    public void fill(ActivityDiscountCodeList.ViewHolder upcastedViewHolder, DiscountCode item) {
        DiscountCodeViewHolder viewHolder = (DiscountCodeViewHolder) upcastedViewHolder;

        viewHolder.txtPrice.setText(item.getAmount());
        viewHolder.txtScope.setText(item.getScope());

        viewHolder.txtPrice.setTypeface(G.fontNumberPrice);
        viewHolder.txtScope.setTypeface(G.fontTitle);



    }
}

