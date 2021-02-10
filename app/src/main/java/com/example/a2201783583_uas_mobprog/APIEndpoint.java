package com.example.a2201783583_uas_mobprog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndpoint {
    @GET("staging/book")
    Call<RawBooks> getRawBooks(@Query("nim") String nim, @Query("nama") String name);
}
