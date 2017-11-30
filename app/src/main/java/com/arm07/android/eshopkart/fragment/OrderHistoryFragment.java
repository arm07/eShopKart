package com.arm07.android.eshopkart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.CategoryItem;
import com.arm07.android.eshopkart.model.Order;
import com.arm07.android.eshopkart.utility.ListAdapterFeatured;
import com.arm07.android.eshopkart.utility.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/29/2017.
 */

public class OrderHistoryFragment extends Fragment{

    RecyclerView recyclerView;
    ListAdapterFeatured adapter;
    ArrayList<Order> orderArrayList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    private OrderAdapter orderAdapter;
    CategoryItem ct;
    String id;
    private static final String tmpUrl="http://rjtmobile.com/ansari/shopingcart/androidapp/order_history.php?&mobile=";
    String apiKey,userId,phone;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_order_history,container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewFeatured);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        Bundle bundle=getArguments();
        if(bundle!=null) {
            apiKey = bundle.getString("api_key");
            userId = bundle.getString("user_id");
            phone=bundle.getString("input_phone");
            if(apiKey!=null)
                url=new String(tmpUrl+phone+"&api_key="+apiKey+"&user_id="+userId);
            else {
                Toast.makeText(getActivity(), "No valid account exists", Toast.LENGTH_LONG).show();
                url="http://rjtmobile.com/ansari/shopingcart/androidapp/order_history.php?&mobile=9849985918&api_key=1fa9fde8966420a223ea80bf99b8a771&user_id=917";
            }
            //url=new String(tmpUrl+subCategoryId+urlExt)
        }
        
        retrieveHistory(url);


        orderAdapter = new OrderAdapter(getActivity(), orderArrayList);
        recyclerView.setAdapter(orderAdapter);
        return view;
    }

    private void retrieveHistory(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject historyObj = new JSONObject(response);
                            JSONArray ordersJsonArray = historyObj.getJSONArray("Order History");

                            for (int i = 0; i < ordersJsonArray.length(); i++) {
                                JSONObject c = ordersJsonArray.getJSONObject(i);
                                String ID = c.getString("OrderID");

                                String ProductName = c.getString("ItemName");
                                String Quantity = c.getString("ItemQuantity");
                                String Prize = c.getString("FinalPrice");

                                if (c.getString("OrderStatus").equals("1")){
                                    String Status = "Order  Confirm";
                                    final Order order = new Order(ID, ProductName, Quantity, Prize, Status);
                                    orderArrayList.add(order);
                                    orderAdapter.notifyData(orderArrayList);
                                }
                                else if (c.getString("OrderStatus").equals("2")){
                                    String Status = "Order Dispatch";
                                    final Order cty = new Order(ID, ProductName, Quantity, Prize, Status);
                                    orderArrayList.add(cty);
                                    orderAdapter.notifyData(orderArrayList);
                                }
                                else if (c.getString("OrderStatus").equals("3")){
                                    String Status = "Order On the Way";
                                    final Order cty = new Order(ID, ProductName, Quantity, Prize, Status);
                                    orderArrayList.add(cty);
                                    orderAdapter.notifyData(orderArrayList);
                                }
                                else {
                                    String Status = "Order Delivered";
                                    final Order cty = new Order(ID, ProductName, Quantity, Prize, Status);
                                    orderArrayList.add(cty);
                                    orderAdapter.notifyData(orderArrayList);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
        };
    }
}
