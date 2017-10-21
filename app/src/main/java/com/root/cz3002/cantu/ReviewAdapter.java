package com.root.cz3002.cantu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.root.cz3002.cantu.model.Review;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shelinalusandro on 11/10/17.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {

    Context context;
    int itemTemplateLayout; //specify each item layout
    int destinationId;      //specify the id
    ArrayList<Review> data = null;
    String mode; //cuisine or canteen
    ReviewItem holder;
    final int INVALID_ID = -1;
    HashMap<Review, Integer> mIdMap = new HashMap<Review, Integer>();

    public ReviewAdapter(Context context, int itemTemplateLayout, int destinationId,
                         ArrayList<Review> data, String mode) {

        super(context, itemTemplateLayout, destinationId, data);
        this.itemTemplateLayout = itemTemplateLayout;
        this.destinationId = destinationId;
        this.context = context;
        this.data = data;
        this.mode = mode;
        for (int i = 0; i < data.size(); ++i) {
            mIdMap.put(data.get(i), i);
        }
    }

    public void removemIdMap(Review item)
    {
        mIdMap.remove(item);
    }

    public int getCount() {
        return data.size();
    }

    @Override
    public Review getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        Review item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(itemTemplateLayout, parent, false);
            if(mode.equals("cuisine")){
                row.setBackgroundResource(R.drawable.background_border_orange);
            }else if(mode.equals("canteen")){
                row.setBackgroundResource(R.drawable.background_border_purple);
            }

            holder = new ReviewItem();
            holder.name = (TextView) row.findViewById(R.id.userName);
            holder.rating = (RatingBar) row.findViewById(R.id.ratingBar);
            holder.comment = (TextView) row.findViewById(R.id.comment);
            holder.dateTime = (TextView) row.findViewById(R.id.dateTime);
            row.setTag(holder);

        } else {
            holder = (ReviewItem) row.getTag();
        }

        holder.name.setText(data.get(position).getUserName());
        DecimalFormat decimalFormat=new DecimalFormat("#");
        holder.rating.setRating((float)data.get(position).getRating());
        holder.comment.setText(data.get(position).getComment());
        holder.dateTime.setText(data.get(position).getDateTime());

        return row;
    }

    static class ReviewItem {
        TextView name;
        RatingBar rating;
        TextView comment;
        TextView dateTime;
    }
}
