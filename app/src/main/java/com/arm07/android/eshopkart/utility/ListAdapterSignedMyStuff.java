package com.arm07.android.eshopkart.utility;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.MyStuffSignedFragment;
import com.arm07.android.eshopkart.model.MyStuffSigned;

/**
 * Created by rashmi on 11/27/2017.
 */

public class ListAdapterSignedMyStuff extends RecyclerView.Adapter {

    private static MyStuffSignedFragment.ListMystuffSignedSelected mListener;

    public ListAdapterSignedMyStuff(MyStuffSignedFragment.ListMystuffSignedSelected listener) {
        mListener=listener;
    }

    private static class ListViewHolderSigned extends ViewHolder implements View.OnClickListener{
        TextView mTextView;
        ImageView mImageIcon,mImageArrow;
        int mIndex;


        public ListViewHolderSigned(View itemView) {
            super(itemView);
            mImageIcon=(ImageView)itemView.findViewById(R.id.itemImageIcon);
            mTextView=(TextView)itemView.findViewById(R.id.itemText);
            mImageArrow=(ImageView)itemView.findViewById(R.id.itemImageArrow);

            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            mIndex=position;
            mTextView.setText(MyStuffSigned.names[position]);
            mImageIcon.setImageResource(MyStuffSigned.namesImageIds[position]);
        }

        @Override
        public void onClick(View v) {
            mListener.onListMyStuffSelected(mIndex);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mystuff,parent,false);
        return new ListViewHolderSigned(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ListViewHolderSigned)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return MyStuffSigned.names.length;
    }

}


