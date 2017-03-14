package com.xtec.timeline.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xtec.timeline.BuildConfig;
import com.xtec.timeline.common.Constant;
import com.xtec.timeline.net.GsonConverter.GsonConverterFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by 武昌丶鱼 on 2016/11/16.
 * Description:
 */
public class TimelineRetrofit {
    private static final String TAG = "TimelineRetrofit";

    public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;

    private TimelineApi mTimelineApi;

    public TimelineRetrofit(){
        Interceptor ssidInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                // 添加拦截器，每次上传参数后边都添加 <?format=json>
                Request originalRequest = chain.request();


                String formatType = "json";

                String client = "";
//                String client = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), Constant
//                        .AppConfigInfo.CLIENT);

                HttpUrl originalHttpUrl = originalRequest.url();
                String url  = originalHttpUrl.toString();
                if(url.indexOf("account/auth") != -1||url.contains("account/session_key")){
                    HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
//							.setQueryParameter("session_key", "")
                            .setQueryParameter("format",formatType)
                            .setQueryParameter("client",client)
                            .build();

                    Request newRequest = originalRequest.newBuilder()
                            .url(newHttpUrl)
                            .build();

                    Response newResponse = chain.proceed(newRequest);
                    return newResponse;

                }else{

                    String sessionkey = "";
//                    String sessionkey = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), Constant
//                            .AppConfigInfo.SESSIONKEY);
                    HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                            .setQueryParameter("session_key", sessionkey)
                            .setQueryParameter("format",formatType)
                            .setQueryParameter("client",client)
                            .build();

                    Request newRequest = originalRequest.newBuilder()
                            .url(newHttpUrl)
                            .build();

                    Response newResponse = chain.proceed(newRequest);
                    return newResponse;
                }
            }
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if(BuildConfig.LOG_DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ssidInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mTimelineApi = retrofit.create(TimelineApi.class);
    }



    public TimelineApi getTimelineApi() {

        return mTimelineApi;
    }

    public static class TokenInterceptor implements Interceptor {

        private static final Charset UTF8 = Charset.forName("UTF-8");
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            // try the request
            Response originalResponse = chain.proceed(request);

            /**通过如下的办法曲线取到请求完成的数据
             *
             * 原本想通过  originalResponse.body().string()
             * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
             *
             * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
             */
            ResponseBody responseBody = originalResponse.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String bodyString = buffer.clone().readString(charset);

//L.e("reyzarc","-------->"+bodyString);

            /***************************************/

            if (bodyString.contains("账号未登录或会话已过期")){//根据和服务端的约定判断token过期

                //取出本地的refreshToken
                String refreshToken = "sssgr122222222";

                /******/
                // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//				ApiService service = ServiceManager.getService(ApiService.class);
//				Call<String> call = service.refreshToken(refreshToken);
                /******/

//                String userName = PreferencesUtils.getString(MyApplication.getInstance(),
//                        Constant.AppConfigInfo.USERNAME);
//                if (TextUtils.isEmpty(userName)) {
//                    userName = PreferencesUtils.getString(MyApplication.getInstance(),Constant.AppConfigInfo.ANAME);
//                }
//                String pwd = PreferencesUtils.getString(MyApplication.getInstance(),Constant.AppConfigInfo.AUTOPWS);
//
//                HaogpsApi api = HaogpsFactory.getHaoGpsSingleton();
//                Call<HttpResultModel> call =  api.doAutoLogin(userName,pwd);
//
//
//                //要用retrofit的同步方式
////				String newToken = call.execute().body();
//
//                HttpResultModel httpResultModel = call.execute().body();
//
////L.e("reyzarc","============>"+httpResultModel.getSession_key().toString());
//
//                String session_key = httpResultModel.getSession_key();
                String session_key = "";
                /****   *****/
                //保存session_key
//                PreferencesUtils.putString(MyApplication.getInstance(),Constant.AppConfigInfo.SESSIONKEY,httpResultModel.getSession_key());
//                PreferencesUtils.putString(MyApplication.getInstance(),Constant.AppConfigInfo.AUTOPWS,httpResultModel.getAuto_pwd());

//                MyApplication.getInstance().sendBroadcast(new Intent(Constant.ACTION_AUTO_LOGIN_SUCCESS));

                // create a new request and modify it accordingly using the new token
                /****   *****/
                Request originalRequest = chain.request();


                String formatType = "json";

                String client = "";
//                String client = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), Constant
//                        .AppConfigInfo.CLIENT);

                HttpUrl originalHttpUrl = originalRequest.url();

                HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                        .setQueryParameter("session_key", session_key)
                        .setQueryParameter("format",formatType)
                        .setQueryParameter("client",client)
                        .build();

                Request newRequest = originalRequest.newBuilder()
                        .url(newHttpUrl)
                        .build();
                // retry the request

                originalResponse.body().close();
                return chain.proceed(newRequest);
            }

            // otherwise just pass the original response on
            return originalResponse;
        }
    }
}
