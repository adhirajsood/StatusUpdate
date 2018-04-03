package com.example.adhirajs.statusupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements ItemClickListener {

    RecyclerView rv_status;
    ArrayList<ProductStatusModel> productStatusModels;
    ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_status = findViewById(R.id.rv_status);
        fetchData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (productAdapter!=null){
            productAdapter.notifyDataSetChanged();
        }
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

                productStatusModels = response.body();
                setProductDetails(productStatusModels);
                productAdapter = new ProductAdapter(MainActivity.this,productStatusModels);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rv_status.setHasFixedSize(true);
                rv_status.setLayoutManager(layoutManager);
                rv_status.setAdapter(productAdapter);
                productAdapter.setClickListener(MainActivity.this);

            }

            @Override
            public void onFailure(Call<ArrayList<ProductStatusModel>> call, Throwable t) {

            }

        });

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this,ProductDetails.class);
        intent.putExtra("pos",position);
        startActivity(intent);
    }
}
