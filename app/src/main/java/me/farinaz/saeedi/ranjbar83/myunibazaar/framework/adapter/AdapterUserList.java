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
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.User;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.user.ActivityUserInfo;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.user.ActivityUserList;

import java.util.ArrayList;

public class AdapterUserList extends ActivityUserList<User> {

    public AdapterUserList(Context context, ArrayList<User> list) {
        super(context, list);
    }

    public static class UserViewHolder extends ViewHolder {

        ViewGroup root;
        TextView txtAdapterName;
        ImageView imgAdapterName;
    }

    @Override
    public ViewHolder assign(View convertView) {
        UserViewHolder viewHolder = new UserViewHolder();

        viewHolder.root = (ViewGroup) convertView;
        viewHolder.txtAdapterName = (TextView) convertView.findViewById(R.id.txt_adapter_name);
        viewHolder.imgAdapterName = (ImageView) convertView.findViewById(R.id.img_adapter_name);

        return viewHolder;
    }

    @Override
    public void fill(ViewHolder upcastedViewHolder, User item) {
        UserViewHolder viewHolder = (UserViewHolder) upcastedViewHolder;

        viewHolder.txtAdapterName.setTypeface(G.font2);
        viewHolder.txtAdapterName.setText(item.getFirsName() + " " + item.getLastName());

        viewHolder.imgAdapterName.setImageDrawable(DrawableResourceHelper.getDrawableByName(G.getContext(),item.getImage()));
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = item.getUserId();
                String email = item.getEmail();
                String phone = item.getPhone();
                String image = item.getImage();
                String firstName = item.getFirsName();
                String lastName = item.getLastName();

                Intent intent = new Intent(v.getContext(), ActivityUserInfo.class);
                intent.putExtra("id", id);
                intent.putExtra("email", email);
                intent.putExtra("image", image);
                intent.putExtra("phone", phone);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);

                v.getContext().startActivity(intent);


            }
        });

    }
}

