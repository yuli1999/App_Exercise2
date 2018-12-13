package com.umeng.soexample.app_exercise.fragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.BannBean;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

/**
 * data:2018/12/4
 * author: HJL (磊)
 * function:
 */
public class BannAdapter implements MZViewHolder<BannBean.ResultBean> {

    private Context context;
    private List<BannBean.ResultBean> list;
    private SimpleDraweeView mpic;

    public BannAdapter(Context context, List<BannBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.bann_pic, null);
        mpic = view.findViewById(R.id.banner_pic);
        return view;
    }

    @Override
    public void onBind(Context context, int i, BannBean.ResultBean resultBean) {
        Uri uri = Uri.parse(list.get(i).getImageUrl());
        mpic.setImageURI(uri);
    }
}
