package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoActivity extends AppCompatActivity { //user order dabao list
    OrderListPayDabaoAdapter orderListPayDabaoAdapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_from_button);

        //TODO: get data from database-- the order for pay with dabao
        // put it in the ArrayList format.. the data class is in OrderListPayDabaoData.java
        ArrayList<OrderListPayDabaoData> orderDabaoRequests = new ArrayList<OrderListPayDabaoData>();
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",2,4.5,9));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A",1,4.5,4.5));

        orderListPayDabaoAdapter = new OrderListPayDabaoAdapter(this, orderDabaoRequests);

        ListView listView = (ListView) findViewById(R.id.listview_from_button);
        listView.setAdapter(orderListPayDabaoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                Button b1 = (Button) view.findViewById(R.id.order);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(orderListPayDabaoAdapter.getItem(pos).getDeliveryTo()==null ||
                                orderListPayDabaoAdapter.getItem(pos).getDeliveryTo()=="" ||
                                orderListPayDabaoAdapter.getItem(pos).getDeliveryTo()==" "){
                            Toast.makeText(getApplicationContext(), "You have to enter the delivery place", Toast.LENGTH_SHORT).show();
                            orderListPayDabaoAdapter.notifyDataSetChanged();
                        }
                        else{
                            //TODO:send order to database
                            Toast.makeText(getApplicationContext(), "This order has been send", Toast.LENGTH_SHORT).show();
                            orderListPayDabaoAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }
}
