package com.example.ellui.interfaz2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;

public class Details_responsables extends AppCompatActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_responsables);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Bitmap bitmap = getIntent().getParcelableExtra("image");
        TextView titleTextView = (TextView) findViewById(R.id.title);

        setSupportActionBar(toolbar);
        id = extras.getInt("id");
        ImageView imageView = (ImageView) findViewById(R.id.imageDetails);
        imageView.setImageBitmap(bitmap);


    }

}
