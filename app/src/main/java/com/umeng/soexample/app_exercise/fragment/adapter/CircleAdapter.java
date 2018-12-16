package com.umeng.soexample.app_exercise.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.CircleBean;

import java.sql.Date;
import java.util.List;

/**
 * data:2018/12/13
 * author: HJL (磊)
 * function:
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.MyCircleViewHolder> {

    private Context context;
    private List<CircleBean.ResultBean> circlelist;

    public CircleAdapter(Context context, List<CircleBean.ResultBean> circlelist) {
        this.context = context;
        this.circlelist = circlelist;
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.f_circle_item, parent, false);
        MyCircleViewHolder holder = new MyCircleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder holder, int position) {
        CircleBean.ResultBean resultBean = circlelist.get(position);
        Glide.with(context).load(resultBean.getHeadPic()).into(holder.mheadpic);
        holder.mcirname.setText(resultBean.getNickName());
        //时间戳转换
        long createTime = circlelist.get(position).getCreateTime();
        Date date = new Date(createTime);
        holder.mcirtime.setText(date + "");

        holder.mcircount.setText(resultBean.getContent());
        Glide.with(context).load(resultBean.getImage()).into(holder.mcirpic);
    }

    @Override
    public int getItemCount() {
        return circlelist.size();
    }

    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        ImageView mheadpic, mcirpic;
        TextView mcirname, mcirtime, mcircount;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            mheadpic = itemView.findViewById(R.id.circle_head_pic);
            mcirname = itemView.findViewById(R.id.circle_name);
            mcirtime = itemView.findViewById(R.id.circle_time);
            mcircount = itemView.findViewById(R.id.circle_count);
            mcirpic = itemView.findViewById(R.id.circle_pic);
        }
    }

}
