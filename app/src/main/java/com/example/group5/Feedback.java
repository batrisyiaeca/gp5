package com.example.group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    EditText nameFb, messageFb;
    Button submitFb;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;
    String name, message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameFb = (EditText) findViewById(R.id.tv_namefb);
        messageFb = (EditText) findViewById(R.id.tv_fb);
        submitFb = (Button) findViewById(R.id.btnsubmitfb);

        submitFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String name, message;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    storageReference = firebaseDatabase.getReference("Feedback").child(firebaseAuth.getUid());

                    name = nameFb.getEditableText().toString().trim();
                    message = messageFb.getEditableText().toString().trim();
                    FeedbackDetails feedbackDetails = new FeedbackDetails(name, message);
                    storageReference.setValue(feedbackDetails);

                }
            }
        });
    }

    private boolean validate() {
        Boolean result = false;

        name = nameFb.getText().toString();
        message = messageFb.getText().toString();

        if (name.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();

        } else {
            result = true;
            Toast.makeText(this, "Feedback Submitted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Feedback.this, SecondActivity.class));
        }
        return result;
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