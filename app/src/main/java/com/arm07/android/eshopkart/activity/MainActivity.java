package com.arm07.android.eshopkart.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.arm07.android.eshopkart.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

   /* mBottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation);
mBottomNavigationView.enableShiftingMode(false);*/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_mystuff:

                   /* MyStuffFragment savedFragment= (MyStuffFragment) getSupportFragmentManager().findFragmentById(R.id.content);
                    if (savedFragment == null) {
                        MyStuffFragment listFragment = new MyStuffFragment();
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.content, listFragment);
                        fragmentTransaction.commit();
                    }
*/
                    return true;
            }
            return false;
        }

    };

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextMessage = (TextView) findViewById(R.id.message);


        /*MyStuffFragment savedFragment= (MyStuffFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        if (savedFragment == null) {
            MyStuffFragment listFragment = new MyStuffFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.content, listFragment);
            fragmentTransaction.commit();
        }*/

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.enableShiftingMode(false);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
