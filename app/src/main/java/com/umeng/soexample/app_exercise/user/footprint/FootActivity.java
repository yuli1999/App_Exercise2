package com.umeng.soexample.app_exercise.user.footprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.textservice.SuggestionsInfo;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.user.circle.MyCircleAdapter;
import com.umeng.soexample.app_exercise.user.circle.MyCircleBean;
import com.umeng.soexample.app_exercise.user.circle.mvp.MyCirclePresenter;
import com.umeng.soexample.app_exercise.user.footprint.mvp.MyFootPresenter;
import com.umeng.soexample.app_exercise.user.footprint.mvp.MyFootView;

import java.util.ArrayList;
import java.util.List;

public class FootActivity extends AppCompatActivity implements MyFootView {
    private XRecyclerView mre;
    private List<MyFootBean.ResultBean> list = new ArrayList<>();
    private MyFootPresenter myFootPresenter;
    private MyFootAdapter myFootAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot);

        mre = findViewById(R.id.my_foot_re);
        myFootPresenter = new MyFootPresenter(this);
        myFootPresenter.myfoot(1, 5);

        myFootAdapter = new MyFootAdapter(this, list);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mre.setAdapter(myFootAdapter);
        mre.setLayoutManager(manager);

        mre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                myFootPresenter.myfoot(1, 5);
                mre.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                myFootPresenter.myfoot(page, 5);
                mre.loadMoreComplete();
            }
        });
    }

    @Override
    public void onCircle(MyFootBean data) {
        if (data != null) {
            list.addAll(data.getResult());
        }
        myFootAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFaile(Exception e) {
        Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
