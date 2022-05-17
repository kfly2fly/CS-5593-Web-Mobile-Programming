package com.example.icp10_inclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCollections {

    @GET("users")
    Call<List<user>> getData();


}
