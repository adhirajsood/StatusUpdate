package com.example.adhirajs.statusupdate.presenter;

import com.example.adhirajs.statusupdate.interfaces.IGetData;
import com.example.adhirajs.statusupdate.interfaces.IProductActivity;
import com.example.adhirajs.statusupdate.model.ProductStatusModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Presenter used for fetching the data
 */

public class ProductPresenter {
    IProductActivity mListener;
    public ProductPresenter(IProductActivity iProductActivity){
        this.mListener = iProductActivity;
    }

    public void fetchData(){
        mListener.showProgressBar();
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
                mListener.showDataLayout(response.body());
                mListener.hideProgressBar();
            }

            @Override
            public void onFailure(Call<ArrayList<ProductStatusModel>> call, Throwable t) {
                mListener.hideProgressBar();
            }

        });
    }
}
