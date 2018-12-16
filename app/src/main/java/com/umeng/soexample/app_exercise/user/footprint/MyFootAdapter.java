package com.umeng.soexample.app_exercise.user.footprint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.user.circle.MyCircleBean;

import java.util.Date;
import java.util.List;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyFootAdapter extends RecyclerView.Adapter<MyFootAdapter.MyCircleViewHolder> {
    private Context context;
    private List<MyFootBean.ResultBean> list;

    public MyFootAdapter(Context context, List<MyFootBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myfoot_re_item, parent, false);
        MyCircleViewHolder holder = new MyCircleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder holder, int position) {
        holder.mcirclecount.setText(list.get(position).getCommodityName());
        holder.mcirclepic.setImageURI(list.get(position).getMasterPic());
        holder.mcirclemoney.setText(list.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mcirclepic;
        TextView mcirclecount, mcirclemoney;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            mcirclecount = itemView.findViewById(R.id.foot_count);
            mcirclepic = itemView.findViewById(R.id.foot_img);
            mcirclemoney = itemView.findViewById(R.id.foot_money);
        }
    }

}
