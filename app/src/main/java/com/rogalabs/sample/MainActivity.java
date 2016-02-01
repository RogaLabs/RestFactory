package com.rogalabs.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.JsonElement;
import com.rogalabs.apifactory.Rest;
import com.rogalabs.apifactory.RestFactory;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Rest
    MockApi mockApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RestFactory.make(this);


        mockApi.getX().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                Log.d("SUCCESS", response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("FAIL", t.getMessage()
                );
            }
        });


    }
}
