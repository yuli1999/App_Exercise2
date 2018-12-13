package com.umeng.soexample.app_exercise.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.event.FirstEvent;
import com.umeng.soexample.app_exercise.fragment.bean.BannBean;
import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;
import com.umeng.soexample.app_exercise.fragment.mvp.presenter.HomePresenter;
import com.umeng.soexample.app_exercise.fragment.mvp.view.HomeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/12/11
 * author: HJL (磊)
 * function:
 */
public class HomeReAdapter extends RecyclerView.Adapter implements HomeView {

    private Context context;
    private MyViewHolderOne myViewHolderOne;
    private MyViewHolderTwo myViewHolderTwo;
    private MyViewHolderThree myViewHolderThree;

    public HomeReAdapter(Context context) {
        this.context = context;
    }

    //声明
    private MyViewHolderbanner myViewHolderbanner;

    //实例化P层
    HomePresenter homePresenter = new HomePresenter(this);

    //轮播图
    private List<BannBean.ResultBean> bannlist = new ArrayList<>();
    //一
    private List<ReOneBean.ResultBean.RxxpBean> onelist = new ArrayList<>();
    //二
    private List<ReOneBean.ResultBean.PzshBean> twolist = new ArrayList<>();
    //三
    private List<ReOneBean.ResultBean.MlssBean> threelist = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = View.inflate(context, R.layout.bann_item, null);
//            View view = LayoutInflater.from(context).inflate(R.layout.bann_item, parent, false);
            myViewHolderbanner = new MyViewHolderbanner(view);
            return myViewHolderbanner;
        } else if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.re_one, parent, false);
            myViewHolderOne = new MyViewHolderOne(view);
            return myViewHolderOne;
        } else if (viewType == 2) {
//            View view = View.inflate(context, R.layout.re_two, null);
            View view = LayoutInflater.from(context).inflate(R.layout.re_two, parent, false);
            myViewHolderTwo = new MyViewHolderTwo(view);
            return myViewHolderTwo;
        } else if (viewType == 3) {
            View view = LayoutInflater.from(context).inflate(R.layout.re_one, parent, false);
            myViewHolderThree = new MyViewHolderThree(view);
            return myViewHolderThree;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolderbanner) {
            homePresenter.bann();
        } else if (holder instanceof MyViewHolderOne) {
            homePresenter.reone();
        } else if (holder instanceof MyViewHolderTwo) {
            homePresenter.retwo();
        } else if (holder instanceof MyViewHolderThree) {
            homePresenter.rethree();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    //轮播图
    @Override
    public void bann(List data) {
        bannlist.addAll(data);
        final BannAdapter adapter = new BannAdapter(context, bannlist);
        myViewHolderbanner.mbann.setPages(bannlist, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return adapter;
            }
        });
    }


    @Override
    public void onOne(List data) {
        onelist.addAll(data);
        myViewHolderOne.monetitle.setText(onelist.get(0).getName());
        List<ReOneBean.ResultBean.RxxpBean.CommodityListBean> commodityList = onelist.get(0).getCommodityList();
        ReAdapterOne reAdapterOne = new ReAdapterOne(context, commodityList);
        myViewHolderOne.monere.setAdapter(reAdapterOne);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myViewHolderOne.monere.setLayoutManager(linearLayoutManager);
        //调到更多商品页面
        myViewHolderOne.monejump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "啦啦啦", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new FirstEvent());

            }
        });
    }


    @Override
    public void onTwo(List data) {
        twolist.addAll(data);
        myViewHolderTwo.mtwotitle.setText(twolist.get(0).getName());
        List<ReOneBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = twolist.get(0).getCommodityList();
        ReAdapterTwo reAdapterTwo = new ReAdapterTwo(context, commodityList);
        myViewHolderTwo.mtwore.setAdapter(reAdapterTwo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolderTwo.mtwore.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onThree(List data) {
        threelist.addAll(data);
        myViewHolderThree.monetitle.setText(threelist.get(0).getName());
        List<ReOneBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = threelist.get(0).getCommodityList();
        ReAdapterThree reAdapterThree = new ReAdapterThree(context, commodityList);
        myViewHolderThree.monere.setAdapter(reAdapterThree);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        myViewHolderThree.monere.setLayoutManager(gridLayoutManager);

    }

    //失败
    @Override
    public void onFaile(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    //轮播图
    public class MyViewHolderbanner extends RecyclerView.ViewHolder {
        MZBannerView mbann;

        public MyViewHolderbanner(View itemView) {
            super(itemView);
            mbann = itemView.findViewById(R.id.banner);
        }
    }

    //第一个
    public class MyViewHolderOne extends RecyclerView.ViewHolder {
        TextView monetitle;
        RecyclerView monere;
        ImageView monejump;

        public MyViewHolderOne(View itemView) {
            super(itemView);
            monejump = itemView.findViewById(R.id.one_jump);
            monetitle = itemView.findViewById(R.id.one_title);
            monere = itemView.findViewById(R.id.one_re);
        }
    }

    //第二个
    public class MyViewHolderTwo extends RecyclerView.ViewHolder {
        TextView mtwotitle;
        RecyclerView mtwore;

        public MyViewHolderTwo(View itemView) {
            super(itemView);
            mtwotitle = itemView.findViewById(R.id.two_title);
            mtwore = itemView.findViewById(R.id.two_re);
        }
    }

    //第三个
    public class MyViewHolderThree extends RecyclerView.ViewHolder {
        TextView monetitle;
        RecyclerView monere;

        public MyViewHolderThree(View itemView) {
            super(itemView);
            monetitle = itemView.findViewById(R.id.one_title);
            monere = itemView.findViewById(R.id.one_re);
        }
    }

}
