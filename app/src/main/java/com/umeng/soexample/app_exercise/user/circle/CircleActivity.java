package com.umeng.soexample.app_exercise.user.circle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.user.circle.mvp.MyCirclePresenter;
import com.umeng.soexample.app_exercise.user.circle.mvp.MyCircleView;

import java.util.ArrayList;
import java.util.List;

public class CircleActivity extends AppCompatActivity implements MyCircleView {

    private XRecyclerView mre;
    private List<MyCircleBean.ResultBean> list = new ArrayList<>();
    private MyCirclePresenter myCirclePresenter;
    private MyCircleAdapter myCircleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        mre = findViewById(R.id.mycircle_re);
        myCirclePresenter = new MyCirclePresenter(this);
        myCirclePresenter.mycircle(1, 5);

        myCircleAdapter = new MyCircleAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mre.setAdapter(myCircleAdapter);
        mre.setLayoutManager(linearLayoutManager);

        mre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myCirclePresenter.mycircle(1, 5);
                mre.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mre.loadMoreComplete();
            }
        });

    }

    @Override
    public void onCircle(MyCircleBean data) {
        if (data != null) {
            list.addAll(data.getResult());
        }
    }

    @Override
    public void OnFaile(Exception e) {
        Toast.makeText(this, (CharSequence) e, Toast.LENGTH_SHORT).show();
    }
}
