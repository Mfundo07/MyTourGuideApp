package com.example.android.mytourguideapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/27/2017.
 */

public class AttractionsFragment extends Fragment {
    Context context;

    private static final String ANONYMOUS = "House party";
    private static  final String DESCRIPTION = "the house party of the season";

    private ListView tToursListView;
    private ToursAdapter tToursAdapter;
    Button tViewButton;
    private DatabaseReference tToursHeadDatabaseReference;
    private ChildEventListener tChildEventListener;
    private FirebaseDatabase tFirebaseDatabase;
    FirebaseStorage tFirebaseStorage;
    private StorageReference tPhotoStorageReference;
    String tDestinationDescription;
    String tDestinationName;



    public AttractionsFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tour_list, container, false);


        tFirebaseDatabase = FirebaseDatabase.getInstance();
        tFirebaseStorage = FirebaseStorage.getInstance();
        tDestinationName = ANONYMOUS;
        tDestinationDescription = DESCRIPTION;
        tToursHeadDatabaseReference = tFirebaseDatabase.getReference()
                .child("Views");

        tPhotoStorageReference = tFirebaseStorage.getReference().child("tour_photos");

        tToursListView = rootView.findViewById(R.id.list);
        List<Tours> tours = new ArrayList<>();
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







        return rootView;
    }



}
