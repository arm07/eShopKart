package com.arm07.android.eshopkart.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.db.SQL;
import com.arm07.android.eshopkart.model.MyCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/28/2017.
 */

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.MyViewHolder> {

    private final ArrayList<MyCart> myCartArrayList;
    private final LayoutInflater layoutInflater;
    private final Context context;

    private SQL sqlHelper;
    private SQLiteDatabase db;
    PayAdapter myCartAdapter;

    public PayAdapter(Context context,ArrayList<MyCart> myCartArrayList) {
        this.myCartArrayList = myCartArrayList;
        layoutInflater = LayoutInflater.from(context);;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //final TextView Item_ID;
        TextView Item_name;
        TextView Item_quantity;
        TextView Item_price;
        ImageView image;
        //final Button add_item;
        //final Button subtract_item;
        //final TextView required_quantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            Item_name=(TextView)itemView.findViewById(R.id.textTitle);
            Item_price=(TextView)itemView.findViewById(R.id.prodPrice);
            Item_quantity=(TextView)itemView.findViewById(R.id.quantity);
            image=(ImageView)itemView.findViewById(R.id.thumbnail);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,null);
        //view.setOnClickListener(this);

        sqlHelper = new SQL(context);
        db = sqlHelper.getWritableDatabase();

        return new PayAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //holder.Item_ID.setText(myCartArrayList.get(position).ID);
        holder.Item_name.setText(myCartArrayList.get(position).ProductName);
        holder.Item_quantity.setText(myCartArrayList.get(position).Quantity);
        holder.Item_price.setText(myCartArrayList.get(position).Price);
        //holder.itemView.setTag(myCartArrayList.get(position).ID);
        Picasso.with(context)
                .load(myCartArrayList.get(position).Image)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return myCartArrayList.size();
    }

}
