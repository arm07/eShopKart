package com.arm07.android.eshopkart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyStuffFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mystuff, container, false);
        //Recycler view is inflated into the view, adapter is attached and layout manager is passed to the view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

       /* ListAdapter adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;

    }

}