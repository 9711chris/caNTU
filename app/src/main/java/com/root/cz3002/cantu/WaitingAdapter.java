package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.WaitingDabaoer;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class WaitingAdapter extends ArrayAdapter<WaitingDabaoer> {
    private static final String LOG_TAG = WaitingAdapter.class.getSimpleName();
    //private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference dabaoDatabaseReference;

    ArrayList<WaitingDabaoer> waitingList;

    public WaitingAdapter(Activity context, ArrayList<WaitingDabaoer> waitingRequests){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, waitingRequests);
        this.waitingList = waitingRequests;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_fragment3, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final WaitingDabaoer currentWaitingRequest = getItem(position);

        // Find the TextView in the order_fragment3.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentWaitingRequest.getCanteenName());

        // Find the TextView in the order_fragment3.xml layout with the ID canteen_name
        TextView foodName = (TextView) listItemView.findViewById(R.id.food_name);
        foodName.setText(currentWaitingRequest.getFoodName());

        // Find the TextView in the order_fragment3.xml layout with the ID canteen_name
        TextView status = (TextView) listItemView.findViewById(R.id.status);
        status.setText(currentWaitingRequest.getStatus());

        // Find the TextView in the order_fragment3.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentWaitingRequest.getStallName());

        // Find the TextView in the order_fragment3.xml layout with the ID place_deliver
        TextView tv3 = (TextView) listItemView.findViewById(R.id.delivery_to);
        tv3.setText(currentWaitingRequest.getDeliveryTo());

        // Find the TextView in the order_fragment3.xml layout with the ID place_deliver
        TextView timestamp = (TextView) listItemView.findViewById(R.id.timestamp);
        timestamp.setText(currentWaitingRequest.getTimestamp());

        Button b1 = (Button) listItemView.findViewById(R.id.cancel);
        //TODO: b1 setOnClickListener to update database data -- delete the particular tuples.
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("go in here");
                dabaoDatabaseReference=FirebaseDatabase.getInstance().getReference().child("dabao").child(currentWaitingRequest.getId());
                Query foodQuery = dabaoDatabaseReference.orderByChild("foodName").equalTo(currentWaitingRequest.getFoodName());

                foodQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot food:dataSnapshot.getChildren())
                        {
                            food.getRef().setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                waitingList.remove(position);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }
}
