package com.example.eventeger;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileActivity extends AppCompatActivity {

    TextView username, fullname, email, birthday;

    CardView profile, settings, home, eventliste, evendetails;
    BottomNavigationView navi;
    TextView username_home, username_pr;

    private static String nickname = LoginActivity.nickname;
    private ImageView imageViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.textUsername);
        birthday = findViewById(R.id.textBirthday);
        email = findViewById(R.id.textMail);
        fullname = findViewById(R.id.textFullname);
        imageViewUser = findViewById(R.id.profilepicture);

        // Profil-Daten
        username.setText(nickname);
        fullname.setText("Andre Kanister");
        email.setText("admin@ruphyy.de");
        birthday.setText("24.05.2003");

    }


}