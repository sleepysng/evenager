package com.example.eventeger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText password ,username;
    private String pPassword, pUsername;
    public static String nickname;
    private TextView goregister;
    private String URL ="https://eventager.de/login/login.php";
    private Button loginbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goregister = findViewById(R.id.goregister);

        goregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardRegister();
            }
        });

        pPassword = pUsername = "";
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {

        pPassword = password.getText().toString().trim();
        pUsername = username.getText().toString().trim();


        // TODO: Testen ob " " funktioniert..
        if (!pPassword.equals("") || !pUsername.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    response = response.trim();
                    System.out.println(response);
                    if (response.equals("1")) {
                        forwardMain();
                        nickname=pUsername;

                    } else if(response.equals("0")) {
                        Toast.makeText(LoginActivity.this, "Invalid login!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> data = new HashMap<>();
                    data.put("password", pPassword);
                    data.put("username", pUsername);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        } else {
            Toast.makeText(LoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }
    }

    public void forwardMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void forwardRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}