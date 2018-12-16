package com.umeng.soexample.app_exercise.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;
import com.umeng.soexample.app_exercise.labelmvp.adapter.LabelAdapter;
import com.umeng.soexample.app_exercise.labelmvp.presenter.LabelPresenter;
import com.umeng.soexample.app_exercise.labelmvp.view.LabelView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/12/12
 * author: HJL (磊)
 * function:
 */
public class FirstFragment extends Fragment implements LabelView {

    private Button mback;
    private RecyclerView mlabrl;
    private LabelPresenter labelPresenter;
    private List<LabelBean.ResultBean> list = new ArrayList<>();
    private LabelAdapter labelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.event_first_fragment, null);
        mback = view.findViewById(R.id.back);
        mlabrl = view.findViewById(R.id.label_re);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Subscribe
    public void GetData(PassEvent passEvent) {
        Toast.makeText(getActivity(), passEvent.getMoreid() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        labelPresenter = new LabelPresenter(this);
        labelPresenter.label("1002", 1, 10);

        labelAdapter = new LabelAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mlabrl.setAdapter(labelAdapter);
        mlabrl.setLayoutManager(manager);

        //返回键
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回主页
                EventBus.getDefault().post(new HomeEvent());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void labelSuccess(LabelBean labelBean) {
        if (labelBean != null) {
            list.addAll(labelBean.getResult());
        }
        labelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaile(Exception msg) {

    }
}
