package com.example.myapplicationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rogalabs.restfactory.annotations.Rest;
import com.rogalabs.restfactory.RestFactory;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Rest
    MockApi mockApi;

    @Rest( baseUrl = "http://pokeapi.co/api/v2/" )
    MockApi2 mockApi2;

    private TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RestFactory.make(this);

        response = (TextView) findViewById(R.id.response);


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mockApi.getAnyway()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1() {
                            @Override
                            public void call(Object o) {
                                response.setText(o.toString());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.d("Fail", "" + throwable);
                            }
                        });
            }
        });


        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mockApi2.getAnyway()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1() {
                            @Override
                            public void call(Object o) {
                                response.setText(o.toString());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.d("Fail", "" + throwable);
                            }
                        });
            }
        });


    }


}
