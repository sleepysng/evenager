package com.example.eventeger;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {

    TextView username, fullname, email, birthday;
    private static String nickname = LoginActivity.nickname;
    private ImageView imageViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //try {
        // TODO: Username benutzen: URL url = new URL("http://ruphyy.de/user-imgs/user-" + nickname + ".png");
        //    URL url = new URL("http://ruphyy.de/user-imgs/user-leon.png");
        //    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        //    imageViewUser.setImageBitmap(bmp);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}


    }
}