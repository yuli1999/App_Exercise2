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
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.adapter.CircleAdapter;
import com.umeng.soexample.app_exercise.fragment.bean.CircleBean;
import com.umeng.soexample.app_exercise.fragment.mvp.presenter.CirclePresenter;
import com.umeng.soexample.app_exercise.fragment.mvp.view.CircleView;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class FragmentCircle extends Fragment implements CircleView {

    private XRecyclerView mcirre;
    private CirclePresenter circlePresenter;
    private List<CircleBean.ResultBean> list;
    private int page = 1;
    private CircleAdapter circleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.f_circle, null);
        mcirre = view.findViewById(R.id.circle_re);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        circlePresenter = new CirclePresenter(this);

        circlePresenter.circle(page, 5);
        list = new ArrayList<>();
        circleAdapter = new CircleAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mcirre.setAdapter(circleAdapter);
        mcirre.setLayoutManager(manager);
        mcirre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                circlePresenter.circle(1, 5);
                mcirre.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                circlePresenter.circle(page, 5);
                mcirre.loadMoreComplete();
            }
        });

    }

    @Override
    public void onCircle(CircleBean data) {

        if (data != null) {
            if (page == 1) {
                list.clear();
            }
            list.addAll(data.getResult());
            circleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCircleFaile(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
