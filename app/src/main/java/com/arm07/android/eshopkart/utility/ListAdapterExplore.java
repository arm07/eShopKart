package com.arm07.android.eshopkart.utility;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ExploreFragment;
import com.arm07.android.eshopkart.model.Explore;

/**
 * Created by rashmi on 11/25/2017.
 */

public class ListAdapterExplore extends RecyclerView.Adapter {

    private static ExploreFragment.ListExploreSelected mListener;
    public ListAdapterExplore(ExploreFragment.ListExploreSelected listener) {
        mListener=listener;
    }

    public static class ListViewHolderGoods extends RecyclerView.ViewHolder implements View.OnClickListener{
        //@BindView(R.id.itemInGoods)
        TextView textInGoods;
        //@BindView(R.id.itemImageDownArrow)
        ImageView mImageDown;
        int mIndex;
        public ListViewHolderGoods(View itemView) {
            super(itemView);
            textInGoods=(TextView)itemView.findViewById(R.id.itemInGoods);
            mImageDown=(ImageView)itemView.findViewById(R.id.itemImageDownArrow);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex=position;
            textInGoods.setText(Explore.goods[position]);
           //mImageDown.setImageResource(Explore.imageDown[position]);
        }

        @Override
        public void onClick(View v) {
            mListener.onListExploreSelected(mIndex,Explore.goodsIds[mIndex]);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_goods,parent,false);
        return new ListViewHolderGoods(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       // final Explore exploreItem;
        ((ListViewHolderGoods)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Explore.goods.length;
    }
}
