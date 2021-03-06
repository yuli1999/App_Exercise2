package com.umeng.soexample.app_exercise.customize;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.app_exercise.R;

/**
 * data:2018/12/15
 * author: HJL (磊)
 * function:
 */
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
