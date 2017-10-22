package com.root.cz3002.cantu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.root.cz3002.cantu.model.OrderPayData;
import com.root.cz3002.cantu.model.WaitingDabaoer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment1 extends Fragment {
    ToPayAdapter toPayAdapter;
    double totalPriceAll;
    boolean checkpoint;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference orderDatabaseReference;
    private DatabaseReference dabaoDatabaseReference;

    private ChildEventListener dabaoChildEventListener;
    public static ArrayList<String> keys=new ArrayList<String>();
    public static ArrayList<String> dabaokeys=new ArrayList<String>();
//    private ValueEventListener orderValueEventListener;

    public OrderFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase=FirebaseDatabase.getInstance();

        orderDatabaseReference=firebaseDatabase.getReference().child("orders");
        dabaoDatabaseReference=firebaseDatabase.getReference().child("dabao");

        final ArrayList<OrderPayData> orderPayRequests = MainActivity.orderPayRequests;
        final ArrayList<WaitingDabaoer> orderDabaoRequest = new ArrayList<WaitingDabaoer>();


        //orderPayRequests is an ArrayList<OrderPayData> that will get the data from database
        toPayAdapter = new ToPayAdapter(getActivity(), orderPayRequests);

        View rootView = inflater.inflate(R.layout.order_fragment1, container, false);

        final CheckBox selectAll = (CheckBox) rootView.findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAll.isChecked()) {
                    for (int i = 0; i < toPayAdapter.getCount(); i++) {
                        if (!toPayAdapter.getItem(i).getIsChecked()) {
                            toPayAdapter.getItem(i).setIsChecked(true);
                        }
                    }
                    toPayAdapter.notifyDataSetChanged();
                }
                else{
                    for (int i = 0; i < toPayAdapter.getCount(); i++) {
                        if (toPayAdapter.getItem(i).getIsChecked()) {
                            toPayAdapter.getItem(i).setIsChecked(false);
                        }
                    }
                    toPayAdapter.notifyDataSetChanged();
                }
            }
        });


        ImageView delete = (ImageView) rootView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpoint = false;
                for(int i=0; i<toPayAdapter.getCount(); i++){
                    if(toPayAdapter.getItem(i).getIsChecked()){
                        //TODO: delete from database
                        toPayAdapter.remove(orderPayRequests.get(i));
                        checkpoint = true;
                        i--;
                    }
                }
                toPayAdapter.notifyDataSetChanged();
                if(checkpoint) {
                    Toast.makeText(getContext(), "The order has been deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "There is nothing to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button pay = (Button) rootView.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPriceAll = 0;
                for(int i =0; i<toPayAdapter.getCount(); i++){
                    if(toPayAdapter.getItem(i).getIsChecked()) {
                        totalPriceAll += toPayAdapter.getItem(i).getTotalPrice();
                    }
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("Payment Confirmation")

                        .setMessage("Do you really want to buy all this order?\nTotal: "+totalPriceAll)

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                               String key= orderDatabaseReference.child(orderPayRequests.get(0).getStallName().toString()).push().getKey();
                                for(OrderPayData a:orderPayRequests)
                                {
                                    a.setId(key);
                                }

                                orderDatabaseReference.child(orderPayRequests.get(0).getStallName().toString()).child(key).setValue(orderPayRequests);
                                Toast.makeText(getContext(), "Payment Confirmation Successfull", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        Button payDabao = (Button) rootView.findViewById(R.id.pay_dabao);
        payDabao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Where do you want it to be delivered?");
                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("SUBMIT",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String placeDeliver = input.getText().toString();
                                if(!placeDeliver.isEmpty()){
                                    for(int i=0; i<toPayAdapter.getCount();i++){
                                        if (toPayAdapter.getItem(i).getIsChecked()) {
                                            toPayAdapter.getItem(i).setDeliverTo(placeDeliver);
                                        }
                                    }
                                    //update database send order to waiting tab
                                    String key=dabaoDatabaseReference.push().getKey();
                                    for(int j=0; j<toPayAdapter.getCount();j++){
                                        if(toPayAdapter.getItem(j).getIsChecked()){
                                            //TimeZone.getAvailableIDs()
                                            DateFormat D=new SimpleDateFormat("dd/MM/yy hh:mm:ss");
                                            D.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
                                            Date date = new Date();
                                            WaitingDabaoer wd=new WaitingDabaoer();
                                            wd.setCanteenName(toPayAdapter.getItem(j).getCanteenName());
                                            wd.setFoodName(toPayAdapter.getItem(j).getFoodName());
                                            wd.setStallName(toPayAdapter.getItem(j).getStallName());
                                            wd.setUser(MainActivity.id);
                                            wd.setDeliveryTo(placeDeliver);
                                            wd.setQty(toPayAdapter.getItem(j).getQty()); //Added quantity. Changed to int!
                                            wd.setStatus("SEARCHING");
                                            wd.setTimestamp(D.format(date));
                                            orderDabaoRequest.add(wd);
                                            wd.setId(key);
                                            dabaokeys.add(key);
                                            dabaoDatabaseReference.child(key).setValue(orderDabaoRequest);
                                            toPayAdapter.remove(orderPayRequests.get(j));
                                            j--;
                                        }
                                    }

//                                    dabaoDatabaseReference.push().setValue(new WaitingDabaoer());

                                    toPayAdapter.notifyDataSetChanged();
                                    Toast.makeText(getContext(), "The dabao order has been saved", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getContext(), "You have to add the delivery place", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                alertDialog.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(toPayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(toPayAdapter.getItem(position).getIsChecked()){
                    toPayAdapter.getItem(position).setIsChecked(false);
                }
                else{
                    toPayAdapter.getItem(position).setIsChecked(true);
                }
                toPayAdapter.notifyDataSetChanged();

            }
        });


        return rootView;
    }
}
