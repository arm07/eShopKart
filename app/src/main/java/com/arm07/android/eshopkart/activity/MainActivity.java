package com.arm07.android.eshopkart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ExploreFragment;
import com.arm07.android.eshopkart.fragment.FeaturedFragment;
import com.arm07.android.eshopkart.fragment.MyStuffFragment;
import com.arm07.android.eshopkart.fragment.ProductFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends AppCompatActivity implements ExploreFragment.ListExploreSelected,MyStuffFragment.ListMystuffSelected {

    public static final String MY_STUFF_FRAGMENT="mystuff_fragment";
    public static final String FEATURED_FRAGMENT="featured_fragment";
    public static final String EXPLORE_FRAGMENT="Explore_fragment";
    public static final String PRODUCT_FRAGMENT="Product_fragment";

    public static final String USER_LOGGED_IN="false";
    boolean user_looged_in=false;
    private TextView mTextMessage;

    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            //navigation.getMenu().findItem(R.id.navigation_saved).setVisible(true);

            BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
            navigation.enableShiftingMode(false);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       /* bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setItems(R.xml.bottom_bar_tabs);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), "Message-tab clicked"+tabId, Toast.LENGTH_LONG).show();
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), "Message-tab clicked", Toast.LENGTH_LONG).show();
            }
        });*/

    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i("MYTEST_home","HOME");
                    //mTextMessage.setText(R.string.title_home);
                    FeaturedFragment savedfeaturedFragment= (FeaturedFragment) getSupportFragmentManager()
                            .findFragmentByTag(FEATURED_FRAGMENT);;
                    if (savedfeaturedFragment == null) {
                        FeaturedFragment featuredFragment1 = new FeaturedFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.content, featuredFragment1,FEATURED_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_home2","HOME");
                    }
                    else{
                        FeaturedFragment featuredFragment1 = new FeaturedFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, featuredFragment1,FEATURED_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_home2","HOME");
                    }
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_search);
                    Log.i("MYTEST_explore","explore");
                    //mTextMessage.setText(R.string.title_home);
                    ExploreFragment savedexploreFragment= (ExploreFragment) getSupportFragmentManager()
                            .findFragmentByTag(EXPLORE_FRAGMENT);;
                    if (savedexploreFragment == null) {
                        ExploreFragment exploreFragment1 = new ExploreFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.content, exploreFragment1,EXPLORE_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_explore2","explore");
                    }
                    else{
                        ExploreFragment exploreFragment1 = new ExploreFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, exploreFragment1,EXPLORE_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.i("MYTEST_explore2","explore");
                    }
                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case  R.id.navigation_saved:
                    return false;
                case R.id.navigation_mystuff:

                    /*user_looged_in = getIntent().getExtras().getBoolean(USER_LOGGED_IN);
                    Toast.makeText(MainActivity.this,"FIRST TIME See"+user_looged_in,Toast.LENGTH_LONG).show();*/
                    Log.i("MYTEST_mystuff","mystuff");
                    //mTextMessage.setText(R.string.title_MyStuff);
                    MyStuffFragment savedFragment= (MyStuffFragment) getSupportFragmentManager()
                            .findFragmentByTag(MY_STUFF_FRAGMENT);
                    if (savedFragment == null) {
                        MyStuffFragment myStuffFragment = new MyStuffFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.content, myStuffFragment,MY_STUFF_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else {
                        MyStuffFragment myStuffFragment = new MyStuffFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, myStuffFragment,MY_STUFF_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    return true;
            }
            return false;
        }
    };

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

    //navigation.getMenu().findItem(R.id.navigation_saved).setVisible(true);



    @Override
    public void onListExploreSelected(int index) {
        Log.i("MYTEST_ExploreList", "Products list");
        if (index == 5) {
            ProductFragment productFragment = new ProductFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.content, productFragment, PRODUCT_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListMyStuffSelected(int index) {
        //Toast.makeText(getApplicationContext(), "Message-mystuff listitem clicked", Toast.LENGTH_LONG).show();
       // Log.i("MYTEST_MyStuffList", "Mystuff list");

        if(index==2){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
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
