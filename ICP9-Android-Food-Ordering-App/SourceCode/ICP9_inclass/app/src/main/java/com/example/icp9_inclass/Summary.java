package com.example.icp9_inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    TextView crust_sum;
    TextView sauce_sum;
    TextView toppings_sum;
    TextView comments_sum;

    String crust = "";
    String sauce = "";
    ArrayList<String> toppings = new ArrayList<String>();
    String comments;

    Button order_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //Get variables from our MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            crust = extras.getString("crust");
            sauce = extras.getString("sauce");
            toppings = extras.getStringArrayList("toppings");
            comments = extras.getString("comments");
        }
        crust_sum = findViewById(R.id.crust_sum);
        crust_sum.setText(crust);

        sauce_sum = findViewById(R.id.sauce_sum);
        sauce_sum.setText(sauce);

        String top = "";
        for(String topping: toppings) {
            top += topping;
            top += "\n";
        }

        toppings_sum = findViewById(R.id.toppings_sum);
        toppings_sum.setText(top);

        comments_sum = findViewById(R.id.comments_sum);
        comments_sum.setText(comments);

        order_return = findViewById(R.id.order_return);
        order_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //create intent object that will take the user to MainActivity2
                Intent order_return = new Intent(Summary.this, MainActivity.class);
                Toast.makeText(Summary.this, "Taking you to your order", Toast.LENGTH_SHORT).show();
                startActivity(order_return);
            }
        });
    }
}