package com.root.cz3002.cantu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import com.root.cz3002.cantu.model.MenuItem;
import com.root.cz3002.cantu.model.Review;
import com.root.cz3002.cantu.model.Stall;

import java.util.ArrayList;

import me.himanshusoni.quantityview.QuantityView;

public class MainActivity extends AppCompatActivity {

    private String id;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide upper bars
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
            id = bundleOld.getString("ID");
        }

        final Context context = this;
        final Bundle bundle = new Bundle();

        FloatingActionButton switchMode = (FloatingActionButton) findViewById(R.id.switchMode);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        list = (LinearLayout) inflater.inflate(R.layout.list_view, (ViewGroup) findViewById(R.id.list));
        if (mode.equals("canteen")) {
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
                Intent intent = new Intent(context, OrderActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton dabaoer = (FloatingActionButton) findViewById(R.id.dabaoer);
        dabaoer.setImageResource(R.drawable.dabaoer);
        dabaoer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        dabaoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DabaoerActivity.class);
                    startActivity(intent);
            }
        });

        FloatingActionButton creditCard = (FloatingActionButton) findViewById(R.id.cash);
        creditCard.setImageResource(R.drawable.cash);
        creditCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Credit.class);
                startActivity(intent);
            }
        });

        run = new Runnable() {
            public void run() {
                //reload content
                //reviews.clear();
                //reviews.addAll(db.readAll());
                reviewAdapter.notifyDataSetChanged();
                reviewListView.invalidateViews();
                reviewListView.refreshDrawableState();
            }
        };
    }

    private boolean addNewItemInList(LinearLayout list, final Stall cur, final MenuItem menu) {
        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.HORIZONTAL);

        if(cur != null & menu==null) {
            StallListView view = new StallListView(this,cur);
            view.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
            if (mode.equals("canteen")) {
                view.setBackgroundResource(R.drawable.background_border_purple);
            } else {
                view.setBackgroundResource(R.drawable.background_border_orange);
            }
            a.addView(view);
            a.setTag(cur);
            a.setOnClickListener(new View.OnClickListener() {
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
            a.addView(menuView);

            ImageView inputToOrder = (ImageView) menuView.findViewById(MenuListView.ORDER_INT);
            final QuantityView quantity = (QuantityView) menuView.findViewById(MenuListView.QUANTITY_INT);

            inputToOrder.setTag(menu);

            inputToOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuItem menuItem = (MenuItem) v.getTag();

                    //To Order Database input menu and qty

                    Toast.makeText(MainActivity.this, "Input to DB "+ menuItem.getName() + " with quantity "+quantity.getQuantity(), Toast.LENGTH_SHORT).show();

                }
            });

        }

        list.addView(a);
        return false;
    }

    public void populateList(View v) {
        list.removeAllViews();
        if(bottomBar!=null){
            bottomBar.removeAllViews();
            bottomBar.setOnClickListener(null);
        }

        String category = v.getTag().toString();

        Toast.makeText(MainActivity.this, category, Toast.LENGTH_SHORT).show();
        //dummy, example for populating list
        Stall temp = new Stall(1,"MiniWok","A","Chinese","10:00-20:00");
        addNewItemInList(list, temp, null);

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

        System.out.println("MODE: ");
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

        for(int i=0;i<10;i++){
            MenuItem item = new MenuItem(i,"Sambal Fried Chicken","MiniWok",3.50);
            addNewItemInList(list, null, item);

            MenuItem item2 = new MenuItem(i,"Kung Pao Chicken Rice","MiniWok",4.00);
            addNewItemInList(list, null, item2);
        }

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

        //populate menuItems arraylist
        //using Database's method
       /* for (MenuItem item : menuItems) {
            addNewItemInList(list, item);
        }*/
    }

    private void showInfoAndReviews(Stall stall) {

        final Stall theStall = stall;

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View bottomSheetView = null;

        //populate reviews arraylist order on recent
        //using Database's method

        //Dummy

        reviews.clear();
        Review review1 = new Review(1,"shelina","Very delicious!", "11-08-2017 17:05", 4);
        Review review2 = new Review(2,"lusandro","Urgh bad taste!", "11-08-2017 16:00", 2);

        reviews.add(review1);
        reviews.add(review2);

        if(reviews.isEmpty()){
            bottomSheetView = inflater.inflate(R.layout.bottom_sheet, null);
        }
        else {
            reviewAdapter =
                    new ReviewAdapter(this,
                            R.layout.review_list,
                            R.id.userName,
                            reviews,
                            mode
                    );

            reviewListView = new ListView(this);
            reviewListView.setAdapter(reviewAdapter);

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
        }

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
                        float rating = ratingBar.getRating();

                        //Input into Database Here

                        Toast.makeText(MainActivity.this, "Rating: "+rating+" and Comment: "+comment, Toast.LENGTH_SHORT).show();

                        //to refresh layout
                        //with dummy
                        Review review3 = new Review(3,"Lorem","Meh!", "11-08-2017 18:30", 1);
                        reviews.add(review3);
                        System.out.println(reviews);
                        runOnUiThread(run);
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

}
