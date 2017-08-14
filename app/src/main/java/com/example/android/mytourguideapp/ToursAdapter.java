package com.example.android.mytourguideapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 7/27/2017.
 */

public class  ToursAdapter extends ArrayAdapter<Tours> {
    public ToursAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tours> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_cards,parent,false);
        }

        ImageView photoImageView = listItemView.findViewById(R.id.image_view);
        TextView headTextView =  listItemView.findViewById(R.id.head_view);
        TextView addressTextView = listItemView.findViewById(R.id.address_view);
        TextView telephoneTextView = listItemView.findViewById(R.id.telephone_view);

        Tours tour = getItem(position);

        boolean isPhoto = tour.getPhotoUrl() != null;
        if (isPhoto){
            Glide.with(photoImageView.getContext())
                    .load(tour.getPhotoUrl())
                    .into(photoImageView);
            headTextView.setText(tour.getHead());
            addressTextView.setText(tour.getAddress());
            telephoneTextView.setText(tour.getPhone());
        }
         else{
            photoImageView.setVisibility(View.GONE);
            headTextView.setText(tour.getHead());
            addressTextView.setText(tour.getAddress());
            telephoneTextView.setText(tour.getPhone());
        }
        return listItemView;
    }
}
