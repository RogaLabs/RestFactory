#RestFactory

RestFactory is an simplifier for HTTP requests , the main objectiv is remove retrofit setup of your application for an library.

## Usage

in your application file

```java
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new ApiFactory.Builder()
                .setBaseUrl(yourendpoint)
                .build(this);
    }
}
```

after , using interfaces for define your calls (the library is not used here) , 
this interface uses retrofit annotations and types

```java
public interface MockApi {

    @GET("api/json/get/anything")
    Call<JsonElement> getAnything();
}

```

now , we create the activity 

```java

public class MainActivity extends AppCompatActivity {

    @Rest
    MockApi mockApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiFactory.make(this);

        mockApi.getAnything().enqueue(...);
    }
}
```

Note two things , the annotation @Rest and call method ApiFactory.make(this) , done that __mockApi__ you can now use your calls.




