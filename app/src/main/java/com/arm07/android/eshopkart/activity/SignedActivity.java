package com.arm07.android.eshopkart.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ExploreFragment;
import com.arm07.android.eshopkart.fragment.FeaturedFragment;
import com.arm07.android.eshopkart.fragment.MyStuffSignedFragment;
import com.arm07.android.eshopkart.fragment.OrderHistoryFragment;
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
    public static final String ORDER_HISTORY_FRAGMENT="Order_History_fragment";

    public static final boolean USER_LOGGED_IN=true;
    boolean user_looged_in = false;
    private TextView mTextMessage;

    private SharedPreferences spref;
    String api_key,user_id,order_phone_number;

    private int currentId,currSubId;
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(SignedActivity.this,"Successfully SIGNED ",Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);


        spref = getSharedPreferences("file5", Context.MODE_PRIVATE);
         api_key= spref.getString("api_key","1fa9fde8966420a223ea80bf99b8a771");
         user_id=spref.getString("user_id","917");
        order_phone_number = spref.getString("input_phone","9849985918");

        Toast.makeText(SignedActivity.this,"userId"+user_id, Toast.LENGTH_LONG).show();


        //BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        //navigation.getMenu().findItem(R.id.navigation_saved).setVisible(true);
        //navigation.enableShiftingMode(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i("MYTEST_home","HOME");

                    Bundle bundle=new Bundle();
                    bundle.putString("user_id",user_id);
                    bundle.putString("api_key",api_key);
                    Toast.makeText(SignedActivity.this,"userId"+user_id, Toast.LENGTH_LONG).show();

                    FeaturedFragment fragment=new FeaturedFragment();
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=manager.beginTransaction();
                    fragmentTransaction.replace(R.id.content2,fragment,FEATURED_FRAGMENT);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    fragment.setArguments(bundle);
                    //fragmentTransaction.addToBackStack(null);
                    Log.i("MYTEST_home2","HOME");
                    return true;
                case R.id.navigation_search:
                    Log.i("MYTEST_explore","explore");

                    Bundle bundle2=new Bundle();
                    bundle2.putString("user_id",user_id);
                    bundle2.putString("api_key",api_key);

                    ExploreFragment exploreFragment1 = new ExploreFragment();
                    android.support.v4.app.FragmentManager manager2 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = manager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.content2, exploreFragment1,EXPLORE_FRAGMENT);
                    fragmentTransaction2.commit();
                    exploreFragment1.setArguments(bundle2);
                    // fragmentTransaction2.addToBackStack(null);
                    return true;
                /* case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
                 case  R.id.navigation_saved:
                    return false;*/
                case R.id.navigation_mystuff:
                    Log.i("MYTEST_mystuff","mystuff");
                    /*Bundle bundle3=new Bundle();
                    bundle3.putString("user_id",user_id);
                    bundle3.putString("api_key",api_key);
                    bundle3.putString("input_phone",order_phone_number);*/

                    MyStuffSignedFragment myStuffFragment = new MyStuffSignedFragment();
                    android.support.v4.app.FragmentManager manager3 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction3 = manager3.beginTransaction();
                    fragmentTransaction3.replace(R.id.content2, myStuffFragment,MY_SIGNED_STUFF_FRAGMENT);
                    fragmentTransaction3.commit();
                    //myStuffFragment.setArguments(bundle3);
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
        bundle.putString("user_id",user_id);
        bundle.putString("api_key",api_key);

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
        bundle.putString("user_id",user_id);
        bundle.putString("api_key",api_key);

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

        bundle.putString("user_id",user_id);
        bundle.putString("api_key",api_key);
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
        if(index==3){
            Toast.makeText(getApplicationContext(), "Message-mystuff Pref(hist) clicked", Toast.LENGTH_LONG).show();
            Bundle bundle=new Bundle();
            bundle.putString("user_id",user_id);
            bundle.putString("api_key",api_key);
            bundle.putString("input_phone",order_phone_number);

            OrderHistoryFragment historyFragment=new OrderHistoryFragment();
            historyFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content2,historyFragment,ORDER_HISTORY_FRAGMENT)
                    .addToBackStack(null).commit();

        }
        if(index==7){
            //SignOut Button Clicked
            Intent intent=new Intent(SignedActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    //Featured Fragment List selection Handler
    @Override
    public void onItemClick(View view, String id) {
        currentId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-featured listitem clicked"+id, Toast.LENGTH_LONG).show();

        Bundle bundle2=new Bundle();
        bundle2.putString("user_id",user_id);
        bundle2.putString("api_key",api_key);
        bundle2.putInt("categoryId",currentId);

        SubCategoryFragment subFragment = new SubCategoryFragment();
        subFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content2, subFragment, SUB_CATEGORY_FRAGMENT)
                .addToBackStack(null).commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.menuSearch);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(item); //item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSearch:
                Toast.makeText(this, "You have selected Search Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuShoppingCart:
                Toast.makeText(this, "You have selected Shopping Cart", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignedActivity.this,PayActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }



}
