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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.model.SubCategory;
import com.arm07.android.eshopkart.utility.ListAdapterSubCategory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/27/2017.
 */

public class SubCategoryFragment extends Fragment {


    SubCategory[] subcategories;

    private final String TAG = "SubCategory_Fragment";
    private static final String tmpurl = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=";
    private static final String api_key="4c1dbea3d6cd43b13a036fcc684284f5";
    private static final String user_id="908";
    String urlExt="&api_key=1fa9fde8966420a223ea80bf99b8a771&user_id=917";
    private static String url;

    private SubCategory subcategory;

    private RecyclerView subcategoryRecyclerView;
    private ListAdapterSubCategory subcategoryAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SubCategory> subcategoryList;
    private int productId;
    private int subCategoryId;
   // private String subCategoryId;

    OnSubCategoryItemClickListener mListener;
    public interface OnSubCategoryItemClickListener {
        void onItemSubClick(View  view, String id);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mListener=(OnSubCategoryItemClickListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        //subCategoryId = getArguments().getString("categoryId");
        subCategoryId = getArguments().getInt("categoryId");
        url = new String(tmpurl + subCategoryId+urlExt);
        requestSubCategoryData();

        subcategoryRecyclerView = view.findViewById(R.id.recyclerViewSubCateg);
        subcategoryRecyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getActivity());
        subcategoryRecyclerView.setLayoutManager(layoutManager);
        subcategoryList = new ArrayList<>();
        return view;

    }

    private void requestSubCategoryData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, jsonObject.toString());

                try {
                    if (subcategoryList.isEmpty()) {
                        JSONArray contacts = jsonObject.getJSONArray("SubCategory");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String ID = c.getString("Id");
                            String SubCatagoryName = c.getString("SubCatagoryName");
                            String SubCatagoryDiscription = c.getString("SubCatagoryDiscription");
                            String CatagoryImage = c.getString("CatagoryImage");

                            subcategory = new SubCategory(ID,SubCatagoryName,SubCatagoryDiscription,CatagoryImage);
                            subcategoryList.add(subcategory);

                        }
                        subcategoryAdapter = new ListAdapterSubCategory(getContext(),subcategoryList,mListener);
                        subcategoryRecyclerView.setAdapter(subcategoryAdapter);
                    }
                } catch (Exception e) {
                    //noinspection ThrowablePrintedToSystemOut
                    System.out.println(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "ERROR" + volleyError.getMessage());
                Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
        //AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
