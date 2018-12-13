package com.umeng.soexample.app_exercise.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.soexample.app_exercise.App;
import com.umeng.soexample.app_exercise.R;
import com.umeng.soexample.app_exercise.login.LoginActivity;
import com.umeng.soexample.app_exercise.user.information.InformActivity;
import com.umeng.soexample.app_exercise.user.information.InformBean;
import com.umeng.soexample.app_exercise.user.information.mvp.InformPresenter;
import com.umeng.soexample.app_exercise.user.information.mvp.InformView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * data:2018/12/3
 * author: HJL (磊)
 * function:
 */
public class FragmentMy extends Fragment {
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.my_head_pic)
    CircleImageView mMyHeadPic;
    @BindView(R.id.my_inform)
    TextView mMyInform;
    @BindView(R.id.my_Circle)
    TextView mMyCircle;
    @BindView(R.id.my_foot)
    TextView mMyFoot;
    @BindView(R.id.my_wallet)
    TextView mMyWallet;
    @BindView(R.id.my_address)
    TextView mMyAddress;
    private View view;
    private Unbinder unbinder;
    private SharedPreferences user;
    private boolean login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.f_my_item, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMyHeadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        user = App.sContext.getSharedPreferences("user", Context.MODE_PRIVATE);
        String headPic = user.getString("headPic", "");
        String nickName = user.getString("nickName", "");
        login = user.getBoolean("login", true);

        Glide.with(getActivity()).load(headPic).into(mMyHeadPic);
        mNickname.setText(nickName);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_inform, R.id.my_Circle, R.id.my_foot, R.id.my_wallet, R.id.my_address})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_inform:
                if (!login) {
                    Intent intent = new Intent(getActivity(), InformActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.my_Circle:
                break;
            case R.id.my_foot:
                break;
            case R.id.my_wallet:
                break;
            case R.id.my_address:
                break;
        }
    }


}
