package com.example.adhirajs.statusupdate.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.adhirajs.statusupdate.R;
import com.example.adhirajs.statusupdate.interfaces.IProductActivity;
import com.example.adhirajs.statusupdate.interfaces.ItemClickListener;
import com.example.adhirajs.statusupdate.model.ProductStatusModel;
import com.example.adhirajs.statusupdate.presenter.ProductPresenter;

import java.util.ArrayList;


/*MainActivity.Class
* Displays the list of product in recycler view
* Implementation:- ItemClickListener callback event of item click in recycler view
* IProductActivity callback event to communicate with the presenter*/
public class MainActivity extends AppCompatActivity implements ItemClickListener,IProductActivity {

    RecyclerView rv_status;
    ArrayList<ProductStatusModel> productStatusModels;
    ProductAdapter productAdapter;
    int positionSelected;
    ProductPresenter mPresenter;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_status = findViewById(R.id.rv_status);
        mPresenter = new ProductPresenter(this);
        mPresenter.fetchData();
    }


    /*Click for item in recycler view*/
    @Override
    public void onClick(int position) {
        positionSelected = position;
        Intent intent = new Intent(MainActivity.this,ProductDetails.class);
        intent.putExtra("model",productStatusModels.get(position));
        startActivityForResult(intent,1);
    }


    /*Result of the ProductDetails activity to update the item in recycler view*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
               productStatusModels.set(positionSelected,(ProductStatusModel) data.getSerializableExtra("model"));
               productAdapter.notifyItemChanged(positionSelected);
               break;
            default:
                break;

        }
    }

    /*Progress bar to be displayed when api call is initiated*/
    @Override
    public void showProgressBar() {
        progressDialog = new Dialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.dialog_progressbar);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();
    }

    /*Hide the progress bar post api has provided the result*/
    @Override
    public void hideProgressBar() {
        progressDialog.hide();
    }

    /*Method where we receive the response from API*/
    @Override
    public void showDataLayout(ArrayList<ProductStatusModel> res) {
        productStatusModels = res;
        prepareList();
    }

    /*Preparing the list which contains
    * 1.Initiating the adapter
    * 2. Setting orientation to recycler view
    * 3. setting click listener*/
    public void prepareList(){
        productAdapter = new ProductAdapter(MainActivity.this,productStatusModels);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_status.setHasFixedSize(true);
        rv_status.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this,
                layoutManager.getOrientation());
        rv_status.addItemDecoration(dividerItemDecoration);
        rv_status.setAdapter(productAdapter);
        productAdapter.setClickListener(MainActivity.this);
    }
}
