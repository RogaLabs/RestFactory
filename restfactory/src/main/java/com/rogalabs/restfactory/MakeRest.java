package com.rogalabs.restfactory;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by roga on 19/01/16.
 */
public abstract class MakeRest {

    public MakeRest() {}

    public static void load(Context context , Listener listener) {
        for (Field field : context.getClass().getDeclaredFields())
            if (field.isAnnotationPresent(Rest.class)) {
                listener.onLoad(loadFiedls(field));
            }
    }

    private static Field loadFiedls(Field field) {
        field.setAccessible(true);
        return field;
    }

    public interface Listener{
        void onLoad(Field field);
    }
}
