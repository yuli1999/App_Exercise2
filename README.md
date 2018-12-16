
## 自定义view 加减器

```
package com.umeng.soexample.app_exercise.customize;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.app_exercise.R;

public class AddandSub extends LinearLayout {


    private View view;
    private TextView add;
    private TextView sub;
    private TextView number;

    public AddandSub(Context context) {
        this(context, null);
    }

    public AddandSub(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AddandSub(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();

    }



    //找控件
    private void initView(Context context) {
        view = View.inflate(context, R.layout.add_sum, this);
        add = view.findViewById(R.id.tvadd);
        sub = view.findViewById(R.id.tvsub);
        number = view.findViewById(R.id.number);
        number.setText("1");
    }


    private void initListener() {
        //加
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        //减
        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub();
            }
        });
    }
    //加方法
    private void add() {
        String cu = number.getText().toString();
        int parseInt = Integer.parseInt(cu);
        parseInt++;
        setCurentCount(parseInt);
    }
    //减方法
    private void sub() {
        String cu = number.getText().toString();
        int parseInt = Integer.parseInt(cu);
        if (parseInt > 1) {
            parseInt--;
            setCurentCount(parseInt);
        } else {
            Toast.makeText(getContext(), "再减没了", Toast.LENGTH_SHORT).show();
        }
    }

    public int getCurentCount() {
        return Integer.parseInt(number.getText().toString());
    }

    //商品个数接口回调
    private OnNumChangedListener onNumChangedListener;

    public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
        this.onNumChangedListener = onNumChangedListener;
    }

    public interface OnNumChangedListener {
        void onNumChanged(View view, int curNum);
    }

    public void setCurentCount(int num) {
        number.setText(num + "");
        if (onNumChangedListener != null) {
            onNumChangedListener.onNumChanged(this, num);
        }
    }
}

```
## 适配器

```
package com.umeng.soexample.app_exercise.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.customize.AddandSub;
import com.umeng.soexample.app_exercise.fragment.bean.CarBean;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyCarViewHolder> {

    //条目checkbox
    private OnShopCartClickListener listener;

    public void setOnShopCartClickListener(OnShopCartClickListener listener) {
        this.listener = listener;
    }

    public interface OnShopCartClickListener {
        void onShopCartClick(int position, boolean isChecked);
    }

    //全选框
    private OnAddClickListener addListener;

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.addListener = listener;
    }

    public interface OnAddClickListener {
        void onAddClick();
    }
//////
    private Context context;
    private List<CarBean.ResultBean> carlist;


    public CarAdapter(Context context, List<CarBean.ResultBean> carlist) {
        this.context = context;
        this.carlist = carlist;
    }

    @NonNull
    @Override
    public MyCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.f_car_item, parent, false);
        MyCarViewHolder holder = new MyCarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCarViewHolder holder, final int position) {
        final CarBean.ResultBean resultBean = carlist.get(position);
        holder.mcarpic.setImageURI(carlist.get(position).getPic());
        holder.mcarcount.setText(carlist.get(position).getCommodityName());
        holder.mcarmoney.setText("￥" + resultBean.getPrice());

        //选择
        holder.mcarbox.setOnCheckedChangeListener(null);
        holder.mcarbox.setChecked(resultBean.isChecked());
        //单选框改变监听
        holder.mcarbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resultBean.setChecked(isChecked);
                if (listener!=null) {
                    listener.onShopCartClick(position, isChecked);
                }
            }
        });
        //商品数量
        holder.maddsum.setCurentCount(carlist.get(position).getCount());
        holder.maddsum.setOnNumChangedListener(new AddandSub.OnNumChangedListener() {
            @Override
            public void onNumChanged(View view, int curNum) {
                resultBean.setCount(curNum);
                if (addListener!=null){
                    addListener.onAddClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carlist.size();
    }

    class MyCarViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mcarpic;
        TextView mcarcount;
        TextView mcarmoney;
        CheckBox mcarbox;
        AddandSub maddsum;

        public MyCarViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.car_box);
            mcarpic = itemView.findViewById(R.id.car_pic);
            mcarcount = itemView.findViewById(R.id.car_count);
            mcarmoney = itemView.findViewById(R.id.car_money);
            mcarbox = itemView.findViewById(R.id.car_box);
            maddsum = itemView.findViewById(R.id.addsum);
        }
    }
}
```
## 主方法

```
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
        
        //实例化P层
        carPresenter = new CarPresenter(this);
        carPresenter.car();
        
        //适配器
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
```
