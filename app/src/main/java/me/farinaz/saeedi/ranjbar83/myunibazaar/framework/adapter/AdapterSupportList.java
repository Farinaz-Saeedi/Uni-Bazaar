package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper.DrawableResourceHelper;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Support;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.support.ActivitySupportInfo;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.support.ActivitySupportList;

import java.util.ArrayList;

public class AdapterSupportList extends ActivitySupportList< Support> {

    public AdapterSupportList(Context context, ArrayList<Support> list) {
        super(context, list);
    }

    public static class SupportViewHolder extends ViewHolder {

        ViewGroup root;
        TextView txtAdapterName;
        ImageView imgAdapterName;

    }

    @Override
    public ViewHolder assign(View convertView) {
        SupportViewHolder viewHolder = new SupportViewHolder();

        viewHolder.root = (ViewGroup) convertView;
        viewHolder.txtAdapterName = (TextView) convertView.findViewById(R.id.txt_adapter_name);
        viewHolder.imgAdapterName = (ImageView) convertView.findViewById(R.id.img_adapter_name);

        return viewHolder;
    }

    @Override
    public void fill(ViewHolder upcastedViewHolder, Support item) {
        SupportViewHolder viewHolder = (SupportViewHolder) upcastedViewHolder;
        viewHolder.txtAdapterName.setText(item.getFirsName() + " " + item.getLastName());
        viewHolder.txtAdapterName.setTypeface(G.font2);

        viewHolder.imgAdapterName.setImageDrawable(DrawableResourceHelper.getDrawableByName(G.getContext(),item.getImage()));
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int code = item.getSupportCode();
                String password = item.getPassword();
                String date = item.getDate();
                String image = item.getImage();
                String firstName = item.getFirsName();
                String lastName = item.getLastName();

                Intent intent = new Intent(v.getContext(), ActivitySupportInfo.class);
                intent.putExtra("password", password);
                intent.putExtra("date", date);
                intent.putExtra("image", image);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("code", code);

                v.getContext().startActivity(intent);
            }
        });

    }
}

