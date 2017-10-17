package com.root.cz3002.cantu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.root.cz3002.cantu.model.Order;

public class OwnerActivity extends AppCompatActivity {

    private String id;
    //private String mode;
    private LinearLayout list;
    //private ArrayList<Stall> groupOrders = new ArrayList<Stall>(); //to fill with content of list for stalls in canteen/cuisine choice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();
        Intent intent = getIntent();
        /*if (intent.hasExtra("MODE")) {
            mode = bundleOld.getString("MODE");
        }*/
        if (intent.hasExtra("ID")) {
            id = bundleOld.getString("ID");
        }

        final Context context = this;
        final Bundle bundle = new Bundle();

        FloatingActionButton switchMode = (FloatingActionButton) findViewById(R.id.switchMode);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        list = (LinearLayout) inflater.inflate(R.layout.list_view, (ViewGroup) findViewById(R.id.list));

        populateOrder(null);

    }
    private boolean addNewItemInList(final LinearLayout list, final Order cur) {
        final LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.HORIZONTAL);

        OrderListView view = new OrderListView(this, cur);
        view.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
        view.setBackgroundResource(R.drawable.background_border_red);

        a.addView(view);
        a.setTag(cur);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(OwnerActivity.this);
                builder1.setMessage("Are you sure you want to resolve and delete this order?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                list.removeView(a);
                                //database method to remove order in DB
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();;
            }
        });

        list.addView(a);
        return false;
    }

    public void populateOrder(View v) {
        list.removeAllViews();

        //dummy, example for populating list
        Order temp = new Order(1,"Sambal Fried Rice",1,3.5,3.5);
        addNewItemInList(list, temp);
        addNewItemInList(list, temp);
        addNewItemInList(list, temp);
        addNewItemInList(list, temp);
        addNewItemInList(list, temp);

        //populate groupOrders arraylist
        //using Database's method
        //Order temp;
       /* while (true) {
            temp = new Order(take data from db)
            addNewItemInList(list, temp);
            if no orders left in db
                break;
        } ==OR==
        for (Order cur: groupOrders)
            addNewItemInList(list, temp);*/
       if (v != null)
           Toast.makeText(OwnerActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
    }



}
