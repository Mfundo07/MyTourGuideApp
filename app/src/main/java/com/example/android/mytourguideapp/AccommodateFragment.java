package com.example.android.mytourguideapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/27/2017.
 */

public class AccommodateFragment extends Fragment {

    private ListView tToursListView;
    private ToursAdapter tToursAdapter;
    private DatabaseReference tToursHeadDatabaseReference;
    private ChildEventListener tChildEventListener;
    private FirebaseDatabase tFirebaseDatabase;
    FirebaseStorage tFirebaseStorage;

    public AccommodateFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tour_list, container, false);


        tFirebaseDatabase = FirebaseDatabase.getInstance();
        tFirebaseStorage = FirebaseStorage.getInstance();

        tToursHeadDatabaseReference = tFirebaseDatabase.getReference()
                .child("Stay");


        tToursListView = rootView.findViewById(R.id.list);
        final List<Tours> tours = new ArrayList<>();
        tToursAdapter = new ToursAdapter(getActivity(),R.layout.item_cards,tours);
        tToursListView.setAdapter(tToursAdapter);






        tChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tours tour = dataSnapshot.getValue(Tours.class);
                tToursAdapter.add(tour);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        tToursHeadDatabaseReference.addChildEventListener(tChildEventListener);

        tToursListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_more_info,null);
                ImageView photoImageView = dialogView.findViewById(R.id.profile_image_view);
                TextView headTextView =  dialogView.findViewById(R.id.profile_heading);
                TextView descriptTextView = dialogView.findViewById(R.id.profile_description);
                TextView addressTextView = dialogView.findViewById(R.id.profile_address);
                TextView telephoneTextView = dialogView.findViewById(R.id.profile_telephone);
                TextView hoursTextView = dialogView.findViewById(R.id.profile_hours);


                Tours tour = tours.get(i);

                boolean isPhoto = tour.getPhotoUrl() != null;
                if (isPhoto){
                    Glide.with(photoImageView.getContext())
                            .load(tour.getPhotoUrl())
                            .into(photoImageView);
                    headTextView.setText(tour.getHead());
                    descriptTextView.setText(tour.getDescription());
                    addressTextView.setText(tour.getAddress());
                    telephoneTextView.setText(tour.getPhone());
                    hoursTextView.setText(tour.getHours());
                }
                else{
                    photoImageView.setVisibility(View.GONE);
                    headTextView.setText(tour.getHead());
                    descriptTextView.setText(tour.getDescription());
                    addressTextView.setText(tour.getAddress());
                    telephoneTextView.setText(tour.getPhone());
                    hoursTextView.setText(tour.getHours());
                }
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();


                alertDialog.show();
            }
        });







        return rootView;
    }
}
