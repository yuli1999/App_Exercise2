package com.umeng.soexample.app_exercise.labelmvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;

import java.util.List;

/**
 * data:2018/12/15
 * author: HJL (磊)
 * function:
 */
public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.MyLabelViewHolder> {
    private Context context;
    private List<LabelBean.ResultBean> list;

    public LabelAdapter(Context context, List<LabelBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyLabelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_first_fragmen_item, parent, false);
        MyLabelViewHolder holder = new MyLabelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyLabelViewHolder holder, int position) {
        holder.mlabelpic.setImageURI(list.get(position).getMasterPic());
        holder.mlabelcount.setText(list.get(position).getCommodityName());
        holder.mlabelpir.setText(list.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyLabelViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mlabelpic;
        TextView mlabelcount, mlabelpir;

        public MyLabelViewHolder(View itemView) {
            super(itemView);
            mlabelpic = itemView.findViewById(R.id.label_pic);
            mlabelcount = itemView.findViewById(R.id.label_count);
            mlabelpir = itemView.findViewById(R.id.label_pir);
        }
    }
}
