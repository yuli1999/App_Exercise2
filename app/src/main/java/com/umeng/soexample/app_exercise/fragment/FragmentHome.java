package com.umeng.soexample.app_exercise.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.adapter.HomeReAdapter;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class FragmentHome extends Fragment {


    private RecyclerView mRe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.f_home_item, null);
        mRe = view.findViewById(R.id.home_re);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        HomeReAdapter adapter = new HomeReAdapter(getActivity());
        mRe.setAdapter(adapter);
        mRe.setLayoutManager(manager);

    }
}
