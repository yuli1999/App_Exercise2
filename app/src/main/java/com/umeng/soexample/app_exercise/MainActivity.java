package com.umeng.soexample.app_exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.umeng.soexample.app_exercise.customize.ViewPagerSlide;
import com.umeng.soexample.app_exercise.fragment.FragmentCar;
import com.umeng.soexample.app_exercise.fragment.FragmentCircle;
import com.umeng.soexample.app_exercise.fragment.FragmentHome;
import com.umeng.soexample.app_exercise.fragment.FragmentMy;
import com.umeng.soexample.app_exercise.fragment.FragmentOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPagerSlide mVp;
    @BindView(R.id.bt_home)
    RadioButton mBtHome;
    @BindView(R.id.bt_circle)
    RadioButton mBtCircle;
    @BindView(R.id.bt_car)
    RadioButton mBtCar;
    @BindView(R.id.bt_order)
    RadioButton mBtOrder;
    @BindView(R.id.bt_my)
    RadioButton mBtMy;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mVp.setSlide(false);//viewpage不滑动
        fragments.add(new FragmentHome());
        fragments.add(new FragmentCircle());
        fragments.add(new FragmentCar());
        fragments.add(new FragmentOrder());
        fragments.add(new FragmentMy());

        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);


    }

    @OnClick({R.id.bt_home, R.id.bt_circle, R.id.bt_car, R.id.bt_order, R.id.bt_my})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_home:
                mVp.setCurrentItem(0);
                break;
            case R.id.bt_circle:
                mVp.setCurrentItem(1);
                break;
            case R.id.bt_car:
                mVp.setCurrentItem(2);
                break;
            case R.id.bt_order:
                mVp.setCurrentItem(3);
                break;
            case R.id.bt_my:
                mVp.setCurrentItem(4);
                break;
        }
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
}
