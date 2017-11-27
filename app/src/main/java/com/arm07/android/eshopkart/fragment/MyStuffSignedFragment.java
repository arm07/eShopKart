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
import com.arm07.android.eshopkart.utility.ListAdapterSignedMyStuff;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rashmi on 11/27/2017.
 */

public class MyStuffSignedFragment extends Fragment {
    Unbinder unbinder;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ListAdapterSignedMyStuff mListAdapterSignedMyStuff;
    public interface ListMystuffSignedSelected{
        void onListMyStuffSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MyStuffSignedFragment.ListMystuffSignedSelected listener=(MyStuffSignedFragment.ListMystuffSignedSelected)getActivity();

        View view = inflater.inflate(R.layout.fragment_mystuff_signed, container, false);

        //Recycler view is inflated into the view, adapter is attached and layout manager is passed to the view
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerViewSignedMyStuff);
        recyclerView.setHasFixedSize(true);
        //Passing the listener object to the adapter class for implementing onClick()
        mListAdapterSignedMyStuff=new ListAdapterSignedMyStuff(listener);
        recyclerView.setAdapter(mListAdapterSignedMyStuff);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //loadRecylerView();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
