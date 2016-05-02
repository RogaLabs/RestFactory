package com.example.myapplicationtest;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import rx.Observable;

public interface MockApi{

    @GET("people/1")
    Observable<JsonElement> getAnyway();
}
