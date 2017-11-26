package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.Product;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rashmi on 11/25/2017.
 */

public class ListAdapterProduct extends RecyclerView.Adapter<ListAdapterProduct.MyViewHolder> {

    private Context context;
    private List<Product> mProductList;

    public ListAdapterProduct(Context context, List<Product> productList) {
        this.context = context;
        mProductList = productList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView prodName, prodDesc;
        private ImageView mImageView;
        private int mIndex;

        public MyViewHolder(View itemView) {
            super(itemView);
            prodName = (TextView) itemView.findViewById(R.id.textNameInCard);
            prodDesc = (TextView) itemView.findViewById(R.id.textDescInCard);
            mImageView = (ImageView) itemView.findViewById(R.id.imageInFeatured);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured, parent, false);
        return new ListAdapterProduct.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Product myItem = mProductList.get(position);
        holder.prodName.setText(mProductList.get(position).getProductName());
        holder.prodDesc.setText(mProductList.get(position).getDescription());
        /*Picasso.with(context)
                .load(mCategoryItemList.get(position).getCatagoryImage())
                .into(holder.mImageView);*/

        Glide.with(context)
                .load(mProductList.get(position).getImage())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}

