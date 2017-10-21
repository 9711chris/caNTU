package com.root.cz3002.cantu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.SOChildData;
import com.root.cz3002.cantu.model.SOHeaderData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Christantia on 10/20/2017.
 */

public class SOExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference orderDatabaseReference=firebaseDatabase.getReference().child("orders").child("Mini Wok");
    private Query idQuery;
    private List<SOHeaderData> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<SOChildData>> _listDataChild;
    private RecyclerView.ViewHolder holder;


    public SOExpandableListAdapter(Context context, List<SOHeaderData> listDataHeader,
                                   HashMap<String, List<SOChildData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }


    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getOrderSeq())
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getOrderSeq())
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        SOHeaderData headerObj = (SOHeaderData) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.s_o_listgroup, null);

        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.order_num);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerObj.getOrderSeq());

        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //TODO: DELETE FROM DATABASE

                 orderDatabaseReference.child(_listDataHeader.get(groupPosition).getId()).setValue(null);
                 //idQuery=orderDatabaseReference.orderByChild("id").equalTo(_listDataHeader.get(groupPosition).getId());
                 //idQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                     @Override
//                     public void onDataChange(DataSnapshot dataSnapshot) {
//                         for(DataSnapshot data:dataSnapshot.getChildren())
//                         {
//                             Log.e("ref ",data.getRef().toString());
//                             data.getRef().removeValue();
//                         }
//                     }
//
//                     @Override
//                     public void onCancelled(DatabaseError databaseError) {
//
//                     }
//
                 //});

                 //_listDataChild.remove(_listDataHeader.get(groupPosition).getOrderSeq());
                 //_listDataHeader.remove(_listDataHeader.get(groupPosition));
                 notifyDataSetChanged();
                 Toast.makeText(_context, "Deleted", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(_context, OwnerActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 _context.startActivity(intent);
             }
         });

        TextView price = (TextView) convertView.findViewById(R.id.total_price);
        price.setText("Total price: $" + String.valueOf(headerObj.getTotalPrice()));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final SOChildData childObj = (SOChildData) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.s_o_listitem, null);
            }

            TextView txtListChild1 = (TextView) convertView
                    .findViewById(R.id.food_name);
            txtListChild1.setText(childObj.getFoodName());

            TextView txtListChild2 = (TextView) convertView
                    .findViewById(R.id.qty);
            txtListChild2.setText("Quantity: " + String.valueOf(childObj.getQty()));


            return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
