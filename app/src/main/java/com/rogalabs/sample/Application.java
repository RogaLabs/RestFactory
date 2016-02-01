package com.rogalabs.sample;


import com.rogalabs.apifactory.RestFactory;

/**
 * Created by roga on 21/01/16.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new RestFactory.Builder()
                .setBaseUrl("http://beta.json-generator.com/")
                .build(this);
    }
}