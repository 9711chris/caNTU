package com.root.cz3002.cantu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.root.cz3002.cantu.model.OrderPayData;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment1 extends Fragment {
    ToPayAdapter toPayAdapter;

    public OrderFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<OrderPayData> orderPayRequests = new ArrayList<OrderPayData>();
        orderPayRequests.add(new OrderPayData(true, 2.80, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 4.50, "fish bread crumb", "italian","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 2.80, "roasted chicken rice", "chicken rice","CAN A",1));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));


        toPayAdapter = new ToPayAdapter(getActivity(), orderPayRequests);

        View rootView = inflater.inflate(R.layout.order_fragment1, container, false);

        CheckBox selectAll = (CheckBox) rootView.findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<toPayAdapter.getCount();i++){
                    if(!toPayAdapter.getItem(i).getIsChecked()){
                        toPayAdapter.getItem(i).setIsChecked(true);
                    }
                }
                toPayAdapter.notifyDataSetChanged();
            }
        });

        CheckBox deleteSelected = (CheckBox) rootView.findViewById(R.id.delete_selected);
        deleteSelected.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                for(int i=0;i<toPayAdapter.getCount();i++){
                    if(toPayAdapter.getItem(i).getIsChecked()){
                        toPayAdapter.getItem(i).setIsChecked(false);
                    }
                }
                toPayAdapter.notifyDataSetChanged();
            }
        });

        Button pay = (Button) rootView.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Payment Confirmation")
                        .setMessage("Do you really want to buy all this order?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //TODO:send order to database
                                Toast.makeText(getContext(), "Payment Confirmation Successfull", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        Button payDabao = (Button) rootView.findViewById(R.id.pay_dabao);
        payDabao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListPayDabaoActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(toPayAdapter);

        return rootView;
    }
}
