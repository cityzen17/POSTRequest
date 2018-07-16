package com.example.cityzen10.postrequest.api.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityzen10.postrequest.R;
import com.example.cityzen10.postrequest.api.model.User;
import com.example.cityzen10.postrequest.api.service.UserClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText=findViewById(R.id.name);
        final EditText editText1=findViewById(R.id.job);
        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(
                        editText.getText().toString(),
                        editText1.getText().toString()
                );
                sendNetworkRequest(user);

        };
    });
}

    private void sendNetworkRequest(User user) {
        /*OkHttpClient.Builder okhttpclient=new OkHttpClient.Builder();
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpclient.addInterceptor(logging);*/

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/users/")
                .addConverterFactory(GsonConverterFactory.create());
/*
                .client(o.build());
*/
        Retrofit retrofit=builder.build();

        //get client and call object of request
        UserClient userClient=retrofit.create(UserClient.class);
        Call<User> call=userClient.createAccount(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG,"response code"+response.code());
                if (response.code() == 200) {

                    Toast.makeText(MainActivity.this, "sUBMITTED ID:" + response.body().getId(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"sth wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}