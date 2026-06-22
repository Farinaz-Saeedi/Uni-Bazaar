package me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;

import java.util.ArrayList;


public abstract class ActivityRequestList<T> extends ArrayAdapter<T> {
    private LayoutInflater inflater;

    public ActivityRequestList(Context context, ArrayList<T> list) {
        super(context, 0, list);
        inflater = LayoutInflater.from(context);
    }

    public static class ViewHolder {}

    public abstract ViewHolder assign(View convertView);
    public abstract void fill(ViewHolder upcastedViewHolder, T item);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_request, parent, false);

            viewHolder = assign(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        fill(viewHolder, item);
        return convertView;
    }
}
