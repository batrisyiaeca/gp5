package com.example.group5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private CardView contactUs, viewRoom, logout, bookForm, viewReceipt;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (CardView) findViewById(R.id.cv_logout);
        profile = (TextView) findViewById(R.id.tv_viewprofile);
        contactUs = (CardView) findViewById(R.id.cv_contactus);
        viewRoom = (CardView) findViewById(R.id.cv_viewroom);
        bookForm = (CardView) findViewById(R.id.cv_bookroom);
        viewReceipt = (CardView) findViewById(R.id.cv_receipt);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();


            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SecondActivity.this, ContactUs.class));
            }
        });

        viewRoom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SecondActivity.this, ViewRoom.class));
            }
        });

        bookForm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SecondActivity.this, BookingForm.class));
            }
        });

        viewReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Receipt.class));
            }
        });
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.logoutMenu: {
                Logout();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
                break;

            case R.id.AboutUsMenu:
                startActivity(new Intent(SecondActivity.this, AboutUs.class));
                break;

            case R.id.ContactUsMenu:
                startActivity(new Intent (SecondActivity.this,ContactUs.class));
                break;

            case R.id.ViewRoomMenu:
                startActivity(new Intent(SecondActivity.this, ViewRoom.class));

            case R.id.BookingFormMenu:
                startActivity(new Intent(SecondActivity.this, BookingForm.class));
        }

        return super.onOptionsItemSelected(item);
    }
}