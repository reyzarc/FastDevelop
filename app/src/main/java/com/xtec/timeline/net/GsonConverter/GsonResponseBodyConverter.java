package com.xtec.timeline.net.GsonConverter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.xtec.timeline.common.Constant;
import com.xtec.timeline.model.HttpResultModel;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by 冯浩 on 2016/4/25.
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = "GsonResponseBodyConverter";

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String s = value.string();


        HttpResultModel result = gson.fromJson(s, HttpResultModel.class);

        if (result != null) {
            int resultCode = result.getRet();

            if (resultCode == Constant.ERROR) {
                String errorMsg = result.getMsg();
                Log.e("reyzarc","----->"+errorMsg);
                if(errorMsg.contains("状态异常")){
//                    MyApplication.logout();
               }

//                if ("账号未登录或会话已过期".equals(errorMsg)) {
//                    String userName = PreferencesUtils.getString(MyApplication.getInstance(),
//                            Constant.AppConfigInfo.USERNAME);
//                    if (TextUtils.isEmpty(userName)) {
//                        userName = PreferencesUtils.getString(MyApplication.getInstance(),Constant.AppConfigInfo.ANAME);
//                    }
//                    String pwd = PreferencesUtils.getString(MyApplication.getInstance(),Constant.AppConfigInfo.AUTOPWS);
//
//                    MyApplication.autoLogin(userName,pwd);
//
//                } else if (errorMsg.contains("账户状态异常")) {
////                    MyApplication.logout();
//                }
            }
        }


        JsonReader jsonReader = gson.newJsonReader(new StringReader(s));
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}

