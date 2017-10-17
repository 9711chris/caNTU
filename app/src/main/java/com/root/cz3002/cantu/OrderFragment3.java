package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.root.cz3002.cantu.model.WaitingDabaoer;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment3 extends Fragment {
    public OrderFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<WaitingDabaoer> waitingRequests = new ArrayList<WaitingDabaoer>();
        waitingRequests.add(new WaitingDabaoer(1,"Pasta", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","12 Sept 2017"));
        waitingRequests.add(new WaitingDabaoer(2,"Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","4 Dec 2017"));
        waitingRequests.add(new WaitingDabaoer(4,"Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","9 March 2017"));
        waitingRequests.add(new WaitingDabaoer(5,"Fish Bread Crumbs","CAN A", "Italian Pasta", "CAN 11","SEARCHING","18 Feb 2017"));
        waitingRequests.add(new WaitingDabaoer(6,"Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","13 Oct 2017"));
        waitingRequests.add(new WaitingDabaoer(7,"Fish Bread Crumbs", "CAN A", "Italian Pasta", "CAN 11","SEARCHING","30 Dec 2017"));
        waitingRequests.add(new WaitingDabaoer(8,"Fish Bread Crumbs","CAN A", "Italian Pasta", "CAN 11","SEARCHING","1 Sept 2017"));

        WaitingAdapter waitingAdapter = new WaitingAdapter(getActivity(), waitingRequests);

        View rootView = inflater.inflate(R.layout.list_view_from_button, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(waitingAdapter);

        return listView;
    }
}
