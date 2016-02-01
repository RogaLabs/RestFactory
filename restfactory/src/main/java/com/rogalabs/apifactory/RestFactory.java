package com.rogalabs.apifactory;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rogalabs.apifactory.defaults.LogInterceptor;
import com.rogalabs.apifactory.parser.DateSerializer;

import java.lang.reflect.Field;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by roga on 19/01/16.
 */
public class RestFactory extends MakeRest {

    private static String baseUrl;
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public RestFactory(Context context) {
        super();
    }

    public RestFactory(Context context, String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

    private static <T> T create(Class<T> endpoint, String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build();
        Gson gson = buildGson();
        Retrofit retrofit = buildRetroFit(client, gson, baseUrl);
        return retrofit.create(endpoint);
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer(DEFAULT_DATE_FORMAT))
                .create();
    }

    private static Retrofit buildRetroFit(OkHttpClient client, Gson gson, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static final class Builder {
        private String baseUrl;

        public Builder setBaseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public RestFactory build(Context context) {
            return new RestFactory(context, baseUrl);
        }
    }


    public static void make(final Context context) {
        load(context, new Listener() {
            @Override
            public void onLoad(Field field) {
                try {
                    field.set(context, create(field.getType(), baseUrl));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
