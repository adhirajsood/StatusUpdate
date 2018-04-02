package com.example.adhirajs.statusupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
    }


    public void fetchData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IGetData iGetData = retrofit.create(IGetData.class);


        Call<ArrayList<ProductStatusModel>> userArrayListCall = iGetData.getList();
        userArrayListCall.enqueue(new Callback<ArrayList<ProductStatusModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductStatusModel>> call, Response<ArrayList<ProductStatusModel>> response) {

                ArrayList<ProductStatusModel> productStatusModels = response.body();

            }

            @Override
            public void onFailure(Call<ArrayList<ProductStatusModel>> call, Throwable t) {

            }
        });

    }
}
