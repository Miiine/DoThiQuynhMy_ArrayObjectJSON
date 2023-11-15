package com.example.dothiquynhmy_arrayobjectjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Customadapter  extends ArrayAdapter<JsonConstructors> {
    public Customadapter(Context context, int resource, List<JsonConstructors> items) {
        super(context, resource, items);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listview_item, null);
        }
        JsonConstructors p = getItem(position);
        if (p != null) {
            //anh xa + gan gia tri:
            TextView txtTitle = (TextView) view.findViewById(R.id.tvTitle);
            txtTitle.setText(p.tieude);
            TextView txtPrice = (TextView) view.findViewById(R.id.tvprice);
            txtPrice.setText(p.gia);
            TextView txtQuantity = (TextView) view.findViewById(R.id.tvquantity);
            txtQuantity.setText(p.soluong);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgV);
            Picasso.with(getContext()).load(p.anh).into(imageView);
        }
        return view;
    }
}