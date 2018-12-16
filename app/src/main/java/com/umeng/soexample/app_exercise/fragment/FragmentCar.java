package com.umeng.soexample.app_exercise.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.fragment.adapter.CarAdapter;
import com.umeng.soexample.app_exercise.fragment.bean.CarBean;
import com.umeng.soexample.app_exercise.fragment.mvp.presenter.CarPresenter;
import com.umeng.soexample.app_exercise.fragment.mvp.view.CarView;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class FragmentCar extends Fragment implements CarView {
    private static final String TAG = "FragmentCar";
    private RecyclerView mcarre;
    private CarPresenter carPresenter;
    private List<CarBean.ResultBean> list = new ArrayList<>();
    private CarAdapter carAdapter;
    private CheckBox mboxall;
    private TextView mprice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_car, container, false);
        mcarre = view.findViewById(R.id.car_re);
        mboxall = view.findViewById(R.id.box_all);
        mprice = view.findViewById(R.id.total_price);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        carPresenter = new CarPresenter(this);
        carPresenter.car();

        carAdapter = new CarAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mcarre.setAdapter(carAdapter);
        mcarre.setLayoutManager(manager);
        carAdapter.notifyDataSetChanged();

        initcheck();


    }

    //全选反选
    private void initcheck() {
        //全选框选中
        mboxall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = mboxall.isChecked();//true
                for (CarBean.ResultBean resultBean : list) {
                    resultBean.setChecked(checked);//给集合中赋值true,条目checkbox选中
                }
                //刷新页面
                carAdapter.notifyDataSetChanged();
                getSum();//计算总价
            }
        });

        //判断商品条目checkbox
        carAdapter.setOnShopCartClickListener(new CarAdapter.OnShopCartClickListener() {
            @Override
            public void onShopCartClick(int position, boolean isChecked) {
                //条目不选中,去哪选不选中
                if (!isChecked) {
                    mboxall.setChecked(false);
                } else {
                    boolean click = true;
                    //遍历判断条目是否都选中
                    for (CarBean.ResultBean resultBean : list) {
                        if (!resultBean.isChecked()) {//有不选中的
                            click = false;
                            break;//停止
                        }
                    }
                    mboxall.setChecked(click);//全选框不选中
                }
                getSum();//计算总价
            }
        });

        //全选
        carAdapter.setOnAddClickListener(new CarAdapter.OnAddClickListener() {
            @Override
            public void onAddClick() {
                getSum();//计算总价
            }
        });
    }

    //计算总价
    public void getSum() {
        float sum = 0;
        for (CarBean.ResultBean resultBean : list) {
            if (resultBean.isChecked()) {
                sum += resultBean.getPrice() * resultBean.getCount();
            }
        }
        mprice.setText("" + sum);
    }

    @Override
    public void onCar(CarBean data) {
        if (data != null) {
            list.clear();
            list.addAll(data.getResult());
        }
        carAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCircleFaile(Exception e) {
        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("222222", "onCircleFaile: " + e.getMessage());
    }
}
