package com.example.myapplicationtest;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import rx.Observable;

public interface MockApi2 {

    @GET("pokemon/1")
    Observable<JsonElement> getAnyway();
}
