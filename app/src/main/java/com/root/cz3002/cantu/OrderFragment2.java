package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.ToReceiveData;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment2 extends Fragment{
    private DatabaseReference databaseReference;

    public OrderFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("dabao");
        final ArrayList<ToReceiveData> toReceiveRequests = new ArrayList<ToReceiveData>();
        //toReceiveRequests.add(new ToReceiveData(2.80, "steamed chicken rice", "chicken rice","CAN A",2,5.6,"Self-Collect"));
        //toReceiveRequests.add(new ToReceiveData(4.50, "fish bread crumb", "italian","CAN A",1,4.5,"Wait Dabaoer"));
        //toReceiveRequests.add(new ToReceiveData(2.80, "roasted chicken rice", "chicken rice","CAN A",1,2.8,"Self-Collect"));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    for(DataSnapshot d1: d.getChildren()){
                        for(DataSnapshot d2: d1.getChildren()){
                            ToReceiveData r = new ToReceiveData();
                            r.setCanteenName(d2.child("canteenName").getValue(String.class));
                            r.setFoodName(d2.child("foodName").getValue(String.class));
                            r.setPrice(d2.child("price").getValue(Double.class));
                            r.setTotalPrice(d2.child("totalPrice").getValue(Double.class));
                            r.setFoodName(d2.child("foodName").getValue(String.class));
                            r.setStallName(d2.child("stallName").getValue(String.class));
                            r.setStatus(d2.child("collectStatus").getValue(String.class));//collection status
                            toReceiveRequests.add(r);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        ToReceiveAdapter toReceiveAdapter = new ToReceiveAdapter(getActivity(),toReceiveRequests);

        View rootView = inflater.inflate(R.layout.list_view_from_button, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(toReceiveAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }
}
