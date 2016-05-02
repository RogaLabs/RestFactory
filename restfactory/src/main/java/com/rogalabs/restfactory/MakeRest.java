package com.rogalabs.restfactory;

import com.rogalabs.restfactory.annotations.Rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by roga on 19/01/16.
 */
public abstract class MakeRest {

    private static String anotherBaseUrl;

    static void load(Object context, Listener listener) {
        for (Field field : context.getClass().getDeclaredFields())
            if (field.isAnnotationPresent(Rest.class)) {
                if (existsAnotherBaseUrl(field))
                    listener.onLoadWithBaseUrl(openAccessForField(field), anotherBaseUrl);
                else
                    listener.onLoad(openAccessForField(field));

            }
    }

    private static boolean existsAnotherBaseUrl(Field field) {
        Rest annotation = (Rest) extractAnnotation(field);
        if (annotation.baseUrl().isEmpty())
            return false;

        setAnotherBaseUrl(annotation.baseUrl());
        return true;
    }

    private static Annotation extractAnnotation(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            return annotation;
        }
        return null;
    }

    private static void setAnotherBaseUrl(String baseUrl){
        anotherBaseUrl = baseUrl;
    }

    private static Field openAccessForField(Field field) {
        field.setAccessible(true);
        return field;
    }

    interface Listener {
        void onLoad(Field field);
        void onLoadWithBaseUrl(Field field, String anotherBaseUrl);
    }
}
