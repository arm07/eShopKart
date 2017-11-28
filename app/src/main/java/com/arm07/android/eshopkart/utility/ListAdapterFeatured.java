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
import com.arm07.android.eshopkart.fragment.FeaturedFragment;
import com.arm07.android.eshopkart.model.CategoryItem;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rashmi on 11/24/2017.
 */

public class ListAdapterFeatured extends RecyclerView.Adapter<ListAdapterFeatured.MyViewHolder> {

    private Context context;
    private List<CategoryItem> mCategoryItemList;
    private static FeaturedFragment.OnRecyclerViewItemClickListener mListener;

    public ListAdapterFeatured(@NonNull Context context, @NonNull List<CategoryItem> objects,FeaturedFragment.OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.mCategoryItemList = objects;
        mListener=listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView catagoryName, categoryDesc;
        private ImageView mImageView;
        private int mIndex;
        public MyViewHolder(View itemView) {
            super(itemView);
            catagoryName =(TextView)itemView.findViewById(R.id.textNameInCard);
            categoryDesc =(TextView)itemView.findViewById(R.id.textDescInCard);
            mImageView=(ImageView)itemView.findViewById(R.id.imageInFeatured);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            //Toast.makeText(context, "Message-featured listitem clicked", Toast.LENGTH_LONG).show();
            //Toast.makeText(context, "clicked!! "+getPosition(), Toast.LENGTH_SHORT).show();
            if(mListener!=null)
                mListener.onItemClick(view,mCategoryItemList.get(mIndex).getId());
        }
        public void bindView(int position) {
            mIndex=position;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final CategoryItem myItem = mCategoryItemList.get(position);
        holder.catagoryName.setText(mCategoryItemList.get(position).getCatagoryName());
        holder.categoryDesc.setText(mCategoryItemList.get(position).getCatagoryDiscription());
        /*Picasso.with(context)
                .load(mCategoryItemList.get(position).getCatagoryImage())
                .into(holder.mImageView);*/

        Glide.with(context)
                .load(mCategoryItemList.get(position).getCatagoryImage())
                .into(holder.mImageView);
        ((MyViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mCategoryItemList.size();
    }
}
