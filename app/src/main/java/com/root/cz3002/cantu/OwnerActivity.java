package com.root.cz3002.cantu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.Order;
import com.root.cz3002.cantu.model.SOChildData;
import com.root.cz3002.cantu.model.SOHeaderData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OwnerActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ValueEventListener eventListener;

    private String id;
    //private String mode;
    private LinearLayout list;
    //private ArrayList<Stall> groupOrders = new ArrayList<Stall>(); //to fill with content of list for stalls in canteen/cuisine choice
    SOExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<SOHeaderData> listDataHeader;
    HashMap<String, List<SOChildData>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("orders").child("Mini Wok");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();
        Intent intent = getIntent();
        /*if (intent.hasExtra("MODE")) {
            mode = bundleOld.getString("MODE");
        }*/
        if (intent.hasExtra("ID")) {
            id = bundleOld.getString("ID");
        }

        final Context context = this;
        final Bundle bundle = new Bundle();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.e_list);

        // preparing list data
        prepareListData();

        listAdapter = new SOExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

       /* FloatingActionButton switchMode = (FloatingActionButton) findViewById(R.id.switchMode);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        list = (LinearLayout) inflater.inflate(R.layout.list_view, (ViewGroup) findViewById(R.id.list));

        populateOrder(null);*/

    }

    private void prepareListData() {

        listDataHeader = new ArrayList<SOHeaderData>();
        listDataChild = new HashMap<String, List<SOChildData>>();

        //================HOW TO ADD THE DATA==============
        // Adding header data
//        SOHeaderData order1 = new SOHeaderData("Order 1", 5.5);
//        listDataHeader.add(order1);
//        List<SOChildData> dishes1 = new ArrayList<SOChildData>();
//        // Adding child data
//        SOChildData dish1 = new SOChildData("Chicken Rice", 2);
//        dishes1.add(dish1);
//        dishes1.add(dish1);
//        listDataChild.put(listDataHeader.get(0).getOrderSeq(), dishes1); // Header, Child data
//
//        // Adding header data
//        SOHeaderData order2 = new SOHeaderData("Order 2", 4.5);
//        listDataHeader.add(order2);
//        List<SOChildData> dishes2 = new ArrayList<SOChildData>();
//        // Adding child data
//        SOChildData dish2 = new SOChildData("Chicken Rice2", 3);
//        dishes2.add(dish2);
//        dishes2.add(dish2);
//        listDataChild.put(listDataHeader.get(1).getOrderSeq(), dishes2); // Header, Child data


        //===================================================

        //TODO: FETCH FROM DATABASE
        eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listDataHeader.clear();
                listDataChild.clear();
                double sum;
                int count=0;

                Map<String, Object> dataSnapshotValue= (Map<String, Object>) dataSnapshot.getValue();
                if (dataSnapshotValue != null) {
                    for (Map.Entry<String, Object> a : dataSnapshotValue.entrySet()) {
                        sum = 0;
                        String key = "";
                        ArrayList<SOChildData> childList = new ArrayList<SOChildData>();
                        ArrayList<Map<String, Object>> d = (ArrayList) a.getValue();
                        for (Map<String, Object> data : d) {
                            double price = (double) data.get("price");
                            String foodName = data.get("foodName").toString();
                            long quantity = (long) data.get("qty");
                            Log.e("Values", foodName + price + "  " + quantity);
                            key = (String) data.get("id");
                            sum = sum + (price * quantity);
                            SOChildData child = new SOChildData(foodName, quantity);
                            childList.add(child);
                        }
                        Log.e("Price", String.valueOf(sum));
                        SOHeaderData header = new SOHeaderData("order " + (++count), sum, key);
                        Log.e("ID", count + " " + key);
                        listDataHeader.add(header);
                        //listDataChild.put(header)
                        listDataChild.put(listDataHeader.get(count - 1).getOrderSeq(), childList);

                    }
                }

                    listAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);

        //====================HOW TO ADD THE DATA WITH LOOP, FOR DATABASE==============
        /*for (int i = 0; i < numberOfOrders; i++){
            SOHeaderData order = new SOHeaderData("Order " + orderIDNum, totalprice);
            listDataHeader.add(order);
            List<SOChildData> dishesInOrder = new ArrayList<SOChildData>();
            for (int j = 0; j < numberOfDishesInTheOrder; j++) {
                SOChildData dish = new SOChildData(dishName, dishQty);
                dishesInOrder.add(dish);
            }
            listDataChild.put(listDataHeader.get(i).getOrderSeq(), dishesInOrder);
        }*/



    }

    public void refresh(View v){
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void populateOrder(View v) {
        list.removeAllViews();

        //dummy, example for populating list
        Order temp = new Order(1,"Sambal Fried Rice",1,3.5,3.5);

        //populate groupOrders arraylist
        //using Database's method
        //Order temp;
       /* while (true) {
            temp = new Order(take data from db)
            addNewItemInList(list, temp);
            if no orders left in db
                break;
        } ==OR==
        for (Order cur: groupOrders)
            addNewItemInList(list, temp);*/
       if (v != null)
           Toast.makeText(OwnerActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
    }

}
