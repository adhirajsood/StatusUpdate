package com.example.adhirajs.statusupdate.interfaces;

import com.example.adhirajs.statusupdate.model.ProductStatusModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by adhirajs on 2/4/18.
 */

public interface IGetData {

    @GET("todos")
    Call<ArrayList<ProductStatusModel>> getList();

}
