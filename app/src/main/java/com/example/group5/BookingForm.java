package com.example.group5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.Normalizer;
import java.util.Calendar;


public class BookingForm extends AppCompatActivity {

    String[] rooms;

    EditText nameForm, emailForm, phoneForm;
    TextView dateForm;
    Button submitForm;
    Spinner roomForm;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "Booking Form";

    String name, email, phone, date, room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        nameForm = findViewById(R.id.tvName);
        emailForm = findViewById(R.id.tvEmail);
        phoneForm = findViewById(R.id.tvPhone);

        roomForm = findViewById(R.id.spRoomType);

        dateForm = (TextView) findViewById(R.id.tvDateForm);

        submitForm = findViewById(R.id.btnSubmitForm);

        rooms = getResources().getStringArray(R.array.room_array);
        roomForm = (Spinner) findViewById(R.id.spRoomType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, rooms);
        roomForm.setAdapter(adapter);
        roomForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                roomForm.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

       dateForm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar cal = Calendar.getInstance();
               int month = cal.get(Calendar.MONTH);
               int day = cal.get(Calendar.DAY_OF_MONTH);
               int year = cal.get(Calendar.YEAR);

               DatePickerDialog dialog = new DatePickerDialog(
                       BookingForm.this,
                       android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day
               );
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
           }
       });

       onDateSetListener = new DatePickerDialog.OnDateSetListener(){
           @Override
           public void onDateSet(DatePicker datePicker, int month, int day, int year){
               Log.d(TAG, "onDateSet: mm/dd/yy" + month + "/" + day + "/" + year );

               String date = month + "/" + day + "/" + year;
               dateForm.setText(date);
           }
       };

        submitForm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (validate()) {
                    String name, email, phone, room, date;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    name = nameForm.getEditableText().toString().trim();
                    storageReference = firebaseDatabase.getReference("Booking").child(firebaseAuth.getUid());

                    email = emailForm.getEditableText().toString().trim();

                    room = roomForm.getSelectedItem().toString().trim();


                    phone = phoneForm.getEditableText().toString().trim();

                    date = dateForm.getText().toString().trim();
                    BookingDetail bookingDetail = new BookingDetail(name, email, phone, room, date);
                    storageReference.setValue(bookingDetail);
                }
            }
        });
    }

    private boolean validate(){
       Boolean result = false;

       name = nameForm.getText().toString();
       email = emailForm.getText().toString();
       phone = phoneForm.getText().toString();
       date = dateForm.getText().toString();
       room = roomForm.getSelectedItem().toString();

       if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty() || room.isEmpty()){
           Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(BookingForm.this, BookingForm.class));
       }
       else{
           result = true;
           Toast.makeText(this, "Booking Successful", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(BookingForm.this, Receipt.class));
       }
       return result;
    }
}