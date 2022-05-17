package com.example.icp8_inclass;
//import dependencies
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //initialize variables
    EditText username;
    Button btn;
    EditText password;


    //define a constructor method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set variables with values from the UI
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        btn = findViewById(R.id.btn);

        //when a user clicks on the sign in button, this method will take them to a logged in landing page
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //if information is entered correctly, sign the user in
                if(username.getText().toString().equals("user") && password.getText().toString().equals("password")) {
                    //create intent object that will take the user to MainActivity2
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    //tell the user that they are signed in
                    Toast.makeText(MainActivity.this, "You entered the right value", Toast.LENGTH_SHORT).show();
                }
                //if sign in information is wrong, flash the user a message
                else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        })
    ;}
}

