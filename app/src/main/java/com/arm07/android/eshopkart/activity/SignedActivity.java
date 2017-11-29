package com.arm07.android.eshopkart.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ExploreFragment;
import com.arm07.android.eshopkart.fragment.FeaturedFragment;
import com.arm07.android.eshopkart.fragment.MyStuffSignedFragment;
import com.arm07.android.eshopkart.fragment.ProductFragment;
import com.arm07.android.eshopkart.fragment.ShopProductFragment;
import com.arm07.android.eshopkart.fragment.SubCategoryFragment;
import com.roughike.bottombar.BottomBar;

/**
 * Created by rashmi on 11/27/2017.
 */

public class SignedActivity extends AppCompatActivity implements ExploreFragment.ListExploreSelected,MyStuffSignedFragment.ListMystuffSignedSelected,
        FeaturedFragment.OnRecyclerViewItemClickListener,SubCategoryFragment.OnSubCategoryItemClickListener,ProductFragment.OnProductClickListener {

    public static final String MY_SIGNED_STUFF_FRAGMENT = "my_signedstuff_fragment";
    public static final String FEATURED_FRAGMENT = "featured_fragment";
    public static final String EXPLORE_FRAGMENT = "Explore_fragment";
    public static final String PRODUCT_FRAGMENT = "Product_fragment";
    public static final String SUB_CATEGORY_FRAGMENT="Sub_fragment";
    public static final String SHOP_PRODUCT_FRAGMENT="Shop_Product_fragment";

    public static final boolean USER_LOGGED_IN=true;
    boolean user_looged_in = false;
    private TextView mTextMessage;

    private int currentId,currSubId;
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(SignedActivity.this,"Successfully SIGNED ",Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        //user_looged_in=getIntent().getBooleanExtra("userIsLogged",false);
        //BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.getMenu().findItem(R.id.navigation_saved).setVisible(true);
        //navigation.enableShiftingMode(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i("MYTEST_home","HOME");
                    FeaturedFragment fragment=new FeaturedFragment();
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=manager.beginTransaction();
                    fragmentTransaction.replace(R.id.content2,fragment,FEATURED_FRAGMENT);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    //fragmentTransaction.addToBackStack(null);
                    Log.i("MYTEST_home2","HOME");
                    return true;
                case R.id.navigation_search:
                    Log.i("MYTEST_explore","explore");

                    ExploreFragment exploreFragment1 = new ExploreFragment();
                    android.support.v4.app.FragmentManager manager2 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = manager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.content2, exploreFragment1,EXPLORE_FRAGMENT);
                    fragmentTransaction2.commit();
                    // fragmentTransaction2.addToBackStack(null);
                    Log.i("MYTEST_explore2","explore");
                    return true;
             /*   case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;*/
                case  R.id.navigation_saved:
                    return false;
                case R.id.navigation_mystuff:
                    Log.i("MYTEST_mystuff","mystuff");
                    MyStuffSignedFragment myStuffFragment = new MyStuffSignedFragment();
                    android.support.v4.app.FragmentManager manager3 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction3 = manager3.beginTransaction();
                    fragmentTransaction3.replace(R.id.content2, myStuffFragment,MY_SIGNED_STUFF_FRAGMENT);
                    fragmentTransaction3.commit();
                    //fragmentTransaction3.addToBackStack(null);
                    return true;
            }
            return false;
        }
    };

    //Explore Fragment List Item Clicked
    @Override
    public void onListExploreSelected(int index,String id) {

        Log.i("MYTEST_ExploreList", "Products list");

        currSubId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-Explore(sub Category) listitem clicked"+id, Toast.LENGTH_LONG).show();
        Bundle bundle=new Bundle();
        bundle.putInt("subCategoryId",currSubId);

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content2, productFragment, PRODUCT_FRAGMENT)
                .addToBackStack(null).commit();
    }

    //Sub Category OnClick
    @Override
    public void onItemSubClick(View view, String id) {

        currSubId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-sub Category listitem clicked"+id, Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putInt("subCategoryId",currSubId);

        ProductFragment productFragment=new ProductFragment();
        productFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content2,productFragment,PRODUCT_FRAGMENT)
                .addToBackStack(null).commit();
    }

    //Product Fragment OnClick
    @Override
    public void onProdItemClicked(View view, int index, String id, String name, String quantity, String price, String desc, String image) {

        currSubId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-Product listitem clicked"+id, Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putInt("subCategoryId",currSubId);
        bundle.putString("name",name);
        bundle.putString("quant",quantity);
        bundle.putString("price",price);
        bundle.putString("desc",desc);
        bundle.putString("img",image);
        //bundle.putInt("index",index);
        ShopProductFragment shopProductFragment=new ShopProductFragment();
        shopProductFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content2,shopProductFragment,SHOP_PRODUCT_FRAGMENT)
                .addToBackStack(null).commit();
    }

    @Override
    public void onListMyStuffSelected(int index) {

        if(index==2){
            Toast.makeText(getApplicationContext(), "Message-mystuff listitem clicked", Toast.LENGTH_LONG).show();
            //include Paypal & credit cards info
            /*Intent intent = new Intent(SignedActivity.this, LoginActivity.class);
            startActivity(intent);*/
        }
    }

    //Featured Fragment List selection Handler
    @Override
    public void onItemClick(View view, String id) {
        currentId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-featured listitem clicked"+id, Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putInt("categoryId",currentId);

        SubCategoryFragment subFragment = new SubCategoryFragment();
        subFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content2, subFragment, SUB_CATEGORY_FRAGMENT)
                .addToBackStack(null).commit();
    }





}
