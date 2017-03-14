package com.xtec.timeline.net;

import com.xtec.timeline.model.HttpResultModel;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 武昌丶鱼 on 2016/11/16.
 * Description:api
 */
public interface TimelineApi {


    @FormUrlEncoded
    @POST("account/auth")
    Observable<HttpResultModel> login(@Field("account") String username, @Field("password") String password);

    /**
     * 账户信息
     */
    @GET("account/info")
    Observable<HttpResultModel> userInfo();

    @FormUrlEncoded
    @POST("online/list")
    Observable<HttpResultModel> onlineList(@FieldMap Map<String, String> params);
}
