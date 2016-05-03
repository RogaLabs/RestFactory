package com.example.myapplicationtest;

import android.app.Application;

import com.rogalabs.restfactory.RestFactory;

/**
 * Created by roga on 07/03/16.
 */
public class ApplicationApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new RestFactory.Builder()
                .setBaseUrl("http://swapi.co/api/")
                .build();
    }
}
