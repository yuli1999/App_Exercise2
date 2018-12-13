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
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.ReOneBean;

import java.util.List;

/**
 * data:2018/12/7
 * author: HJL (磊)
 * function:
 */
public class ReAdapterTwo extends RecyclerView.Adapter<ReAdapterTwo.MyViewHolder> {

    private Context context;
    private List<ReOneBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public ReAdapterTwo(Context context, List<ReOneBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.re_two_item, null);
        View view = LayoutInflater.from(context).inflate(R.layout.re_two_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mpic.setImageURI(list.get(position).getMasterPic());
        holder.mcount.setText(list.get(position).getCommodityName());
        holder.money.setText("￥" + list.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mpic;
        TextView mcount;
        TextView money;

        public MyViewHolder(View itemView) {
            super(itemView);
            mpic = itemView.findViewById(R.id.two_pic);
            mcount = itemView.findViewById(R.id.two_count);
            money = itemView.findViewById(R.id.two_pir);
        }
    }
}
