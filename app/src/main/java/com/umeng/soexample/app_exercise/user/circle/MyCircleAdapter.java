package com.umeng.soexample.app_exercise.user.circle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;

import java.util.Date;
import java.util.List;

/**
 * data:2018/12/16
 * author: HJL (磊)
 * function:
 */
public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.MyCircleViewHolder> {
    private Context context;
    private List<MyCircleBean.ResultBean> list;

    public MyCircleAdapter(Context context, List<MyCircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycircle_re_item, parent, false);
        MyCircleViewHolder holder = new MyCircleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder holder, int position) {
        holder.mcirclecount.setText(list.get(position).getContent());
        holder.mcirclepic.setImageURI(list.get(position).getImage());
        Date date = new Date(list.get(position).getCreateTime());
        holder.mcircletime.setText(date + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mcirclepic;
        TextView mcirclecount, mcircletime;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            mcirclecount = itemView.findViewById(R.id.my_circle_count);
            mcirclepic = itemView.findViewById(R.id.my_circle_pic);
            mcircletime = itemView.findViewById(R.id.my_circle_time);
        }
    }

}
