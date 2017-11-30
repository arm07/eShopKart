package com.arm07.android.eshopkart.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.db.SQL;
import com.arm07.android.eshopkart.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.arm07.android.eshopkart.db.SQL.IMAGE;
import static com.arm07.android.eshopkart.db.SQL.KEY_ID;
import static com.arm07.android.eshopkart.db.SQL.NAME;
import static com.arm07.android.eshopkart.db.SQL.PRICE;
import static com.arm07.android.eshopkart.db.SQL.QUANTITY;
import static com.arm07.android.eshopkart.db.SQL.TABLE;

/**
 * Created by rashmi on 11/28/2017.
 */

public class ShopProductFragment extends android.support.v4.app.Fragment {

    Unbinder mUnbinder;

    @BindView(R.id.shopProdImageInCard)
    ImageView imageView;
    @BindView(R.id.shopProdtextPriceInCard)
    TextView tvPrice;
    @BindView(R.id.shopProdtextQuantInCard)
    TextView tvQuan;
    @BindView(R.id.shopProdtextNameInCard)
    TextView tvName;
    @BindView(R.id.shopProdtextDescInCard)
     TextView tvDesc;
    @BindView(R.id.appCompatButtonBuy)
    Button btnBuy;
    @BindView(R.id.spinner)
    Spinner spinnerQuant;

    private int prodId,currIndex;
    private String name, quantity,price,desc,image;
    private static Product mylistItem;
    ArrayList<Product> myProdlistItems;

    //private Spinner spinner;
    private SQL SqlHelper;
    private SQLiteDatabase db;

    RecyclerView.LayoutManager layoutManager;

    public ShopProductFragment() {

    }
    /*public static ShopProductFragment newInstance(Product productBean){
        ShopProductFragment fragment = new ShopProductFragment();
        ShopProductFragment.mylistItem = productBean;
        return fragment;
    }*/

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.shop_product,container,false);
        mUnbinder = ButterKnife.bind(this, view);

        SqlHelper = new SQL(getContext());
        db = SqlHelper.getWritableDatabase();


        Bundle bundle=getArguments();
        if(bundle!=null){
            prodId=getArguments().getInt("subCategoryId");
            name=bundle.getString("name");
            quantity=bundle.getString("quant");
            price=bundle.getString("price");
            desc=bundle.getString("desc");
            image=bundle.getString("img");
        }
        //currIndex=getArguments().getInt("index");
        //mylistItem.getID()
        //myProdlistItems.get(currIndex).getImage())
        Picasso.with(getActivity()).load(image).into(imageView);
        tvName.setText(name);
        //tvQuan.setText(quantity);
        tvDesc.setText(desc);
        addSpinner(quantity);
        tvPrice.setText(quantity);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(KEY_ID,prodId);
                cv.put(NAME,name);
                cv.put(PRICE,price+"");
                cv.put(QUANTITY,quantity);
                cv.put(IMAGE,image);

                db.insert(TABLE,null,cv);
                Toast.makeText(getContext(),name + " added to the cart",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void addSpinner(String quantity) {

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i<=Integer.valueOf(quantity); i++){
            list.add(""+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list);
        spinnerQuant.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
