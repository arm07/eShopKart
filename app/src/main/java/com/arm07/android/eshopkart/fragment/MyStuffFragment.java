package com.arm07.android.eshopkart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.utility.ListAdapterMyStuff;


public class MyStuffFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ListAdapterMyStuff listAdapterMyStuff;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mystuff, container, false);
        //Recycler view is inflated into the view, adapter is attached and layout manager is passed to the view
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);

        listAdapterMyStuff=new ListAdapterMyStuff();
        recyclerView.setAdapter(listAdapterMyStuff);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadRecylerView();

        return view;
    }

    private void loadRecylerView() {



    }

}