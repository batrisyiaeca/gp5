package com.example.group5;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Attempts;
    private TextView Register;

    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name     = (EditText)findViewById(R.id.t_name);
        Password = (EditText)findViewById(R.id.t_password);
        Attempts = (TextView)findViewById(R.id.t_attempts);
        Login    = (Button)findViewById(R.id.btn_Login);
        Register = (TextView)findViewById(R.id.t_signup);

        Attempts.setText("No of attempts remaining: 3");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword)
    {
        if ((userName.equals("admin")) && (userPassword.equals("123"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else {
            counter--;
            Attempts.setText("No of attempts remaining: " + String.valueOf(counter));
            if (counter == 0)
            {
                Login.setEnabled(false);
            }
        }
    }
}