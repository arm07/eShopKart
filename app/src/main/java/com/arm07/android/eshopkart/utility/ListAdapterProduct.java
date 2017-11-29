package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ProductFragment;
import com.arm07.android.eshopkart.model.Product;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rashmi on 11/25/2017.
 */

public class ListAdapterProduct extends RecyclerView.Adapter<ListAdapterProduct.MyViewHolder> {

    private Context context;
    private List<Product> mProductList;

    ProductFragment.OnProductClickListener mListener;
    public ListAdapterProduct(Context context, List<Product> productList, ProductFragment.OnProductClickListener mListener) {
        this.context = context;
        mProductList = productList;
        this.mListener=mListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView prodName, prodDesc,prodQuant,prodPrice;
        private ImageView mImageView;
        private int mIndex;

        public MyViewHolder(View itemView) {
            super(itemView);
            prodName = (TextView) itemView.findViewById(R.id.prodtextNameInCard);
            prodQuant=(TextView)itemView.findViewById(R.id.prodtextQuantInCard);
            prodPrice=(TextView)itemView.findViewById(R.id.prodtextPriceInCard);
            prodDesc = (TextView) itemView.findViewById(R.id.prodtextDescInCard);
            mImageView = (ImageView) itemView.findViewById(R.id.prodImageInCard);

            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex=position;
        }
        @Override
        public void onClick(View v) {
            mListener.onProdItemClicked(v,mIndex,mProductList.get(mIndex).getID(),
                    mProductList.get(mIndex).getProductName(),
                    mProductList.get(mIndex).getQuantity(),
                    mProductList.get(mIndex).getPrice(),
                    mProductList.get(mIndex).getDescription(),
                    mProductList.get(mIndex).getImage());
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ListAdapterProduct.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Product myItem = mProductList.get(position);
        holder.prodName.setText(mProductList.get(position).getProductName());
        holder.prodQuant.setText(mProductList.get(position).getQuantity());
        holder.prodPrice.setText(mProductList.get(position).getPrice());
        holder.prodDesc.setText(mProductList.get(position).getDescription());
        /*Picasso.with(context)
                .load(mCategoryItemList.get(position).getCatagoryImage())
                .into(holder.mImageView);*/

        Glide.with(context)
                .load(mProductList.get(position).getImage())
                .into(holder.mImageView);
        ((MyViewHolder)holder).bindView(position);
    }
    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}

