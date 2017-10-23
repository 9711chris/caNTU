package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.root.cz3002.cantu.model.DRChildData;
import com.root.cz3002.cantu.model.DabaoRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoRequestAdapter extends ArrayAdapter<DabaoRequest> {
    private static final String LOG_TAG = DabaoRequestAdapter.class.getSimpleName();
    private DRExpandableListViewAdapter listAdapter;
    private ExpandableListView expListView;
    private String listDataHeader;
    private List<DRChildData> listDataChild;

    public DabaoRequestAdapter(Activity context, ArrayList<DabaoRequest> dabaoRequests){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, dabaoRequests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dabao_request_item, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        DabaoRequest currentDabaoRequest = getItem(position);

        // Find the TextView in the dabao_request_item.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentDabaoRequest.getCanteenName());

        // Find the TextView in the dabao_request_item.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentDabaoRequest.getStallName());

        // Find the TextView in the dabao_request_item.xml layout with the ID place_deliver
        TextView tv3 = (TextView) listItemView.findViewById(R.id.place_deliver);
        tv3.setText(currentDabaoRequest.getPlaceDeliver());

        // get the listview
        expListView = (ExpandableListView) listItemView.findViewById(R.id.list_food);

        // preparing list data
        prepareListData(currentDabaoRequest);

        listAdapter = new DRExpandableListViewAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        Button b1 = (Button) listItemView.findViewById(R.id.acc);
        b1.setText("Accept Order");
        //b1.setOnClickListener(); implement to send data to database.

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private void prepareListData(DabaoRequest dabaoRequest) {
        listDataHeader = "List of food";
        listDataChild = dabaoRequest.getFood_qty();
    }
}
