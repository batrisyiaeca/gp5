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
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    private EditText userName, userPassword, userEmail,userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password;

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
                                //sendEmailVerification();
                                sendUserData();
                                Toast.makeText(Registration.this, "Succesfully Registered,Upload Complete", Toast.LENGTH_SHORT).show();
                                finish();
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
        userAge = (EditText) findViewById(R.id.t_age);
        userProfilePic = (ImageView) findViewById(R.id.ivProfile);
    }

    private Boolean validate()
    {
        Boolean result = false;
        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty() && age.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }else
        {
            result = true;
        }

        return result;
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(Registration.this, "Succesfully Registered,Verification mail send!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Registration.this, MainActivity.class));
                    } else {
                        Toast.makeText(Registration.this, "Verification mail hasn'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile (age, email, name);
        myRef.setValue(userProfile);
    }

}