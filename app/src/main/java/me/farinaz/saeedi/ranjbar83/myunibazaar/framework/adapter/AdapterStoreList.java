package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper.ResourceMapper;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Store;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.store.ActivityStoreList;

import java.util.ArrayList;

public class AdapterStoreList extends ActivityStoreList<Store> {

    public AdapterStoreList(Context context, ArrayList<Store> list) {
        super(context, list);
    }

    public static class StoreViewHolder extends ActivityStoreList.ViewHolder {

        TextView txtStoreName;
        TextView txtOwner;
        TextView txtType;
        TextView txt1;
        TextView txt2;
        TextView txt3;
        ImageView image;
        ImageView imgFraudulent;
    }

    @Override
    public ActivityStoreList.ViewHolder assign(View convertView) {
        StoreViewHolder viewHolder = new StoreViewHolder();

        viewHolder.txtStoreName = (TextView) convertView.findViewById(R.id.txt_store_name);
        viewHolder.txtOwner = (TextView) convertView.findViewById(R.id.txt_owner);
        viewHolder.txtType = (TextView) convertView.findViewById(R.id.txt_type);
        viewHolder.txt1 = (TextView) convertView.findViewById(R.id.txt1);
        viewHolder.txt2 = (TextView) convertView.findViewById(R.id.txt2);
        viewHolder.txt3 = (TextView) convertView.findViewById(R.id.txt3);
        viewHolder.image = (ImageView) convertView.findViewById(R.id.img_store);
        viewHolder.imgFraudulent = (ImageView) convertView.findViewById(R.id.img_badge);

        return viewHolder;
    }

    @Override
    public void fill(ActivityStoreList.ViewHolder upcastedViewHolder, Store item) {
        StoreViewHolder viewHolder = (StoreViewHolder) upcastedViewHolder;

        viewHolder.txtStoreName.setText( item.getName());
        viewHolder.txtOwner.setText( item.getOwnerFirstName() + " " + item.getOwnerLastName());
        viewHolder.txtType.setText( item.getType());
        viewHolder.image.setImageResource(ResourceMapper.getDrawableResourceByNameWithContext(G.getContext(),item.getImage()));

        int flag = 0;
        if (item.getFraudulent()) {
            flag = View.VISIBLE;
        }else{
            flag = View.INVISIBLE;
        }
        viewHolder.imgFraudulent.setVisibility(flag);

        viewHolder.txt1.setTypeface(G.font2);
        viewHolder.txt2.setTypeface(G.font2);
        viewHolder.txt3.setTypeface(G.font2);
        viewHolder.txtStoreName.setTypeface(G.font1);
        viewHolder.txtOwner.setTypeface(G.font1);
        viewHolder.txtType.setTypeface(G.font1);

    }
}

