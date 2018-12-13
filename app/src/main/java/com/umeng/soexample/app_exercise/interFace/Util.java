package com.umeng.soexample.app_exercise.interFace;

import com.umeng.soexample.app_exercise.fragment.bean.LabelBean;
import com.umeng.soexample.app_exercise.user.information.InformBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * data:2018/12/1
 * author: HJL (磊)
 * function:
 */
public interface Util {
    //登录
    String LOGIN_URL = "http://172.17.8.100/small/user/v1/login";
    //注册
    String REGISTER_URL = "http://172.17.8.100/small/user/v1/register";
    //轮播图
    String BANNER_URL = "http://172.17.8.100/small/commodity/v1/bannerShow";
    //首页展示
    String HOME_URL = "http://172.17.8.100/small/commodity/v1/commodityList";

    //更多商品
    @GET("findCommodityListByLabe")
    Observable<LabelBean> getlabel(@Query("labelId") String labelId, @Query("page") String page, @Query("count") String count);

    //个人信息
    @GET("user/verify/v1/getUserById")
    Observable<InformBean> getInform();

}
