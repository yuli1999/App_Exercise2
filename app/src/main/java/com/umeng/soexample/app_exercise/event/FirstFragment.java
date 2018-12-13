package com.umeng.soexample.app_exercise.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umeng.soexample.app_exercise.R;

import org.greenrobot.eventbus.EventBus;

/**
 * data:2018/12/12
 * author: HJL (磊)
 * function:
 */
public class FirstFragment extends Fragment {

    private Button mback;
    private RecyclerView mlabrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.event_first_fragment, null);
        mback = view.findViewById(R.id.back);
        mlabrl = view.findViewById(R.id.label_re);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //返回
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new HomeEvent());
            }
        });
    }
}
