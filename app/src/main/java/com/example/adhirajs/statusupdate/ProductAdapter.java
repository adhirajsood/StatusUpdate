package com.example.adhirajs.statusupdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adhirajs on 2/4/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductStatusModel> mResultList;
    private ItemClickListener clickListener;

    public ProductAdapter(Context context, ArrayList<ProductStatusModel> resultList) {
        mContext = context;
        mResultList = resultList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_status.setText("" + mResultList.get(i).getCompleted());
        viewHolder.tv_desc.setText("" + mResultList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_desc,tv_status;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_desc  = itemView.findViewById(R.id.tv_desc);
            tv_status  = itemView.findViewById(R.id.tv_status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(getAdapterPosition());

        }
    }
}