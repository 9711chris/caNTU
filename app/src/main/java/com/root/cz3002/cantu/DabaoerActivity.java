package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.DRChildData;
import com.root.cz3002.cantu.model.DabaoRequest;

import java.util.ArrayList;
import java.util.Timer;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoerActivity extends AppCompatActivity {
    private DatabaseReference dabaoDatabaseReference;
    private ChildEventListener dabaoChildEventListener;
    private ValueEventListener dabaoValueEventListener;
    ArrayList<DabaoRequest> dabaoRequests;
    DabaoRequestAdapter dabaoAdapter;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_from_button);
        dabaoDatabaseReference= FirebaseDatabase.getInstance().getReference().child("dabao");
        //m = new ArrayList<DRChildData>();
        dabaoRequests = new ArrayList<DabaoRequest>();
        //ArrayList<DRChildData> m = new ArrayList<DRChildData>();
        //m.add(new DRChildData("Pasta",2));
        //m.add(new DRChildData("Fish bread crumb set",1));

        dabaoAdapter = new DabaoRequestAdapter(this, dabaoRequests);
        dabaoAdapter.setNotifyOnChange(true);

       // dabaoRequests.add(new DabaoRequest(1, "gg", "can1", "yong tau foo", m, "PENDING", "hall10"));
        //dabaoRequests.add(new DabaoRequest(2, "gh", "can2", "yong tau foo", m, "PENDING", "hall12"));

        dabaoValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    DabaoRequest dabaoRequest = new DabaoRequest();
                    boolean found = false;
                    ArrayList<DRChildData> m = new ArrayList<DRChildData>();
                    int count = 0;
                    for(DataSnapshot d1: d.getChildren()){
                        dabaoRequest.setCanteenName(d1.child("canteenName").getValue(String.class));
                        dabaoRequest.setPlaceDeliver(d1.child("deliveryTo").getValue(String.class));
                        if(d1.child("status").getValue(String.class).equals("FOUND")){
                            found = true;
                            break;
                        }
                        else{
                            dabaoRequest.setStatus(d1.child("status").getValue(String.class));
                        }
                        dabaoRequest.setStallName(d1.child("stallName").getValue(String.class));
                        dabaoRequest.setName(d1.child("user").getValue(String.class));
                        dabaoRequest.setId(d1.child("id").getValue(String.class));
                        dabaoRequest.setKey(d.getKey().toString());
                        count++;
                        //dabaoRequest.setO_f_number(d1.getKey().toString());
                        Long qty;
                        if((d1.child("qty").getValue(Long.class)) == null) {
                            qty = new Long(0);
                        }
                        else{
                            qty = (Long) d1.child("qty").getValue(Long.class);
                        }

                        DRChildData children = new DRChildData(d1.child("foodName").getValue(String.class), qty);
                        m.add(children);
                    }
                    if(!found){
                        dabaoRequest.setChildCount(count);
                        dabaoRequest.setFood_qty(m);
                        dabaoRequests.add(dabaoRequest);

                    }
                }





                    if (dabaoRequests.isEmpty()) {
                        Toast.makeText(DabaoerActivity.this, "It's empty here only", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DabaoerActivity.this, "It's not empty here", Toast.LENGTH_SHORT).show();
                    }

                dabaoAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dabaoDatabaseReference.addValueEventListener(dabaoValueEventListener);
//
        if(!dabaoRequests.isEmpty())
        {
            Toast.makeText(this,"Length is "+ dabaoRequests.size(), Toast.LENGTH_SHORT).show();
        }

        Log.e("bub bub", dabaoRequests.toString());
        ListView listView = (ListView) findViewById(R.id.listview_from_button);
        listView.setAdapter(dabaoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Button b1 = (Button) view.findViewById(R.id.acc);
                //dabaoAdapter.notifyDataSetChanged();
                //finish();
                //startActivity(getIntent());
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("===kk");
                        updateStatusInDabaoDB(dabaoAdapter.getItem(pos));
                        //dabaoRequests = new ArrayList<DabaoRequest>();
                        populateAdapter();
                        dabaoAdapter.notifyDataSetChanged();
                        dabaoAdapter.notifyDataSetChanged();
                        dabaoAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }
    private DatabaseReference dabaoDatabaseRef = FirebaseDatabase.getInstance().getReference().child("dabao");
    private void updateStatusInDabaoDB(final DabaoRequest currentDabaoRequest){
        DatabaseReference statusRef = dabaoDatabaseRef.child(currentDabaoRequest.getKey()).getRef();
        int count = currentDabaoRequest.getChildCount();
        for(int i =0; i<count; i++){
            statusRef.child(String.valueOf(i)).child("status").setValue("FOUND");
        }
    }

    private void populateAdapter(){
        dabaoRequests.clear();
        dabaoValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    DabaoRequest dabaoRequest = new DabaoRequest();
                    boolean found = false;
                    ArrayList<DRChildData> m = new ArrayList<DRChildData>();
                    int count = 0;
                    for(DataSnapshot d1: d.getChildren()){
                        System.out.println("==="+d.getChildrenCount());
                        dabaoRequest.setCanteenName(d1.child("canteenName").getValue(String.class));
                        dabaoRequest.setPlaceDeliver(d1.child("deliveryTo").getValue(String.class));
                        System.out.println("==="+d1.child("deliveryTo").getValue(String.class));
                        System.out.println("==="+d1.child("status").getValue(String.class));
                        if(d1.child("status").getValue(String.class).equals("FOUND")){
                            found = true;
                            break;
                        }
                        else{
                            dabaoRequest.setStatus(d1.child("status").getValue(String.class));
                        }
                        dabaoRequest.setStallName(d1.child("stallName").getValue(String.class));
                        dabaoRequest.setName(d1.child("user").getValue(String.class));
                        dabaoRequest.setId(d1.child("id").getValue(String.class));
                        dabaoRequest.setKey(d.getKey().toString());
                        count++;
                        //dabaoRequest.setO_f_number(d1.getKey().toString());
                        Long qty;
                        if((d1.child("qty").getValue(Long.class)) == null) {
                            qty = new Long(0);
                        }
                        else{
                            qty = (Long) d1.child("qty").getValue(Long.class);
                        }

                        DRChildData children = new DRChildData(d1.child("foodName").getValue(String.class), qty);
                        m.add(children);
                    }
                    if(!found){
                        dabaoRequest.setChildCount(count);
                        dabaoRequest.setFood_qty(m);
                        dabaoRequests.add(dabaoRequest);
                        m.clear();

                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dabaoDatabaseRef.addValueEventListener(dabaoValueEventListener);
        dabaoAdapter.notifyDataSetChanged();
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

