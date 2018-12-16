package com.umeng.soexample.app_exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.umeng.soexample.app_exercise.customize.ViewPagerSlide;
import com.umeng.soexample.app_exercise.event.FirstEvent;
import com.umeng.soexample.app_exercise.event.FirstFragment;
import com.umeng.soexample.app_exercise.event.HomeEvent;
import com.umeng.soexample.app_exercise.event.HomeEvent2;
import com.umeng.soexample.app_exercise.event.HomeEvent3;
import com.umeng.soexample.app_exercise.event.PassEvent;
import com.umeng.soexample.app_exercise.event.SecondEvent;
import com.umeng.soexample.app_exercise.event.SecondFragment;
import com.umeng.soexample.app_exercise.event.ThirdEvent;
import com.umeng.soexample.app_exercise.event.ThirdFragment;
import com.umeng.soexample.app_exercise.fragment.FragmentCar;
import com.umeng.soexample.app_exercise.fragment.FragmentCircle;
import com.umeng.soexample.app_exercise.fragment.FragmentHome;
import com.umeng.soexample.app_exercise.fragment.FragmentMy;
import com.umeng.soexample.app_exercise.fragment.FragmentOrder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private FragmentManager mFragmentManager;
    private FragmentTransaction transaction;
    private FirstFragment firstFragment;

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

        mFragmentManager = getSupportFragmentManager();
        EventBus.getDefault().register(this);
        mVp.setOffscreenPageLimit(4);

    }

    //****************Eventbas

    //跳过去弹出页面
    //一更多
    @Subscribe
    public void eventFirst(FirstEvent event) {
        transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragmentlayout, new FirstFragment(), "first");
        transaction.addToBackStack("first");//返回键返回
        transaction.commit();
    }


    //一返回
    @Subscribe
    public void HomeEvent(HomeEvent event) {
        transaction = mFragmentManager.beginTransaction();
        Fragment first = mFragmentManager.findFragmentByTag("first");
        transaction.hide(first);
        transaction.remove(first);
        transaction.commit();

    }

    //二
    @Subscribe
    public void evenSecond(SecondEvent event) {
        FragmentTransaction transaction2 = mFragmentManager.beginTransaction();
        transaction2.add(R.id.fragmentlayout, new SecondFragment(), "second");
        transaction2.addToBackStack("second");
        transaction2.commit();
    }

    //二返回
    @Subscribe
    public void HomeEvent2(HomeEvent2 event2) {
        FragmentTransaction transaction2 = mFragmentManager.beginTransaction();
        Fragment second = mFragmentManager.findFragmentByTag("second");
        transaction2.hide(second);
        transaction2.remove(second);
        transaction2.commit();
    }

    //三
    @Subscribe
    public void eventThird(ThirdEvent event) {
        FragmentTransaction transaction3 = mFragmentManager.beginTransaction();
        transaction3.add(R.id.fragmentlayout, new ThirdFragment(), "third");
        transaction3.addToBackStack("third");
        transaction3.commit();
    }

    //
    @Subscribe
    public void HomeEvent3(HomeEvent3 event3) {
        FragmentTransaction transaction3 = mFragmentManager.beginTransaction();
        Fragment third = mFragmentManager.findFragmentByTag("third");
        transaction3.hide(third);
        transaction3.remove(third);
        transaction3.commit();
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


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
