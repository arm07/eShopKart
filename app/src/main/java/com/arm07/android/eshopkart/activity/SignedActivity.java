package com.arm07.android.eshopkart.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.fragment.ExploreFragment;
import com.arm07.android.eshopkart.fragment.FeaturedFragment;
import com.arm07.android.eshopkart.fragment.MyStuffSignedFragment;
import com.arm07.android.eshopkart.fragment.ProductFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roughike.bottombar.BottomBar;

/**
 * Created by rashmi on 11/27/2017.
 */

public class SignedActivity extends AppCompatActivity implements ExploreFragment.ListExploreSelected,MyStuffSignedFragment.ListMystuffSignedSelected {

    public static final String MY_SIGNED_STUFF_FRAGMENT = "my_signedstuff_fragment";
    public static final String FEATURED_FRAGMENT = "featured_fragment";
    public static final String EXPLORE_FRAGMENT = "Explore_fragment";
    public static final String PRODUCT_FRAGMENT = "Product_fragment";

    public static final String USER_LOGGED_IN = "false";
    boolean user_looged_in = false;
    private TextView mTextMessage;

    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(SignedActivity.this,"Successfully SIGNED ",Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        //navigation.getMenu().findItem(R.id.navigation_saved).setVisible(true);
        navigation.enableShiftingMode(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    MyStuffSignedFragment savedFragment= (MyStuffSignedFragment) getSupportFragmentManager()
                            .findFragmentByTag(MY_SIGNED_STUFF_FRAGMENT);
                    if (savedFragment == null) {
                        MyStuffSignedFragment myStuffFragment = new MyStuffSignedFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.content, myStuffFragment,MY_SIGNED_STUFF_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else {
                        MyStuffSignedFragment myStuffFragment = new MyStuffSignedFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, myStuffFragment,MY_SIGNED_STUFF_FRAGMENT);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    return true;
            }
            return false;
        }
    };

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

        if(index==2){
            //include Paypal & credit cards info
            /*Intent intent = new Intent(SignedActivity.this, LoginActivity.class);
            startActivity(intent);*/
        }
    }
}
