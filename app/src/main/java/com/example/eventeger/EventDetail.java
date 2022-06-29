package com.example.eventeger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetail extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;
    Button eventCancel;
    String s1, s2;
    int myImage;

    // TODO: Fix Error

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        mainImageView = findViewById(R.id.eventKind);
        title = findViewById(R.id.eventTopic);
        description = findViewById(R.id.eventDescp);
        eventCancel = findViewById(R.id.backButton);

        eventCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventDetail.this, MainActivity.class);
                startActivity(i);
            }
        });

        //getData();
        //setData();
    }

    private void getData() {
        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("s1") && getIntent().hasExtra("s2")) {

            s1 = getIntent().getStringExtra("s1");
            s2 = getIntent().getStringExtra("s2");
            myImage = getIntent().getIntExtra("myImage", 1);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData() {
        title.setText(s1);
        description.setText(s2);
    }

}

