package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.Order;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/29/2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private ArrayList<Order> orderArrayList;
    private final LayoutInflater layoutInflater;

    public OrderAdapter(Context context, ArrayList<Order> orderArrayList) {
        layoutInflater = LayoutInflater.from(context);
        this.orderArrayList = orderArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        //view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(orderArrayList.get(position).OrderID);
        holder.name.setText(orderArrayList.get(position).ItemName);
        holder.quantity.setText(orderArrayList.get(position).ItemQuantity);
        holder.prize.setText(orderArrayList.get(position).FinalPrice);
        holder.status.setText(orderArrayList.get(position).OrderStatus);
        holder.itemView.setTag(orderArrayList.get(position).OrderID);
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        final TextView id;
        final TextView name;
        final TextView quantity;
        final TextView status;
        final TextView prize;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.id =  itemView.findViewById(R.id.order_id);
            this.name = itemView.findViewById(R.id.order_name);
            this.quantity =  itemView.findViewById(R.id.order_quantity);
            this.prize =  itemView.findViewById(R.id.order_prize);
            this.status =  itemView.findViewById(R.id.order_status);
        }
    }

    public void notifyData(ArrayList<Order> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.orderArrayList = myList;
        notifyDataSetChanged();
    }
}
