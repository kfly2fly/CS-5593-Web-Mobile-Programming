package com.example.icp8_inclass;

//import dependencies
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    //define a button object
    Button btn;

    //define a constructor method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //tie btn to the UI button
        btn = findViewById(R.id.button);

        //when a user clicks on the sign out button, this method will return the user to the sign in page
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //create intent object that will return the user to MainActivity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                //show the user some text
                Toast.makeText(MainActivity2.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                //start the activity
                startActivity(intent);

            }
        })
    ;}
}