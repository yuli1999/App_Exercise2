package com.umeng.soexample.app_exercise.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.adapter.BannAdapter;
import com.umeng.soexample.app_exercise.fragment.adapter.ReAdapterOne;
import com.umeng.soexample.app_exercise.fragment.adapter.ReAdapterThree;
import com.umeng.soexample.app_exercise.fragment.adapter.ReAdapterTwo;
import com.umeng.soexample.app_exercise.fragment.bean.BannBean;
import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;
import com.umeng.soexample.app_exercise.fragment.mvp.presenter.HomePresenter;
import com.umeng.soexample.app_exercise.fragment.mvp.view.HomeView;
import com.umeng.soexample.app_exercise.net.HttpNet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class FragmentHome extends Fragment implements HomeView {

    private RecyclerCoverFlow lb;
    private String url = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private List<BannBean.ResultBean> list = new ArrayList<>();
    private List<ReOneBean.ResultBean.RxxpBean.CommodityListBean> listre = new ArrayList<>();
    private List<ReOneBean.ResultBean.PzshBean.CommodityListBeanX> listtwo = new ArrayList<>();
    private List<ReOneBean.ResultBean.MlssBean.CommodityListBeanXX> listthree = new ArrayList<>();
    private BannAdapter adapter;
    private RecyclerView mHomeRe1;
    private RecyclerView mHomeRe2;
    private RecyclerView mHomeRe3;
    private ReAdapterOne reAdapterOne;
    private HomePresenter homePresenter;
    private ReAdapterTwo reAdapterTwo;
    private ReAdapterThree reAdapterThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.f_home_item, null);
        lb = view.findViewById(R.id.list);
        mHomeRe1 = view.findViewById(R.id.home_re_1);
        mHomeRe2 = view.findViewById(R.id.home_re_2);
        mHomeRe3 = view.findViewById(R.id.home_re_3);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homePresenter = new HomePresenter(this);
        initdata();
        adapter = new BannAdapter(getActivity(), list);
        lb.setAdapter(adapter);
        lb.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                lb.getLayoutManager().getItemCount();
            }
        });
        //一
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHomeRe1.setLayoutManager(manager);

        //二
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        mHomeRe2.setLayoutManager(manager2);
        //三
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHomeRe3.setLayoutManager(gridLayoutManager);

        homePresenter.reone();
        homePresenter.retwo();
        homePresenter.rethree();

    }

    private void initdata() {
        HttpNet.EnestenGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                final BannBean bann_bean = gson.fromJson(data, BannBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        List<BannBean.ResultBean> result = bann_bean.getResult();
                        list.addAll(result);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    Handler handler = new Handler();

    @Override
    public void onSuccess(List data) {
        reAdapterOne = new ReAdapterOne(getActivity(), listre);
        mHomeRe1.setAdapter(reAdapterOne);
        listre.addAll(data);
        reAdapterOne.notifyDataSetChanged();
    }

    @Override
    public void onTwo(List data) {
        reAdapterTwo = new ReAdapterTwo(getActivity(), listtwo);
        mHomeRe2.setAdapter(reAdapterTwo);
        listtwo.addAll(data);
        reAdapterTwo.notifyDataSetChanged();
    }

    @Override
    public void onThree(List data) {
        reAdapterThree = new ReAdapterThree(getActivity(), listthree);
        mHomeRe3.setAdapter(reAdapterThree);
        listthree.addAll(data);
        reAdapterThree.notifyDataSetChanged();
    }

    @Override
    public void onFaile(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
