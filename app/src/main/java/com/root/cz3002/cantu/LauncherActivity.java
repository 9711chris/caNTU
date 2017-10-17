package com.root.cz3002.cantu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();

        Button login = (Button) findViewById(R.id.login);
        Button logout = (Button) findViewById(R.id.logout);

        Intent intent = getIntent();
        if (intent.hasExtra("ID")) {
            id = bundleOld.getString("ID");
            login.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.VISIBLE);
            logout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(LauncherActivity.this, LauncherActivity.class);
                    startActivity(intent);
                }
            });
        }

        final Context context = this;
        final Bundle bundle = new Bundle();
        View canteen = (View) findViewById(R.id.canteen);
        canteen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                bundle.putString("MODE", "canteen");
                if (id != null)
                    bundle.putString("ID", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        View cuisine = (View) findViewById(R.id.cuisine);
        cuisine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                bundle.putString("MODE", "cuisine");
                if (id != null)
                    bundle.putString("ID", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });


        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish_activity"));
    }
}
