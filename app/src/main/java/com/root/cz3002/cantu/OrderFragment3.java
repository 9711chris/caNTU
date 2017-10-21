package com.root.cz3002.cantu;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.WaitingDabaoer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment3 extends Fragment {
    private ChildEventListener dabaoChildEventListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dabaoDatabaseReference;
    private ValueEventListener dabaoValueEventListener;
    public OrderFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firebaseDatabase=FirebaseDatabase.getInstance();
        dabaoDatabaseReference=firebaseDatabase.getReference().child("dabao");
        final ArrayList<WaitingDabaoer> waitingRequests = new ArrayList<WaitingDabaoer>();
        /*dabaoChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                waitingRequests.clear();
                ArrayList<Map> ordersdabao= (ArrayList<Map>) dataSnapshot.getValue();
                for(Map a:ordersdabao)
                {

                    WaitingDabaoer wd= new WaitingDabaoer();
                    wd.setStatus(a.get("status").toString());
                    wd.setId(a.get("id").toString());
                    wd.setFoodName(a.get("foodName").toString());
                    wd.setCanteenName(a.get("canteenName").toString());
                    wd.setTimestamp(a.get("timestamp").toString());
                    wd.setDeliveryTo(a.get("deliveryTo").toString());
                    waitingRequests.add(wd);
                }
                //Log.e("Key of data",ordersdabao.get(0).toString());
//                for (Map.Entry<String, Object> d: ordersdabao.entrySet())
//                {
//
//                    WaitingDabaoer wd= new WaitingDabaoer();
//                        Map<String,Object> data= (Map<String, Object>) d.getValue();
//                    Log.e("Key of data",data.get("0").toString());
//                        for(Map.Entry<String,Object> index :data.entrySet())
//                        {
//
//                        }
//                }
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
        dabaoDatabaseReference.addChildEventListener(dabaoChildEventListener);*/

        dabaoValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                waitingRequests.clear();
                Map<String, ArrayList<Map>> ordersdabao= (Map<String, ArrayList<Map>>) dataSnapshot.getValue();
                if(ordersdabao!=null){
                Log.e("orderdabao", ordersdabao.keySet().toString());
                for(String s:ordersdabao.keySet())
                {
                    ArrayList<Map> data=ordersdabao.get(s);
                    for(Map a:data)
                    {
                        Log.e("a", a.toString());
                        WaitingDabaoer wd= new WaitingDabaoer();
                        wd.setStatus(a.get("status").toString());
                        wd.setId(a.get("id").toString());
                        wd.setFoodName(a.get("foodName").toString());
                        wd.setCanteenName(a.get("canteenName").toString());
                        wd.setTimestamp(a.get("timestamp").toString());
                        wd.setDeliveryTo(a.get("deliveryTo").toString());
                        waitingRequests.add(wd);
                    }
                    //Log.e("dtaa", data.keySet().toString());
                }

            }else{
                    Toast.makeText(getContext(), "Oops no data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dabaoDatabaseReference.addValueEventListener(dabaoValueEventListener);

//        waitingRequests.add(new WaitingDabaoer("","Pasta", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","12 Sept 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","4 Dec 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","9 March 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs","CAN A", "Italian Pasta", "CAN 11","SEARCHING","18 Feb 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","13 Oct 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","30 Dec 2017"));
//        waitingRequests.add(new WaitingDabaoer("","Fish Bread Crumbs","CAN A", "Italian Pasta", "CAN 11","SEARCHING","1 Sept 2017"));

        WaitingAdapter waitingAdapter = new WaitingAdapter(getActivity(), waitingRequests);

        View rootView = inflater.inflate(R.layout.list_view_from_button, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(waitingAdapter);

        return listView;
    }
}
