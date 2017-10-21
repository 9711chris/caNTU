package com.root.cz3002.cantu;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.root.cz3002.cantu.model.DRChildData;
import com.root.cz3002.cantu.model.SOChildData;

import java.util.List;

/**
 * Created by brigi on 21/10/2017.
 */

public class DRExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private String  _listDataHeader; // header titles
    // child data in format of header title, child title
    private  List<DRChildData> _listDataChild;

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.d_r_list_header, null);

        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.list_food);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(_listDataHeader);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final SOChildData childObj = (SOChildData) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.d_r_list_item, null);
        }

        TextView txtListChild1 = (TextView) convertView
                .findViewById(R.id.food_name);
        txtListChild1.setText(childObj.getFoodName());

        TextView txtListChild2 = (TextView) convertView
                .findViewById(R.id.qty);
        txtListChild2.setText(String.valueOf(childObj.getQty()));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
