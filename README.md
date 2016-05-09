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
    
    //starting 0.5.0 version is possible define other base URL for your interface
    @Rest( baseUrl = "other base url") 
    MockApi2 mockApi2;

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
	        compile 'com.github.RogaLabs:RestFactory:{latestversion}'
	}
```

## Credits

[Bruno Lima](https://github.com/brunodles)

Many influences on structuring of this file `RestFactory.class` it was this guy.


##License
```
The MIT License (MIT)

Copyright (c) 2015 Roga Labs

Permission is hereby granted, free of charge, to any person obtaining a 
copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, including without limitation 
the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included 
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```




