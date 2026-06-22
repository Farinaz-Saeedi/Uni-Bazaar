package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Request;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.request.ActivityRequestList;

import java.util.ArrayList;

public class AdapterRequestList extends ActivityRequestList<Request> {

    public AdapterRequestList(Context context, ArrayList<Request> list) {
        super(context, list);
    }

    public static class RequestViewHolder extends ViewHolder {

        TextView txtStoreName;
        TextView txtStoreDes;
        TextView txtDate;
    }

    @Override
    public ViewHolder assign(View convertView) {
        RequestViewHolder viewHolder = new RequestViewHolder();

        viewHolder.txtStoreName = (TextView) convertView.findViewById(R.id.txt_store_name);
        viewHolder.txtStoreDes = (TextView) convertView.findViewById(R.id.txt_store_des);
        viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txt_date);

        return viewHolder;
    }

    @Override
    public void fill(ViewHolder upcastedViewHolder, Request item) {
        RequestViewHolder viewHolder = (RequestViewHolder) upcastedViewHolder;

        viewHolder.txtDate.setTypeface(G.font3);
        viewHolder.txtStoreName.setTypeface(G.font2);
        viewHolder.txtStoreDes.setTypeface(G.font3);

        viewHolder.txtStoreName.setText(item.getStoreName());
        viewHolder.txtStoreDes.setText(item.getDescription());
        viewHolder.txtDate.setText(item.getDate());

    }
}

