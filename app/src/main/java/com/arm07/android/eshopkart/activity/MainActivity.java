package com.arm07.android.eshopkart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import com.arm07.android.eshopkart.fragment.MyStuffFragment;
import com.arm07.android.eshopkart.fragment.ProductFragment;
import com.arm07.android.eshopkart.fragment.SubCategoryFragment;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends AppCompatActivity implements ExploreFragment.ListExploreSelected,MyStuffFragment.ListMystuffSelected,
        FeaturedFragment.OnRecyclerViewItemClickListener,SubCategoryFragment.OnSubCategoryItemClickListener{

    public static final String MY_STUFF_FRAGMENT="mystuff_fragment";
    public static final String FEATURED_FRAGMENT="featured_fragment";
    public static final String EXPLORE_FRAGMENT="Explore_fragment";
    public static final String PRODUCT_FRAGMENT="Product_fragment";
    public static final String SUB_CATEGORY_FRAGMENT="Sub_fragment";

    public static final String USER_LOGGED_IN="false";
    boolean user_looged_in=false;
    private TextView mTextMessage;
    private int currentId,currSubId;


    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       /* bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setItems(R.xml.bottom_bar_tabs);
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), "Message-tab clicked", Toast.LENGTH_LONG).show();
            }
        });*/

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i("MYTEST_home","featured");
                    /*FeaturedFragment savedfeaturedFragment= (FeaturedFragment) getSupportFragmentManager()
                            .findFragmentByTag(FEATURED_FRAGMENT);*/

                        FeaturedFragment featuredFragment1 = new FeaturedFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, featuredFragment1,FEATURED_FRAGMENT);
                        //fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_home2","featured_HOME");

                    return true;
                case R.id.navigation_search:
                    Log.i("MYTEST_explore","explore");
                    /*ExploreFragment savedexploreFragment= (ExploreFragment) getSupportFragmentManager()
                            .findFragmentByTag(EXPLORE_FRAGMENT);*/

                        ExploreFragment exploreFragment1 = new ExploreFragment();
                        android.support.v4.app.FragmentManager manager3 = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = manager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.content, exploreFragment1,EXPLORE_FRAGMENT);
                        //fragmentTransaction.addToBackStack(null);
                        fragmentTransaction3.commit();
                        Log.i("MYTEST_explore2","explore");

                    return true;
               /* case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;*/

                case R.id.navigation_mystuff:
                    Log.i("MYTEST_mystuff","mystuff");
                  /*  MyStuffFragment savedFragment= (MyStuffFragment) getSupportFragmentManager()
                            .findFragmentByTag(MY_STUFF_FRAGMENT); */

                        MyStuffFragment myStuffFragment = new MyStuffFragment();
                        android.support.v4.app.FragmentManager manager2 = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = manager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.content, myStuffFragment,MY_STUFF_FRAGMENT);
                        //fragmentTransaction.addToBackStack(null);
                        fragmentTransaction2.commit();
                    return true;
            }
            return false;
        }
    };

    //Explore Fragment List Item Clicked
    @Override
    public void onListExploreSelected(int index,String id) {

        currSubId=Integer.valueOf(id);
        Toast.makeText(getApplicationContext(),"Message-sub Category listitem clicked"+id, Toast.LENGTH_LONG).show();
        Bundle bundle=new Bundle();
        bundle.putInt("subCategoryId",currSubId);

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, productFragment, PRODUCT_FRAGMENT)
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
                .replace(R.id.content,productFragment,PRODUCT_FRAGMENT)
                .addToBackStack(null).commit();
    }
    @Override
    public void onListMyStuffSelected(int index) {
        //Toast.makeText(getApplicationContext(), "Message-mystuff listitem clicked", Toast.LENGTH_LONG).show();
        if(index==2){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    // Featured Fragment On Click
    @Override
    public void onItemClick(View view, String data) {
        currentId=Integer.valueOf(data);
        Toast.makeText(getApplicationContext(),"Message-featured listitem clicked"+data, Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putInt("categoryId",currentId);

        SubCategoryFragment subFragment = new SubCategoryFragment();
        subFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, subFragment, SUB_CATEGORY_FRAGMENT)
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
}

/*@Override
    protected void onResume() {
        super.onResume();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();

                switch (tabId) {
                    case R.id.navigation_home1:
                        Log.i("MYTEST_home", "HOME");
                        //mTextMessage.setText(R.string.title_home);

                        FeaturedFragment featuredFragment1 = new FeaturedFragment();
                        fragmentTransaction.replace(R.id.content, featuredFragment1, FEATURED_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_home2", "HOME");

                        break;
                    case  R.id.navigation_search1:
                        //mTextMessage.setText(R.string.title_search);
                        Log.i("MYTEST_explore", "explore");
                        //mTextMessage.setText(R.string.title_home);
                        ExploreFragment savedexploreFragment = (ExploreFragment) getSupportFragmentManager()
                                .findFragmentByTag(EXPLORE_FRAGMENT);
                        if (savedexploreFragment == null) {
                            ExploreFragment exploreFragment1 = new ExploreFragment();
                            fragmentTransaction.add(R.id.content, exploreFragment1, EXPLORE_FRAGMENT);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            Log.i("MYTEST_explore2", "explore");
                        } else {
                            ExploreFragment exploreFragment1 = new ExploreFragment();
                            fragmentTransaction.replace(R.id.content, exploreFragment1, EXPLORE_FRAGMENT);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            Log.i("MYTEST_explore2", "explore");
                        }
                    case R.id.navigation_notifications1:
                        break;
                    // mTextMessage.setText(R.string.title_notifications);
                    case R.id.navigation_saved1:
                        break;
                    case  R.id.navigation_mystuff1:
                        Log.i("MYTEST_mystuff", "mystuff");
                        //mTextMessage.setText(R.string.title_MyStuff);
                        MyStuffFragment savedFragment = (MyStuffFragment) getSupportFragmentManager()
                                .findFragmentByTag(MY_STUFF_FRAGMENT);
                        if (savedFragment == null) {
                            MyStuffFragment myStuffFragment = new MyStuffFragment();
                            fragmentTransaction.add(R.id.content, myStuffFragment, MY_STUFF_FRAGMENT);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        } else {
                            MyStuffFragment myStuffFragment = new MyStuffFragment();
                            fragmentTransaction.replace(R.id.content, myStuffFragment, MY_STUFF_FRAGMENT);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                        break;
                }
            }

        } );

    }*/