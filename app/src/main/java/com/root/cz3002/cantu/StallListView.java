package com.root.cz3002.cantu;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.cz3002.cantu.model.Stall;

/**
 * Created by Christantia on 10/6/2017.
 */

public class StallListView extends LinearLayout {
    public final static int PLUS_INT = 0;
    public final static int ARROW_INT = 1;
    public StallListView(Context context, Stall info) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
        layoutParams = new LinearLayout.LayoutParams(600, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(40, 35, 30, 35);

        LinearLayout temp = new LinearLayout(context);
        temp.setOrientation(LinearLayout.VERTICAL);
        temp.setLayoutParams(layoutParams);
        TextView tv = new TextView(context);
        tv.setText(info.getName());
        tv.setTextSize(20);
        TextView tv2 = new TextView(context);
        tv2.setText(info.getOpeningHour());
        temp.addView(tv);
        temp.addView(tv2);
        addView(temp);
        /*layoutParams = new LinearLayout.LayoutParams(90,90);
        layoutParams.setMargins(30, 65, 30, 65);

        ImageView plus = new ImageView(context);
        plus.setId(PLUS_INT);
        plus.setImageResource(R.drawable.plus);
        plus.setLayoutParams(layoutParams);
        addView(plus);
        ImageView arrow = new ImageView(context);
        arrow.setId(ARROW_INT);
        arrow.setImageResource(R.drawable.navigation);
        arrow.setLayoutParams(layoutParams);
        addView(arrow);*/
    }
}
