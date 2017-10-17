package com.root.cz3002.cantu;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.cz3002.cantu.model.MenuItem;

import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by shelinalusandro on 8/10/17.
 */

public class MenuListView extends LinearLayout{

    public final static int MENU_INT = 0;
    public final static int QUANTITY_INT = 1;
    public final static int PRICE_INT = 2;
    public final static int ORDER_INT = 3;

    public MenuListView(Context context, MenuItem menu, String mode) {

        super(context);
        setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(new LinearLayout.LayoutParams(layoutParams));

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 5f);
        layoutParams.setMargins(20, 35, 20, 0);

        LinearLayout.LayoutParams itemLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, 3f);

        LinearLayout temp = new LinearLayout(context);
        temp.setOrientation(LinearLayout.HORIZONTAL);
        temp.setLayoutParams(layoutParams);

        TextView tv = new TextView(context);
        tv.setId(MENU_INT);
        tv.setText(menu.getName());
        tv.setTextSize(20);
        tv.setLayoutParams(itemLayout);

        TextView tv2 = new TextView(context);
        tv2.setId(PRICE_INT);
        tv2.setText("$ " + String.format("%.2f", menu.getPrice()));
        tv2.setTextSize(20);
        itemLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        tv2.setLayoutParams(itemLayout);

        //itemLayout.setMargins(0,0,80,0);
        ImageView order = new ImageView(context);
        order.setId(ORDER_INT);
        order.setImageResource(R.drawable.add_order);
        itemLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        order.setLayoutParams(itemLayout);

        System.out.println("mode="+mode);

        temp.addView(tv);
        //addView(qView);
        temp.addView(tv2);
        temp.addView(order);
        addView(temp);

        LinearLayout temp2 = new LinearLayout(context);
        temp.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.setMargins(40, 0, 30, 35);

        temp.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams itemLayout2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayout2.setMargins(150, 0, 0, 35);

        QuantityView qView = new QuantityView(context);
        qView.setId(QUANTITY_INT);
        if(mode=="canteen"){
            qView.setAddButtonTextColor(R.color.holoYellow);
        }else if (mode=="cuisine"){
            qView.setAddButtonTextColor(R.color.holoPurple);
        }
        qView.setLayoutParams(itemLayout2);

        temp2.addView(qView);
        addView(temp2);
    }
}
