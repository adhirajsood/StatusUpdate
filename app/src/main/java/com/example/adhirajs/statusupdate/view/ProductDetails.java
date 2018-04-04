package com.example.adhirajs.statusupdate.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adhirajs.statusupdate.R;
import com.example.adhirajs.statusupdate.model.ProductStatusModel;

/**
 * Activity to display the product details for the item clicked
 *
 */

public class ProductDetails extends AppCompatActivity{

    ProductStatusModel productStatusModel;
    Button btn_complete;
    TextView tv_desc,tv_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productStatusModel = (ProductStatusModel) super.getIntent().getSerializableExtra("model");
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_status = (TextView) findViewById(R.id.tv_status);
        btn_complete = (Button) findViewById(R.id.btn_complete);

        tv_desc.setText(productStatusModel.getTitle());
        /*mark complete to be displayed only if the order is pending */
        if (productStatusModel.getCompleted()){
            btn_complete.setVisibility(View.GONE);
            tv_status.setText("Order Placed");
        }

    }

    /*Mark complete button click for updating the status from pending to placed*/
    public void markComplete(View view) {
        if (!productStatusModel.getCompleted()) {
            productStatusModel.setCompleted(true);
            btn_complete.setVisibility(View.GONE);
            tv_status.setText("Order Placed");
            Toast.makeText(this,"Ordered placed successfully!!",Toast.LENGTH_SHORT).show();
        }
    }

    /*Set result on back press*/
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("model",productStatusModel);
        setResult(RESULT_OK,intent);
        finish();
    }
}
