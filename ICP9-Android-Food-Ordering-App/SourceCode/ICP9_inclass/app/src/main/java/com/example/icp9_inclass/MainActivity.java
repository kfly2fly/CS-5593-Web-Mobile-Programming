package com.example.icp9_inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    String crust;
    String sauce;
    ArrayList<String> toppings = new ArrayList<String>();
    EditText comments;
    EditText email;

    Button order;
    Button summary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comments = findViewById(R.id.user_input);
        order = findViewById(R.id.order);
        summary = findViewById(R.id.summary);
        email = findViewById(R.id.email);

        //Onclick for Summary Button
        summary.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View V) {
                                           //create intent object that will take the user to summary
                                           Intent intent = new Intent(MainActivity.this, Summary.class);
                                           Toast.makeText(MainActivity.this, "Taking you to your order summary", Toast.LENGTH_SHORT).show();
                                           intent.putExtra("crust", crust);
                                           intent.putExtra("sauce", sauce);
                                           intent.putExtra("toppings", toppings);
                                           intent.putExtra("comments", comments.getText().toString());
                                           startActivity(intent);

                                       }
                                   }
        );
        //OnClick for Order Button
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                sumbitOrder(view);






            }
        });
    }


    //method called when a crust is selected
    public void crustSelect(View view) {
        //is button selected
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.original_crust:
                if (checked)
                    crust = "Original Crust";
                break;
            case R.id.thin_crust:
                if (checked)
                    crust = "Thin Crust";
                break;
            case R.id.pan_crust:
                if (checked)
                    crust = "Pan Crust";
                break;
        }
    }


    //method called when a crust is selected
    public void sauceSelect(View view) {
        //is button selected
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.marinara_sauce:
                if (checked)
                    sauce = "Marinara Sauce";
                break;
            case R.id.alfredo_sauce:
                if (checked)
                    sauce = "Alfredo Sauce";
                break;
        }
    }

    //method called when a crust is selected
    public void toppingSelect(View view) {
        //is button selected
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.meat:
                if (checked)
                    toppings.add("Meat");
                else
                    toppings.remove("Meat");
                break;
            case R.id.cheese:
                if (checked)
                    toppings.add("Cheese");
                else
                    toppings.remove("Cheese");
                break;
            case R.id.veggies:
                if (checked)
                    toppings.add("Veggies");
                else
                    toppings.remove("Veggies");
                break;
        }
    }

    //This method displays the given quantity value on the screen.
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    //This method increments the quantity of pizzas by one
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than one hundred pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    //This method decrements the quantity of pizzas by one
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select at least one pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    private boolean isUserEmpty(){
        // Checking If email is present or not
        String address = email.getText().toString();
        if(address == null || address.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = "Email is Required";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }


    //Summary Order to be sent through email
    public void sumbitOrder(View view){

        if (!isUserEmpty()) {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                crust = extras.getString("crust");
                sauce = extras.getString("sauce");
                toppings = extras.getStringArrayList("toppings");


            }

            String OS = "Pizza Crust: "
                    + crust +"  \n " +

                    "Pizza Sauce:  "
                    + sauce + "   \n" +

                    "Pizza Toppings:  "
                    + toppings + "  \n" +
                    "Enjoy the Meal 😋";

            String to = email.getText().toString();
            String commentTxt = comments.getText().toString();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setType("plain/text");
            // Recipients
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
            // Adding Subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            // Adding the Order Summary Text
            emailIntent.putExtra( Intent.EXTRA_TEXT, OS);
            // Redirecting to Email Intent
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }

}
