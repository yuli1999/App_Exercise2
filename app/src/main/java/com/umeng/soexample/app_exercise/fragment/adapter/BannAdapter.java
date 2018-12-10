package com.umeng.soexample.app_exercise.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.BannBean;

import java.util.List;

/**
 * data:2018/12/4
 * author: HJL (磊)
 * function:
 */
public class BannAdapter extends RecyclerView.Adapter<BannAdapter.MyViewHolder> {
    private Context context;
    private List<BannBean.ResultBean> list;

    public BannAdapter(Context context, List<BannBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.bann_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mcount.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.mpic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mpic;
        TextView mcount;

        public MyViewHolder(View itemView) {
            super(itemView);
            mpic = itemView.findViewById(R.id.pic);
            mcount = itemView.findViewById(R.id.count);
        }
    }

}
