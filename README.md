#RestFactory
[![](https://jitpack.io/v/RogaLabs/RestFactory.svg)](https://jitpack.io/#RogaLabs/RestFactory)

RestFactory is a simplifier for HTTP requests , the main objective is remove retrofit setup of your application for a library.

## Usage

in your application file

```java
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new RestFactory.Builder()
                .setBaseUrl(yourendpoint)
                .build();
    }
}
```

and define your application name and internet permission on manifest file

```
...
<uses-permission android:name="android.permission.INTERNET" />
...
<application
        android:name=".Application"
...
```






after , using interfaces for define your calls (the library is not used here) , 
this interface uses retrofit annotations and types

```java
public interface MockApi {

    @GET("api/json/get/anything")
    Call<JsonElement> getAnything();
}

```

now , we'll create the activity

```java

public class MainActivity extends AppCompatActivity {

    @Rest
    MockApi mockApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RestFactory.make(this);

        mockApi.getAnything().enqueue(...);
    }
}
```

Note two things , the annotation ```@Rest``` and call method ```RestFactory.make(this)``` , done that __mockApi__ you can now use your calls.


## Optional

This lib uses for default this format date ```yyyy-MM-dd HH:mm:ss``` , if you want to use other formats:

```java
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new RestFactory.Builder()
                .setBaseUrl(yourendpoint)
                .addDateFormat(your date pattern or list of date patterns)
                .build();
    }
}
```


## Install

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

and add dependency:

```
	dependencies {
	        compile 'com.github.RogaLabs.RestFactory:restfactory:{latestversion}'
	}
```



