package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.CategoryItem;

import java.util.List;

/**
 * Created by rashmi on 11/24/2017.
 */

public class ListAdapterFeatured extends RecyclerView.Adapter {

    private Context context;
    private List<CategoryItem> mCategoryItemList;


    public ListAdapterFeatured(@NonNull Context context, int resource, @NonNull List objects) {
        this.context = context;
        this.mCategoryItemList = objects;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView,mTextViewDesc;
        private ImageView mImageView;
        private int mIndex;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.textNameInCard);
            mTextViewDesc=(TextView)itemView.findViewById(R.id.textDescInCard);

        }

        public void bindView(int position) {
            mIndex=position;
            final CategoryItem myItem = mCategoryItemList.get(position);
            mTextView.setText(mCategoryItemList.get(position).getCatagoryName());
            mTextViewDesc.setText(mCategoryItemList.get(position).getCatagoryDiscription());

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bindView(position);

        /*
        Picasso.with(context)
                .load(mCategoryItemList.get(position).getCatagoryImage())
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return mCategoryItemList.size();
    }
}
