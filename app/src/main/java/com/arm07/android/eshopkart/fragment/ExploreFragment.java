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
import com.arm07.android.eshopkart.utility.ListAdapterExplore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rashmi on 11/25/2017.
 */

public class ExploreFragment extends Fragment {

    Unbinder mUnbinder;
    @BindView(R.id.listExploreRecyclerView)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ListAdapterExplore mListAdapterExplore;

    public interface ListExploreSelected{
        void onListExploreSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListExploreSelected listener=(ListExploreSelected)getActivity();

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);

        mListAdapterExplore=new ListAdapterExplore(listener);
        recyclerView.setAdapter(mListAdapterExplore);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
