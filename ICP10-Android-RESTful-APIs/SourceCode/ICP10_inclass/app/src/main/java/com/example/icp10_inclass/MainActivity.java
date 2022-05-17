package com.example.icp10_inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCollections apiCollections = retrofit.create(ApiCollections.class);

        Call<List<user>> usersCall = apiCollections.getData();

        usersCall.enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {
                if (response.isSuccessful()) {
                    List<user> users = response.body();
                    for (user user: users) {
                        String data = "";
                        data += "ID: " + user.getId() + "\n";
                        data += "Username: " + user.getUsername() + "\n\n";

                        textView.append(data);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Data Failed", Toast.LENGTH_SHORT);
            }
        });
    }
}