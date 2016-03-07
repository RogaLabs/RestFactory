package com.rogalabs.restfactory;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by roga on 19/01/16.
 */
public abstract class MakeRest {

    private static String anotherBaseUrl;

    public static void load(Object context, Listener listener) {
        for (Field field : context.getClass().getDeclaredFields())
            if (field.isAnnotationPresent(Rest.class)) {
                if (existsAnotherBaseUrl(field))
                    listener.onLoadWithBaseUrl(field, anotherBaseUrl);
                else
                    listener.onLoad(loadFiedls(field));

            }
    }

    private static boolean existsAnotherBaseUrl(Field field) {
        Class<? extends Annotation> annotation = extractAnnotation(field);
        try {
            if (anotherBaseUlrIsNotEmpty(annotation)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Class<? extends Annotation> extractAnnotation(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            Class<? extends Annotation> type = annotation.annotationType();
            return type;
        }
        return null;
    }

    private static boolean anotherBaseUlrIsNotEmpty(Class<? extends Annotation> annotation) throws Exception {
        for (Method method : annotation.getDeclaredMethods()) {
            Object value = method.invoke(annotation, new Object[0]);
            if (!TextUtils.isEmpty((String) value)) {
                anotherBaseUrl = (String) value;
                return true;
            }
        }
        return false;
    }


    private static Field loadFiedls(Field field) {
        field.setAccessible(true);
        return field;
    }

    public interface Listener {
        void onLoad(Field field);
        void onLoadWithBaseUrl(Field field, String anotherBaseUrl);
    }
}
