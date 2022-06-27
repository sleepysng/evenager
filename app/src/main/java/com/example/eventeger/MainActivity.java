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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    CardView profile, settings, home, eventliste, evendetails;
    BottomNavigationView navi;
    TextView username_home, username_pr;

    TextView username, fullname, email, birthday;
    private String nickname = LoginActivity.nickname;
    private ImageView imageViewUser;


    String url = "https://eventager.de/userdata/view.php";

    RecyclerView recyclerView;
    String s1[], s2[];
    private final int[] images = {R.drawable.p,R.drawable.f,R.drawable.e,R.drawable.e,R.drawable.e,R.drawable.e,R.drawable.e};
    int i = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //--------home------- //
        username_home = findViewById(R.id.textUsername1);
        username_home.setText(nickname);
        profile = findViewById(R.id.profile);
        settings = findViewById(R.id.setting);
        home = findViewById(R.id.home);
        // evendetails = findViewById(R.id.eventDetail);
        eventliste = findViewById(R.id.eventList);
        navi = findViewById(R.id.bottom_nav);
        //--------home------- //


        //--------profil------- //
        username_pr = findViewById(R.id.textUsername);

        birthday = findViewById(R.id.textBirthday);
        email = findViewById(R.id.textMail);
        fullname = findViewById(R.id.textFullname);
        imageViewUser = findViewById(R.id.profilepicture);
        //--------profil------- //


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
                            user();


                            break;

                    }
                    return false;
                });

    }


    public void s() {
        recyclerView = findViewById(R.id.eventRecycler);
        s1 = getResources().getStringArray(R.array.events);
        s2 = getResources().getStringArray(R.array.dates);
        MyAdapter ma = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(ma);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void a() {
        home.setVisibility(View.VISIBLE);
        profile.setVisibility(View.GONE);
        eventliste.setVisibility(View.VISIBLE);
        settings.setVisibility(View.GONE);
        s();

    }

    public void user() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response.trim(), Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("params", nickname);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);






   /*   private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config5.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String fullname1 = jo.getString(Config5.KEY_FULLNAME);
                String birth1 = jo.getString(Config5.KEY_BIRTH);
                String mail1 = jo.getString(Config5.KEY_MAIL);




            }


        } catch (JSONException e) {
            e.printStackTrace();

        }

*/

    }
}








