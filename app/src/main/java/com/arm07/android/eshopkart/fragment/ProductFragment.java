package com.arm07.android.eshopkart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.Product;
import com.arm07.android.eshopkart.utility.ListAdapterProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/25/2017.
 */

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    ListAdapterProduct adapter;
    ArrayList<Product> mylistItems;
    RecyclerView.LayoutManager layoutManager;

    private String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=205&api_key=4c1dbea3d6cd43b13a036fcc684284f5&user_id=908";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product,container,false);

        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewProduct);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        mylistItems = new ArrayList<>();
        loadRecylerView();
        return view;
    }


    private void loadRecylerView() {

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("MYTEST_RESPONSE_Product",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("Product");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject itemProduct = categories.getJSONObject(i);
                        String id=itemProduct.getString("Id");
                        String catagoryName=itemProduct.getString("ProductName");
                        String catagoryDiscription=itemProduct.getString("Discription");
                        String catagoryImage=itemProduct.getString("Image");
                        Product productItem=new Product(id,catagoryName,catagoryDiscription,catagoryImage);
                        mylistItems.add(productItem);
                    }

                    adapter = new ListAdapterProduct(getActivity(), mylistItems);
                    recyclerView.setAdapter(adapter);
                    Log.i("MYTEST_RESPONSE_Product_Finished",response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(sr);
    }


}
