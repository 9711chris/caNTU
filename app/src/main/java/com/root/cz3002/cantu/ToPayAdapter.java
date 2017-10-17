package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.root.cz3002.cantu.model.OrderPayData;

import java.util.ArrayList;

/**
 * Created by brigi on 14/10/2017.
 */

public class ToPayAdapter extends ArrayAdapter<OrderPayData> {
    private static final String LOG_TAG = ToPayAdapter.class.getSimpleName();
    // Get the {@link OrderPayData} object located at this position in the list
    OrderPayData currentOrderPayRequest;

    public ToPayAdapter(Activity context, ArrayList<OrderPayData> orderPayRequests){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, orderPayRequests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_pay_data_item, parent, false);
        }

        currentOrderPayRequest = getItem(position);

        CheckBox ch = (CheckBox) listItemView.findViewById(R.id.food_name);
        ch.setText(currentOrderPayRequest.getFoodName());
        ch.setTextSize(20);
        ch.setChecked(currentOrderPayRequest.getIsChecked());
        /*ch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if(isChecked == true) {
                    ((CheckBox) v).setChecked(false);
                    currentOrderPayRequest.setIsChecked(false);
                    notifyDataSetChanged();
                } else{
                    ((CheckBox) v).setChecked(true);
                    currentOrderPayRequest.setIsChecked(true);
                    notifyDataSetChanged();
                }
            }
        });
        */



        TextView qty = (TextView) listItemView.findViewById(R.id.qty);
        qty.setText(String.valueOf(currentOrderPayRequest.getQty()));
        qty.setTextSize(20);

        // Find the TextView in the dabao_request_item.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentOrderPayRequest.getCanteenName());
        tv1.setTextSize(20);

        // Find the TextView in the dabao_request_item.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentOrderPayRequest.getStallName());
        tv2.setTextSize(20);

        TextView price = (TextView) listItemView.findViewById(R.id.price);
        price.setText(String.valueOf(currentOrderPayRequest.getPrice()));
        price.setTextSize(20);

        // Find the TextView in the dabao_request_item.xml layout with the ID place_deliver
        TextView tp = (TextView) listItemView.findViewById(R.id.total_price);
        tp.setText(String.valueOf(currentOrderPayRequest.getTotalPrice()));
        tp.setTextSize(20);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    public void sendSelectedOrderToDB(){
        //TODO: iterate through the list
        // check if isChecked is true then send the data to DB
    }

}
