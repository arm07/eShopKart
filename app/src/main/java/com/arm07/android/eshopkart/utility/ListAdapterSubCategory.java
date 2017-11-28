package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.SubCategoryFragment;
import com.arm07.android.eshopkart.model.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rashmi on 11/27/2017.
 */

public class ListAdapterSubCategory extends RecyclerView.Adapter<ListAdapterSubCategory.MyViewHolder> {

    private Context context;
    private List<SubCategory> subcategoryList;
    private static SubCategoryFragment.OnSubCategoryItemClickListener listener;

    public ListAdapterSubCategory(Context context, List<SubCategory> subcategoryList,SubCategoryFragment.OnSubCategoryItemClickListener mListener) {
        this.context = context;
        this.subcategoryList = subcategoryList;
        listener=mListener;
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView catagoryName, categoryDesc;
        private ImageView mImageView;
        private int mIndex;

        public MyViewHolder(View itemView) {
            super(itemView);
            catagoryName = (TextView) itemView.findViewById(R.id.textNameInCardSub);
            categoryDesc = (TextView) itemView.findViewById(R.id.textDescInCardSub);
            mImageView = (ImageView) itemView.findViewById(R.id.imageInSubCategory);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(listener!=null)
                listener.onItemSubClick(v,subcategoryList.get(mIndex).getID());
        }
        public void bindView(int position) {mIndex=position;}
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.catagoryName.setText(subcategoryList.get(position).SubCatagoryName);
        holder.categoryDesc.setText(subcategoryList.get(position).SubCatagoryDiscription);
        Picasso.with(context)
                .load(subcategoryList.get(position).CatagoryImage)
                .into(holder.mImageView);
        ((MyViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }


}
