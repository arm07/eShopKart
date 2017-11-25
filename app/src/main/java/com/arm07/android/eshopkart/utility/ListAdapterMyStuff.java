package com.arm07.android.eshopkart.utility;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.MyStuff;

/**
 * Created by rashmi on 11/23/2017.
 */

public class ListAdapterMyStuff extends RecyclerView.Adapter {

    private class ListViewHolder extends ViewHolder{
        TextView mTextView;
        ImageView mImageIcon,mImageArrow;
        int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mImageIcon=(ImageView)itemView.findViewById(R.id.itemImageIcon);
            mTextView=(TextView)itemView.findViewById(R.id.itemText);
            mImageArrow=(ImageView)itemView.findViewById(R.id.itemImageArrow);
        }
        public void bindView(int position){
            mIndex=position;
            mTextView.setText(MyStuff.names[position]);
            mImageIcon.setImageResource(MyStuff.namesImageIds[position]);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mystuff,parent,false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return MyStuff.names.length;
    }
}
