package com.example.adhirajs.statusupdate;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by adhirajs on 3/4/18.
 */

public class BaseActivity extends AppCompatActivity {
    ArrayList<ProductStatusModel> productStatusModels;

    public ArrayList<ProductStatusModel> getProductDetails() {
        return productStatusModels;
    }

    public void setProductDetails(ArrayList<ProductStatusModel> productStatusModels) {
        this.productStatusModels = productStatusModels;
    }
}
