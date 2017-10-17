package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.duration;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoAdapter extends ArrayAdapter<OrderListPayDabaoData> {
    private static final String LOG_TAG = OrderListPayDabaoAdapter.class.getSimpleName();

    OrderListPayDabaoData currentOrderDabaoRequest;

    public OrderListPayDabaoAdapter(Activity context, ArrayList<OrderListPayDabaoData> orderDabaoRequests){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, orderDabaoRequests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_list_pay_data_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        currentOrderDabaoRequest = getItem(position);

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID food_name
        TextView tv0 = (TextView) listItemView.findViewById(R.id.food_name);
        tv0.setText(currentOrderDabaoRequest.getFoodName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentOrderDabaoRequest.getCanteenName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentOrderDabaoRequest.getStallName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID total_price_text
        TextView tv3 = (TextView) listItemView.findViewById(R.id.total_price_text);
        tv3.setText("Total Price: ");

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID total_price
        TextView totalPrice = (TextView) listItemView.findViewById(R.id.total_price);
        totalPrice.setText(String.valueOf(currentOrderDabaoRequest.getTotalPrice()));

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID price
        //TextView price = (TextView) listItemView.findViewById(R.id.price);
        //price.setText(String.valueOf(currentOrderDabaoRequest.getPrice()));

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID qty
        //TextView qty = (TextView) listItemView.findViewById(R.id.quantity);
        //qty.setText(String.valueOf(currentOrderDabaoRequest.getQty()));

        EditText deliveryTo = (EditText) listItemView.findViewById((R.id.delivery_to));
        String del_string = deliveryTo.getText().toString();
        if(!(del_string == null || del_string == "")){
            currentOrderDabaoRequest.setDeliveryTo(del_string);
        }

       Button order = (Button) listItemView.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: checker still not functional
                //Check if delivery to is inputted or not
                if(!(currentOrderDabaoRequest.getDeliveryTo() == null || currentOrderDabaoRequest.getDeliveryTo() =="")){
                    //TODO:send order to database
                    Toast.makeText(getContext(), "This order has been send", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getContext(), "You have to enter the delivery place", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageView delete = (ImageView) listItemView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:delete from database and refresh page.
                Toast.makeText(getContext(), "This order has been deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
