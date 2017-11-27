package com.arm07.android.eshopkart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.activity.LoginActivity;
import com.arm07.android.eshopkart.utility.ListAdapterMyStuff;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyStuffFragment extends Fragment {

    Unbinder unbinder;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.signInOrSignUp)
    Button signInUpBTN;

    ListAdapterMyStuff listAdapterMyStuff;

    public interface ListMystuffSelected{
        void onListMyStuffSelected(int index);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // listener object to handle on Click event
        ListMystuffSelected listener=(ListMystuffSelected)getActivity();
        View view = inflater.inflate(R.layout.fragment_mystuff, container, false);

        //Recycler view is inflated into the view, adapter is attached and layout manager is passed to the view
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        //Passing the listener object to the adapter class for implementing onClick()
        listAdapterMyStuff=new ListAdapterMyStuff(listener);
        recyclerView.setAdapter(listAdapterMyStuff);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //loadRecylerView();
        unbinder = ButterKnife.bind(this, view);

        signInUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    /*private void loadRecylerView() {

    }*/

}