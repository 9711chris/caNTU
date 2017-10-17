package com.root.cz3002.cantu;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.cz3002.cantu.model.Order;

/**
 * Created by Christantia on 10/6/2017.
 */

public class OrderListView extends LinearLayout {
    public final static int PLUS_INT = 0;
    public final static int ARROW_INT = 1;
    public OrderListView(Context context, Order order) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(new LayoutParams(layoutParams));
        layoutParams = new LayoutParams(600, 150);
        layoutParams.setMargins(40, 35, 5, 40);

        LinearLayout temp = new LinearLayout(context);
        temp.setOrientation(LinearLayout.VERTICAL);
        temp.setLayoutParams(layoutParams);
        TextView tv = new TextView(context);
        tv.setText(order.getMenuName());
        tv.setTextSize(20);
        TextView tv2 = new TextView(context);
        tv2.setText("Qty.: " + order.getQuantity());
        tv2.setTextSize(18);
        temp.addView(tv);
        temp.addView(tv2);
        addView(temp);
        layoutParams = new LinearLayout.LayoutParams(200,90);
        layoutParams.setMargins(90, 60, 0, 0);

        TextView tv3 = new TextView(context);
        tv3.setText( "$" + String.format("%.2f", order.getTotalPrice()));
        tv3.setTextSize(20);
        tv3.setLayoutParams(layoutParams);
        addView(tv3);

        /*ImageView plus = new ImageView(context);
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
