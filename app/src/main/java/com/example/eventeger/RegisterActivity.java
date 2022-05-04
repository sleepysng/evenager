package com.example.eventeger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private final String URL = "http://95.156.227.28/login/register.php";
    private EditText username, password, email, fullname, birthday, passwordconfirm;
    private String pUsername, pPassword, pEmail, pFullname, pBirthday, pPasswordconfirm;
    private Button registerbtn, loginbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        birthday = findViewById(R.id.birthday);
        passwordconfirm = findViewById(R.id.passwordconfirm);
        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardLogin();
            }
        });
    }

    public void register() {

        pPasswordconfirm = passwordconfirm.getText().toString().trim();
        pPassword = password.getText().toString().trim();
        pFullname = fullname.getText().toString().trim();
        pUsername = username.getText().toString().trim();
        pBirthday = birthday.getText().toString().trim();
        pEmail = email.getText().toString().trim();

        // TODO: Keine Sonderzeichen im Usernamen ("!%&$äöü*")!

        if (!(pFullname.equals("") || pPassword.equals("") || pEmail.equals("") || pUsername.equals("") || pBirthday.equals("") || pPasswordconfirm.equals(""))) {
            if (!pPassword.equals(pPasswordconfirm)) {
                Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();
                        if (response.equals("success")) {
                            forwardMain();
                        } else if (response.equals("failure")) {
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("fullname", pFullname);
                        data.put("email", pEmail);
                        data.put("username", pUsername);
                        data.put("password", pPassword);
                        data.put("birthday", pBirthday);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        } else {
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void forwardMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void forwardLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

