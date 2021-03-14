package com.example.group5;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewRoom extends AppCompatActivity {

    Button bookbtn1, bookbtn2, bookbtn3, bookbtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewroom);

        bookbtn1 = (Button) findViewById(R.id.bookbtn1);
        bookbtn2 = (Button) findViewById(R.id.bookbtn2);
        bookbtn3 = (Button) findViewById(R.id.bookbtn3);
        bookbtn4 = (Button) findViewById(R.id.bookbtn4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRoom.this, BookingForm.class));
            }
        });

        bookbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRoom.this, BookingForm.class));
            }
        });

        bookbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRoom.this, BookingForm.class));
            }
        });

        bookbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRoom.this, BookingForm.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}
