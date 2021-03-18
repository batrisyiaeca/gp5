package com.example.group5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Receipt extends AppCompatActivity {

    TextView bookingName, bookingEmail, bookingPhone, bookingRoom, bookingDate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        bookingName = (TextView) findViewById(R.id.tv_bookingName);
        bookingEmail = (TextView) findViewById(R.id.tv_bookingEmail);
        bookingPhone = (TextView) findViewById(R.id.tvBookingPhone);
        bookingRoom = (TextView) findViewById(R.id.tvBookingRoom);
        bookingDate = (TextView) findViewById(R.id.tvBookingDate);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("Booking").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookingDetail bookingDetail = dataSnapshot.getValue(BookingDetail.class);
                bookingName.setText(bookingDetail.getName());
                bookingEmail.setText(bookingDetail.getEmail());
                bookingPhone.setText(bookingDetail.getPhone());
                bookingRoom.setText(bookingDetail.getRoom());
                bookingDate.setText(bookingDetail.getDate());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Receipt.this, error.getCode(), Toast.LENGTH_SHORT).show();
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