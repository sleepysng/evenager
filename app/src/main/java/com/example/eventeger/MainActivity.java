package com.example.eventeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;


import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    CardView profile, settings, home, eventliste, evendetails;
    BottomNavigationView navi;
    TextView username_home, username_pr;


    TextView username, fullname, email, birthday;
    private static String nickname = LoginActivity.nickname;
    private ImageView imageViewUser;

    RecyclerView recyclerView;
    String s1[], s2[];
    int[] images = {R.drawable.logo_k,R.drawable.logo_d,R.drawable.logo_h,R.drawable.johnnysinsblancket,R.drawable.kekw,R.drawable.mcmahon,R.drawable.montebrille,R.drawable.re};
    int i = 1;
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
        user();




        if (i == 1) {
            a();
        }

        navi.setOnNavigationItemSelectedListener
                ((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_main:
                            home.setVisibility(View.VISIBLE);
                            eventliste.setVisibility(View.VISIBLE);

                            profile.setVisibility(View.INVISIBLE);
                            settings.setVisibility(View.INVISIBLE);

                            s();

                            break;
                        case R.id.nav_setting:
                            home.setVisibility(View.GONE);

                            eventliste.setVisibility(View.GONE);
                            profile.setVisibility(View.GONE);
                            settings.setVisibility(View.VISIBLE);

                            break;
                        case R.id.nav_profile:
                            home.setVisibility(View.GONE);

                            eventliste.setVisibility(View.GONE);
                            profile.setVisibility(View.VISIBLE);
                            settings.setVisibility(View.GONE);



                            break;

                    }
                    return false;
                });

    }


public void s (){
    recyclerView = findViewById(R.id.eventRecycler);
    s1 = getResources().getStringArray(R.array.letters);
    s2 = getResources().getStringArray(R.array.description);
    MyAdapter ma = new MyAdapter(this, s1, s2, images);
    recyclerView.setAdapter(ma);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
}

public void a(){
    home.setVisibility(View.VISIBLE);
    profile.setVisibility(View.GONE);
    eventliste.setVisibility(View.VISIBLE);
    settings.setVisibility(View.GONE);
    s();

}

public void user(){

        if(nickname.equals("sleepy")){

        }
        else if(nickname.equals("rupphy")){

        }

}

    }






