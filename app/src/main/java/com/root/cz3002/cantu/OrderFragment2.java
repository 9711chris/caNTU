package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.root.cz3002.cantu.model.ToReceiveData;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment2 extends Fragment{
    public OrderFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<ToReceiveData> toReceiveRequests = new ArrayList<ToReceiveData>();
        toReceiveRequests.add(new ToReceiveData(2.80, "steamed chicken rice", "chicken rice","CAN A",2,5.6,"Self-Collect"));
        toReceiveRequests.add(new ToReceiveData(4.50, "fish bread crumb", "italian","CAN A",1,4.5,"Wait Dabaoer"));
        toReceiveRequests.add(new ToReceiveData(2.80, "roasted chicken rice", "chicken rice","CAN A",1,2.8,"Self-Collect"));

        ToReceiveAdapter toReceiveAdapter = new ToReceiveAdapter(getActivity(),toReceiveRequests);

        View rootView = inflater.inflate(R.layout.list_view_from_button, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(toReceiveAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }
}
