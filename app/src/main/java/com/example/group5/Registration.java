package com.example.group5;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this, MainActivity.class));
                            }else{
                                Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });
    }

    private void setupUIViews()
    {
        userEmail = (EditText)findViewById(R.id.t_email);
        userName = (EditText)findViewById(R.id.t_username);
        userPassword = (EditText)findViewById(R.id.t_password2);
        regButton = (Button)findViewById(R.id.btn_register);
        userLogin = (TextView)findViewById(R.id.t_login2);
    }

    private Boolean validate()
    {
        Boolean result = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }else
        {
            result = true;
        }

        return result;
    }
}