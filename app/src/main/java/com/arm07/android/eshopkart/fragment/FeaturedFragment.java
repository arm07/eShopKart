package com.arm07.android.eshopkart.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.CategoryItem;
import com.arm07.android.eshopkart.utility.ListAdapterFeatured;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/24/2017.
 */

public class FeaturedFragment extends Fragment {

    RecyclerView recyclerView;
    ListAdapterFeatured adapter;
    ArrayList<CategoryItem> mylistItems;
    RecyclerView.LayoutManager layoutManager;
    CategoryItem ct;
    String id;
    private int currentId;
    private SharedPreferences spref;


    private final String TAG = "Featured_Fragment";

    private String url;// = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=1fa9fde8966420a223ea80bf99b8a771&user_id=917";
   private static final String tmpUrl="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?";
    String apiKey,userId,urlExt;
    OnRecyclerViewItemClickListener listener;
    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view,String id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        listener=(OnRecyclerViewItemClickListener)getActivity();

        View view=inflater.inflate(R.layout.fragment_featured,container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewFeatured);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        Bundle bundle=getArguments();
        if(bundle!=null) {
            apiKey = bundle.getString("api_key");
            userId = bundle.getString("user_id");
            url=new String(tmpUrl+"api_key="+apiKey+"&user_id="+userId);
        }
        else{
            url="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=1fa9fde8966420a223ea80bf99b8a771&user_id=917";
        }

        Toast.makeText(getActivity(),"category url"+url, Toast.LENGTH_LONG).show();
        mylistItems = new ArrayList<>();
        loadRecylerView();
        return view;
    }

    private void loadRecylerView() {

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("MYTEST_RESPONSE",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("Category");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject itemCategory = categories.getJSONObject(i);
                        String id=itemCategory.getString("Id");
                        String catagoryName=itemCategory.getString("CatagoryName");
                        String catagoryDiscription=itemCategory.getString("CatagoryDiscription");
                        String catagoryImage=itemCategory.getString("CatagoryImage");
                        CategoryItem categoryItem=new CategoryItem(id,catagoryName,catagoryDiscription,catagoryImage);
                        mylistItems.add(categoryItem);
                    }

                    adapter = new ListAdapterFeatured(getActivity(), mylistItems,listener);
                    recyclerView.setAdapter(adapter);

                    Log.i("MYTEST_RESPONSE_Finished",response);
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


