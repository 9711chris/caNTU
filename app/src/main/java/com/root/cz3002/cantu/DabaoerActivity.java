package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.root.cz3002.cantu.model.DabaoRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_from_button);

        Map<String,Integer> m =  new HashMap<String,Integer>();
        m.put("fish soup", 1);
        m.put("wanton noodle",1);

        ArrayList<DabaoRequest> dabaoRequests = new ArrayList<DabaoRequest>();
        dabaoRequests.add(new DabaoRequest(1, "gg", "can1", "yong tau foo", m, "PENDING", "hall10"));
        dabaoRequests.add(new DabaoRequest(2, "gh", "can2", "yong tau foo", m, "PENDING", "hall12"));
        dabaoRequests.add(new DabaoRequest(4, "gf", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(5, "gr", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(6, "gt", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(7, "gy", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(8, "gu", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(9, "gi", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(10, "go", "can3", "yong tau foo", m, "PENDING", "hall11"));
        dabaoRequests.add(new DabaoRequest(11,"gp", "can3", "yong tau foo", m, "PENDING", "hall11"));

        DabaoRequestAdapter dabaoAdapter = new DabaoRequestAdapter(this, dabaoRequests);

        ListView listView = (ListView) findViewById(R.id.listview_from_button);
        listView.setAdapter(dabaoAdapter);

    }

}
