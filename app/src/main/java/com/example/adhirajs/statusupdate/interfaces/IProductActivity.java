package com.example.adhirajs.statusupdate.interfaces;

import com.example.adhirajs.statusupdate.model.ProductStatusModel;

import java.util.ArrayList;

/**
 * Created by adhirajs on 3/4/18.
 */

public interface IProductActivity {

    void showProgressBar();

    void hideProgressBar();

    void showDataLayout(ArrayList<ProductStatusModel> productStatusModels);
}
