package com.example.group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class BookingForm extends AppCompatActivity {

    EditText nameForm, emailForm,  phoneForm, timeForm, dateForm, roomForm;
    Button submitForm;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;

    String name, email, phone, time, date, room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        nameForm = findViewById(R.id.tvName);
        emailForm = findViewById(R.id.tvEmail);
        phoneForm = findViewById(R.id.tvPhone);
        timeForm = findViewById(R.id.tvTime);
        dateForm = findViewById(R.id.tvDate);
        roomForm = findViewById(R.id.tvRoom);

        submitForm = findViewById(R.id.btnSubmitForm);

        submitForm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name, email, phone, time, date;
                firebaseDatabase = FirebaseDatabase.getInstance();
                name = nameForm.getEditableText().toString().trim();
                storageReference = firebaseDatabase.getReference("Booking").child(name);
                email = emailForm.getEditableText().toString().trim();
                room = emailForm.getEditableText().toString().trim();

                phone = phoneForm.getEditableText().toString().trim();
                time = timeForm.getEditableText().toString().trim();
                date = dateForm.getEditableText().toString().trim();
                BookingDetail bookingDetail = new BookingDetail(name, email, phone, time, date);
                storageReference.setValue(bookingDetail);
            }
        });
    }

    private boolean validate(){
       Boolean result = false;

       name = nameForm.getText().toString();
       email = emailForm.getText().toString();
       phone = phoneForm.getText().toString();
       time = timeForm.getText().toString();
       date = dateForm.getText().toString();

       if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || time.isEmpty() || date.isEmpty()){
           Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(BookingForm.this, BookingForm.class));
       }
       else{
           result = true;
           Toast.makeText(this, "Booking Submitted", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(BookingForm.this, Receipt.class));
       }
       return result;
    }
}