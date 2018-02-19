package com.root.cz3002.cantu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.root.cz3002.cantu.model.MenuItem;
import com.root.cz3002.cantu.model.OrderPayData;
import com.root.cz3002.cantu.model.Review;
import com.root.cz3002.cantu.model.Stall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import me.himanshusoni.quantityview.QuantityView;

public class MainActivity extends AppCompatActivity {
    private static final String MENU ="menu" ;
    public static ArrayList<OrderPayData> orderPayRequests=new ArrayList<OrderPayData>();
    private static String STALL="stall";
    public static String id;
    private String mode;
    private LinearLayout list;
    private RelativeLayout bottomBar;
    private boolean inStall = false;
    private View belongsToCanteenView;
    private RelativeLayout bottomBarCanteen;
    //private ArrayList<Stall> groupStalls = new ArrayList<Stall>(); //to fill with content of list for stalls in canteen/cuisine choice
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ReviewAdapter reviewAdapter;
    private ListView reviewListView;
    private Runnable run;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference canteenDatabaseReference;
    private ValueEventListener canteenValueEventListener;
    private ValueEventListener cuisineValueEventListener;
    private DatabaseReference reviewDatabaseReference;
    private ChildEventListener reviewChildEventListener;
    private ValueEventListener reviewValueEventListener;

    private ValueEventListener stallValueEventListener;
    private DatabaseReference cuisineDatabaseReference;

    private ValueEventListener dishValueEventListener;
    private DatabaseReference dishDataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.id=null;
        //Hide upper bars

        firebaseDatabase=FirebaseDatabase.getInstance();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();
        Intent intent = getIntent();
        if (intent.hasExtra("MODE")) {
            mode = bundleOld.getString("MODE");
        }
        if (intent.hasExtra("ID")) {
            MainActivity.id = bundleOld.getString("ID");
            Toast.makeText(MainActivity.this,"I am "+id, Toast.LENGTH_LONG).show();
        }

        final Context context = this;
        final Bundle bundle = new Bundle();

        FloatingActionButton switchMode = (FloatingActionButton) findViewById(R.id.switchMode);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        list = (LinearLayout) inflater.inflate(R.layout.list_view, (ViewGroup) findViewById(R.id.list));
        if (mode.equals("canteen")) {
            canteenDatabaseReference=firebaseDatabase.getReference().child("canteen");
            View upperBar = inflater.inflate(R.layout.canteen_bar, (ViewGroup) findViewById(R.id.toolbar));
            switchMode.setImageResource(R.drawable.cuisine);
            switchMode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_orange_light)));
            switchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    bundle.putString("ID",id);
                    bundle.putString("MODE", "cuisine");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
            populateList(findViewById(R.id.cana));
        }
        else {
            //stallDatabaseReference=firebaseDatabase.getReference().child("stalls");
            View upperBar = inflater.inflate(R.layout.cuisine_bar, (ViewGroup) findViewById(R.id.toolbar));
            switchMode.setImageResource(R.drawable.store);
            switchMode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            switchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    bundle.putString("ID",id);
                    bundle.putString("MODE", "canteen");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
            populateList(findViewById(R.id.chinese));
        }
        FloatingActionButton orderList = (FloatingActionButton) findViewById(R.id.orderList);
        orderList.setImageResource(R.drawable.order_list);
        orderList.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==null){
                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(context, OrderActivity.class);
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton dabaoer = (FloatingActionButton) findViewById(R.id.dabaoer);
        dabaoer.setImageResource(R.drawable.dabaoer);
        dabaoer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        dabaoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==null){
                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(context, DabaoerActivity.class);
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton creditCard = (FloatingActionButton) findViewById(R.id.cash);
        creditCard.setImageResource(R.drawable.cash);
        creditCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id==null){
                    Toast.makeText(getApplicationContext(),"You have to login first",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(context, Credit.class);
                    startActivity(intent);
                }
            }
        });

        run = new Runnable() {
            public void run() {
                //reload content
                populateReview();
            }
        };
    }

    private boolean addNewItemInList(LinearLayout list, final Stall cur, final MenuItem menu) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        if(cur != null & menu==null) {
            StallListView view = new StallListView(this,cur);
            view.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
            if (mode.equals("canteen")) {
                view.setBackgroundResource(R.drawable.background_border_purple);
            } else {
                view.setBackgroundResource(R.drawable.background_border_orange);
            }
            linearLayout.addView(view);
            linearLayout.setTag(cur);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomBarCanteen.setVisibility(View.INVISIBLE);
                    populateMenu(v);
                }
            });
        }
        else if(cur==null & menu != null){
            MenuListView menuView = new MenuListView(this, menu, mode);
            menuView.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
            if (mode.equals("canteen")) {
                menuView.setBackgroundResource(R.drawable.background_border_purple);
            } else {
                menuView.setBackgroundResource(R.drawable.background_border_orange);
            }
            linearLayout.addView(menuView);

            ImageView inputToOrder = (ImageView) menuView.findViewById(MenuListView.ORDER_INT);
            final QuantityView quantity = (QuantityView) menuView.findViewById(MenuListView.QUANTITY_INT);

            inputToOrder.setTag(menu);

            inputToOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuItem menuItem = (MenuItem) v.getTag();
                    if(quantity.getQuantity()!=0 && MainActivity.id!=null){
                        OrderPayData o=new OrderPayData(true,"","user",
                                menuItem.getPrice(),menuItem.getName(),
                                menuItem.getStall(),
                                "Canteen "+menuItem.getCanteen(),
                                quantity.getQuantity());
                        //Log.e("DATA", /*menuItem.getName()+*/" "+menuItem.getStall()+" "+menuItem.getPrice()+" "+quantity.getQuantity());
                        MainActivity.orderPayRequests.add(o);
                        Toast.makeText(MainActivity.this, "Input to DB " + menuItem.getName() + " with quantity " + quantity.getQuantity(), Toast.LENGTH_SHORT).show();
                        quantity.setQuantity(0);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Please input the quantity!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

        list.addView(linearLayout);
        return false;
    }

    public void populateList(View v) {
        list.removeAllViews();
        if(bottomBar!=null){
            bottomBar.removeAllViews();
            bottomBar.setOnClickListener(null);
        }

        String category = v.getTag().toString();

        Log.e("Mode ", mode);
        if(mode.equals("canteen")) {

            canteenDatabaseReference = firebaseDatabase.getReference().child("canteen").child(category.toString()).child("stalls");
            canteenValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list.removeAllViews();
                    Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                    addDataToList(data,STALL);
                }

                @Override
                public void onCancelled (DatabaseError databaseError){

                }
            };
            canteenDatabaseReference.addValueEventListener(canteenValueEventListener);

        }
        else if(mode.equals("cuisine")){
            cuisineDatabaseReference=firebaseDatabase.getReference().child("cuisine").child(category);

            cuisineValueEventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list.removeAllViews();
                    Log.e("It ran: ", "Yes");
                    Map<String,Object> data=(Map<String, Object>) dataSnapshot.getValue();
                    addDataToList(data,STALL);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            cuisineDatabaseReference.addValueEventListener(cuisineValueEventListener);
        }

        //dummy, example for populating list
        /*Stall temp = new Stall(1,"MiniWok","A","Chinese","10:00-20:00");
        addNewItemInList(list, temp, null);*/

        //populate groupStalls arraylist
        //using Database's method
        /*Stall temp;
       while (true) {
            temp = new Stall(get data from db);
            addNewItemInList(list, temp);
            if database empty
                break;
        }
        =========OR===========
        for (Stall cur : gropStalls){

        }
      */

        /////load canteen name
        bottomBarCanteen = (RelativeLayout) findViewById(R.id.bottom_bar_category);
        TextView categoryInfo = (TextView) findViewById(R.id.textCanteen);

        if(mode.equals("canteen")) {
            System.out.println("IN CANTEEN");
            categoryInfo.setBackgroundResource(R.drawable.rounded_corner_purple);
            category = "Canteen " + category;
            System.out.println("The Category INSIDE: " + category + ", " + categoryInfo.getText().toString());
        }

        bottomBarCanteen.setVisibility(View.VISIBLE);
        categoryInfo.setText(category);
        System.out.println("The Category: " + category + ", " + categoryInfo.getText().toString());
        //////////////////////

        belongsToCanteenView = v;
    }

    public void populateMenu(View v) {
        list.removeAllViews();
        final Stall stall = (Stall) v.getTag();
        String stallName = stall.getName();
        System.out.println(stallName);
        Toast.makeText(MainActivity.this, stallName, Toast.LENGTH_LONG).show();

        //dummy, example for populating Menu Items
        dishDataBaseReference=firebaseDatabase.getReference().child("stall").child(stallName);
        //dummy, example for populating Menu Items
        dishValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> data= (Map<String, Object>) dataSnapshot.getValue();
                addDataToList(data,MENU);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dishDataBaseReference.addValueEventListener(dishValueEventListener);


        inStall = true;

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        bottomBar = (RelativeLayout) inflater.inflate(R.layout.stall_info, (ViewGroup) findViewById(R.id.bottom_bar));
        TextView stallInfo = (TextView) bottomBar.findViewById(R.id.textStall);
        stallInfo.setText(stallName);

        if(mode.equals("canteen")) {
            findViewById(R.id.textStall).setBackgroundResource(R.drawable.rounded_corner_purple);
        }

        bottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoAndReviews(stall);
            }
        });
    }

    private void populateReview(){

        reviewValueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviews.clear();

                Map<String, Object> ids= (Map<String, Object>) dataSnapshot.getValue();

                if(ids!=null) {
                    for (Map.Entry<String, Object> data : ids.entrySet()) {
                        Review review = new Review();

                        Map<String, Object> d = (Map<String, Object>) data.getValue();
                        for (Map.Entry<String, Object> fin : d.entrySet()) {
                            //Toast.makeText(MainActivity.this,"Inside for",Toast.LENGTH_SHORT).show();

                            if (fin.getKey().toString().equals("id")) {
                                review.setId(fin.getValue().toString());
                            }
                            if (fin.getKey().toString().equals("stallName")) {
                                review.setStallName(fin.getValue().toString());
                            }
                            if (fin.getKey().toString().equals("comment")) {
                                review.setComment(fin.getValue().toString());
                            }
                            if (fin.getKey().toString().equals("userName")) {
                                review.setUserName(fin.getValue().toString());
                            }
                            if (fin.getKey().toString().equals("rating")) {
                                review.setRating(Double.parseDouble(String.valueOf(fin.getValue())));
                            }
                            if (fin.getKey().toString().equals("dateTime")) {
                                review.setDateTime(fin.getValue().toString());
                            }
                        }
                        reviews.add(review);
                    }
                }

                System.out.println("SIZE OF LV !!!! "+reviews.size());

                reviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        };

        reviewDatabaseReference.addValueEventListener(reviewValueEventListener);

    }

    private void showInfoAndReviews(final Stall stall) {

        reviewDatabaseReference=firebaseDatabase.getReference().child("review").child(stall.getName());

        final Stall theStall = stall;

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View bottomSheetView = null;

        reviews.clear();

        reviewAdapter =
                new ReviewAdapter(this,
                        R.layout.review_list,
                        R.id.userName,
                        reviews,
                        mode
                );

        reviewListView = new ListView(this);
        reviewListView.setAdapter(reviewAdapter);

        populateReview();

        bottomSheetView = inflater.inflate(R.layout.bottom_sheet, null);

        final LinearLayout ll = (LinearLayout) bottomSheetView.findViewById(R.id.reviews);
        ll.addView(reviewListView);

        reviewListView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        TextView stallName = (TextView) bottomSheetView.findViewById(R.id.textStall);
        TextView openingHour =  (TextView) bottomSheetView.findViewById(R.id.openingHour);
        TextView type = (TextView) bottomSheetView.findViewById(R.id.type);
        TextView location = (TextView) bottomSheetView.findViewById(R.id.location);
        Button writeReview = (Button) bottomSheetView.findViewById(R.id.btn_review);

        stallName.setText(stall.getName());
        openingHour.setText("Opening Hours: "+stall.getOpeningHour());
        type.setText("Type: "+stall.getCuisine());
        location.setText("Location: "+stall.getCanteen());

        if(mode.equals("canteen")){
            stallName.setBackgroundResource(R.drawable.background_purple);
            writeReview.setBackgroundResource(R.drawable.background_purple);
        }

        writeReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(id==null){
                    Toast.makeText(getApplicationContext(),"You have to login first to write review",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View dialogView = inflater.inflate(R.layout.review_dialog, null);
                    TextView stallName = (TextView) dialogView.findViewById(R.id.stallName);
                    stallName.setText(theStall.getName());

                    builder.setView(dialogView);
                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText editText = (EditText) dialogView.findViewById(R.id.commentWrite);
                            String comment = editText.getText().toString();

                            RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.ratingBarWrite);
                            double rating = ratingBar.getRating();

                            //Toast.makeText(MainActivity.this, "Rating: "+rating+" and Comment: "+comment, Toast.LENGTH_SHORT).show();

                            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
                            String currentDateTimeString = df.format(Calendar.getInstance().getTime());

                            //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

                            Review review = new Review();
                            String key = reviewDatabaseReference.push().getKey();
                            review.setComment(comment);
                            review.setDateTime(currentDateTimeString);
                            review.setRating(rating);
                            review.setUserName(MainActivity.id);
                            review.setStallName(theStall.getName());
                            review.setId(key);

                            reviewDatabaseReference.child(key).setValue(review);

                            //runOnUiThread(run);
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);

        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        mBehavior.setPeekHeight(600);

        bottomSheetDialog.show();
    }

    @Override
    public void onBackPressed()
    {
        if(inStall){
            populateList(belongsToCanteenView);
            bottomBar.setOnClickListener(null);
            inStall = false;
            return;
        }

        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs

    }

    private void addDataToList(Map<String,Object> data,String type) {
        if (data != null) {
            if (type.equals(STALL)) {
                for (Map.Entry<String, Object> s : data.entrySet()) {

                    Map<String, Object> stall = (Map<String, Object>) s.getValue();
                    Stall st = new Stall();
                    st.setName(stall.get("name").toString());
                    st.setCanteen(stall.get("canteen").toString());
                    st.setCuisine(stall.get("cuisine").toString());
                    st.setOpeningHour(stall.get("OpeningHours").toString());
                    st.setId((Long) stall.get("id"));
                    addNewItemInList(list, st, null);
                }
            } else if (type.equals(MENU)) {
                for (Map.Entry<String, Object> s : data.entrySet()) {

                    Map<String, Object> menu = (Map<String, Object>) s.getValue();
                    MenuItem dish = new MenuItem((Long) menu.get("id"),
                            menu.get("name").toString(),
                            menu.get("stall").toString(), (Double) menu.get("price"),menu.get("canteen").toString());
//                    dish.setName();
                    addNewItemInList(list, null, dish);
                }
            } else {
                Toast.makeText(MainActivity.this, "Sorry no Data", Toast.LENGTH_SHORT).show();
                ;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(canteenValueEventListener!=null)
        {
            canteenDatabaseReference.removeEventListener(canteenValueEventListener);
        }if(cuisineValueEventListener!=null)
        {
            cuisineDatabaseReference.removeEventListener(cuisineValueEventListener);
        }
        if(dishValueEventListener!=null)
        {
            dishDataBaseReference.removeEventListener(dishValueEventListener);
        }
        if(reviewValueEventListener!=null)
        {
            reviewDatabaseReference.removeEventListener(reviewValueEventListener);
        }

    }
}
