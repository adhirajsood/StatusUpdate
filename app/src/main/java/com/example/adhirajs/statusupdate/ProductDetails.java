package com.example.adhirajs.statusupdate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by adhirajs on 3/4/18.
 */

public class ProductDetails extends MainActivity{

    ArrayList<ProductStatusModel> productStatusModels;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productStatusModels = getProductDetails();
        position = super.getIntent().getIntExtra("pos",0);

    }

    public void markComplete(View view) {
       /* if (!productStatusModels.get(position).getCompleted())
        productStatusModels.get(position).setCompleted(true);*/
    }
}
