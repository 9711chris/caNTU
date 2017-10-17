package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.root.cz3002.cantu.model.ToReceiveData;

import java.util.ArrayList;

/**
 * Created by brigi on 15/10/2017.
 */

public class ToReceiveAdapter extends ArrayAdapter<ToReceiveData> {
    private static final String LOG_TAG = ToReceiveAdapter.class.getSimpleName();
    // Get the {@link OrderPayData} object located at this position in the list
    ToReceiveData currentToReceiveRequest;

    public ToReceiveAdapter(Activity context, ArrayList<ToReceiveData> currentToReceiveRequest){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, currentToReceiveRequest);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_fragment2, parent, false);
        }

        currentToReceiveRequest = getItem(position);


        // Find the TextView in the order_fragment2.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentToReceiveRequest.getCanteenName());
        tv1.setTextSize(15);

        // Find the TextView in the order_fragment2.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentToReceiveRequest.getStallName());
        tv2.setTextSize(15);

        // Find the TextView in the order_fragment2.xml layout with the ID place_deliver
        TextView tp = (TextView) listItemView.findViewById(R.id.total_price);
        tp.setText(String.valueOf(currentToReceiveRequest.getTotalPrice()));
        tp.setTextSize(15);

        // Find the TextView in the order_fragment2.xml layout with the ID canteen_name
        TextView foodName = (TextView) listItemView.findViewById(R.id.food_name);
        foodName.setText(currentToReceiveRequest.getFoodName());
        foodName.setTextSize(15);

        // Find the TextView in the order_fragment2.xml layout with the ID canteen_name
        TextView status = (TextView) listItemView.findViewById(R.id.status);
        status.setText(currentToReceiveRequest.getStatus());
        status.setTextSize(15);
        status.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Button b1 = (Button) listItemView.findViewById(R.id.order_receive);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: update database -- order status completed
            }
        });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
