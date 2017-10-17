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
        waitingRequests.add(new WaitingDabaoer(1, "gg", "can1", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(2, "gh", "can2", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(4, "gf", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(5, "gr", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(6, "gt", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(7, "gy", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(8, "gu", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(9, "gi", "can3", "yong tau foo", "SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(10, "go", "can3", "yong tau foo","SEARCHING"));
        waitingRequests.add(new WaitingDabaoer(11,"gp", "can3", "yong tau foo","SEARCHING"));

        WaitingAdapter waitingAdapter = new WaitingAdapter(getActivity(), waitingRequests);

        View rootView = inflater.inflate(R.layout.list_view_from_button, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(waitingAdapter);
        return listView;
    }
}
