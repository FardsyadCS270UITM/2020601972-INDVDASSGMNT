package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {

    ImageView github;
    ImageView youtube;
    ImageView gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        github = findViewById(R.id.github);
        youtube = findViewById(R.id.youtube);
        gmail = findViewById(R.id.gmail);

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://github.com/FardsyadCS270UITM");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.youtube.com/channel/UCH3he1lriCtmXfuNBkeZ67g");
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://mail.google.com/mail/u/0/?ogbl#inbox");
            }
        });
    }

    private void gotourl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri) );
    }
}