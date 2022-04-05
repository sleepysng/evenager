package com.example.eventeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    CardView profile, settings, home, eventliste, evendetails;
    BottomNavigationView navi;
    TextView username_home,username_pr;
    String s1[],s2[];
    int images[] = {};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username_home = findViewById(R.id.textUsername1);
        username_pr = findViewById(R.id.textUsername);
        profile = findViewById(R.id.profile);
        settings = findViewById(R.id.setting);
        home = findViewById(R.id.home);
     // evendetails = findViewById(R.id.eventDetail);
        eventliste = findViewById(R.id.eventList);
        navi = findViewById(R.id.bottom_nav);

        username_home.setText("Ruphyy");
        username_pr.setText("ruphyy");



        home.setVisibility(View.VISIBLE);
        profile.setVisibility(View.GONE);
        eventliste.setVisibility(View.VISIBLE);
        settings.setVisibility(View.GONE);
//        evendetails.setVisibility(View.GONE);

        navi.setOnNavigationItemSelectedListener
                ((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_main:
                            home.setVisibility(View.VISIBLE);
                            eventliste.setVisibility(View.VISIBLE);
                          //  evendetails.setVisibility(View.INVISIBLE);
                            profile.setVisibility(View.INVISIBLE);
                            settings.setVisibility(View.INVISIBLE);


                            break;
                        case R.id.nav_setting:
                            home.setVisibility(View.INVISIBLE);
                           // evendetails.setVisibility(View.INVISIBLE);
                            eventliste.setVisibility(View.INVISIBLE);
                            profile.setVisibility(View.INVISIBLE);
                            settings.setVisibility(View.VISIBLE);

                            break;
                        case R.id.nav_profile:
                            home.setVisibility(View.INVISIBLE);
                           // evendetails.setVisibility(View.INVISIBLE);
                            eventliste.setVisibility(View.INVISIBLE);
                            profile.setVisibility(View.VISIBLE);
                            settings.setVisibility(View.INVISIBLE);
                    }
                    return false;
                });


    }
}