package com.example.eventeger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    CardView settings, home, eventliste;
    BottomNavigationView navi;
    TextView username_home, username_pr;

    TextView username, fullname, email, birthday;
    private String nickname = LoginActivity.nickname;
    private ImageView imageViewUser;
    Button deleteAccount;

    String url = "https://eventager.de/login/";

    RecyclerView recyclerView;
    String s1[], s2[];
    private final int[] images = {R.drawable.p,R.drawable.f,R.drawable.e,R.drawable.f,R.drawable.e,R.drawable.p,R.drawable.p};
    int i = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------home------- //
        username_home = findViewById(R.id.textUsername1);
        username_home.setText(nickname+ ",");
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

        deleteAccount = findViewById(R.id.deleteAccount1);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "deleteAccount.php",
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
                                        params.put("username", nickname);
                                        return params;
                                    }

                                };
                                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                                requestQueue.add(stringRequest);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bist du dir wirklich sicher, dass du deinen Account unwiderruflich löschen möchtest?").setPositiveButton("Ja", dialogClickListener)
                        .setNegativeButton("Abbrechen", dialogClickListener).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.languages_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.locations_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


        if (i == 1) {
            a();
        }

        navi.setOnNavigationItemSelectedListener
                ((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_main:
                            home.setVisibility(View.VISIBLE);
                            eventliste.setVisibility(View.VISIBLE);
                            settings.setVisibility(View.INVISIBLE);

                            s();

                            break;
                        case R.id.nav_setting:
                            home.setVisibility(View.GONE);
                            eventliste.setVisibility(View.GONE);
                            settings.setVisibility(View.VISIBLE);

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
        eventliste.setVisibility(View.VISIBLE);
        settings.setVisibility(View.GONE);
        s();

    }

    public void user() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "getdata.php",
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

    }
}








