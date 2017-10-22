package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.DRChildData;
import com.root.cz3002.cantu.model.DabaoRequest;
import com.root.cz3002.cantu.model.WaitingDabaoer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoerActivity extends AppCompatActivity {
    private DatabaseReference dabaoDatabaseReference;
    private ChildEventListener dabaoChildEventListener;
    private ValueEventListener dabaoValueEventListener;
    private ArrayList<DRChildData> m;
    ArrayList<DabaoRequest> dabaoRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_from_button);
        dabaoDatabaseReference= FirebaseDatabase.getInstance().getReference().child("dabao");
        m = new ArrayList<DRChildData>();
        dabaoRequests = new ArrayList<DabaoRequest>();

        dabaoValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                dabaoRequests.clear();
                Map<String, Object> dataSnapshot1= (Map<String, Object>) dataSnapshot.getValue();
                for(Map.Entry<String, Object> data: dataSnapshot1.entrySet())
                {
                    DabaoRequest dabaoRequest=new DabaoRequest();
                    m.clear();
                    ArrayList<Map<String, Object>> d=(ArrayList<Map<String, Object>>)data.getValue();

                    //Log.e("dt values",d.toString());
                    for(Map<String, Object> dt:d)
                    {

                        dabaoRequest.setCanteenName(dt.get("canteenName").toString());
                        dabaoRequest.setPlaceDeliver(dt.get("deliveryTo").toString());
                        dabaoRequest.setStatus(dt.get("status").toString());
                        dabaoRequest.setName(dt.get("user").toString());
                        DRChildData child=new DRChildData(dt.get("foodName").toString(),(Long)dt.get("qty"));
                        m.add(child);
                        Log.e("Value m1", m.get(0).getFoodName().toString());

                    }

                    Log.e("dabaoReuest value", dabaoRequest.getCanteenName()+ dabaoRequest.getName());
                    dabaoRequest.setFood_qty(m);
                    Log.e("Value m", m.get(0).toString());
                    dabaoRequests.add(dabaoRequest);
                    Log.e("Inside daboRequests", dabaoRequests.get(0).getCanteenName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dabaoDatabaseReference.addValueEventListener(dabaoValueEventListener);


//

        DabaoRequestAdapter dabaoAdapter = new DabaoRequestAdapter(this, dabaoRequests);
        Log.e("bub bub", dabaoRequests.toString());
        ListView listView = (ListView) findViewById(R.id.listview_from_button);
        listView.setAdapter(dabaoAdapter);



    }

}


//        dabaoChildEventListener= new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                ArrayList<Map<String, Object>> dataSnatshpt1= (ArrayList<Map<String, Object>>) dataSnapshot.getValue();
//                for(Map<String,Object> data:dataSnatshpt1)
//                {
//                    Log.e("DataSnapshot key", data.keySet().toString());
//                    //Log.e("ddatasnapshtos key", dataSnapshot1.getKey().toString());
//                }
////                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
////                {
////
////                    Log.e("DataSnapshot key", dataSnapshot.getKey().toString());
////                    Log.e("ddatasnapshtos key", dataSnapshot1.getKey().toString());
////                    Log.e("ddatasnapshtos value", dataSnapshot1.getValue().toString());
////
////                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        dabaoDatabaseReference.addChildEventListener(dabaoChildEventListener);

   // ArrayList<DRChildData> m = new ArrayList<DRChildData>();
//        m.add(new DRChildData("Pasta",2));
//        m.add(new DRChildData("Fish bread crumb set",1));
//
//
//        dabaoRequests.add(new DabaoRequest(1, "gg", "can1", "yong tau foo", m, "PENDING", "hall10"));
//        dabaoRequests.add(new DabaoRequest(2, "gh", "can2", "yong tau foo", m, "PENDING", "hall12"));
//        dabaoRequests.add(new DabaoRequest(4, "gf", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(5, "gr", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(6, "gt", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(7, "gy", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(8, "gu", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(9, "gi", "can3", "yong tapu foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(10, "go", "can3", "yong tau foo", m, "PENDING", "hall11"));
//        dabaoRequests.add(new DabaoRequest(11,"gp", "can3", "yong tau foo", m, "PENDING", "hall11"));

