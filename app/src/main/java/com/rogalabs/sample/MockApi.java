package com.rogalabs.sample;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by roga on 20/01/16.
 */
public interface MockApi {

    @GET("api/json/get/4yWHpsdug")
    Call<JsonElement> getX();
}
