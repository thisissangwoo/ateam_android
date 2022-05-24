package com.example.anafor.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v2/local/search/keyword.json")
    Call<ResultSearchKeyword> getSearchLocationDetail(
            @Header("Authorization") String token,
            @Query("query") String query,
            @Query("x") String x,
            @Query("y") String y,
            @Query("size") int size
    );
}
