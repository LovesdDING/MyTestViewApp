package com.example.cz10000_001.mytestapp.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okhttp  封装
 * Created by Administrator on 2017/5/4.
 */

public class OkHttpHelper {
    private static OkHttpClient okHttpClient;
    private Gson mGson;
    private Handler mHandler;

    private OkHttpHelper() {
        okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpHelper getInstance() {

        return new OkHttpHelper();
    }

    private void doRequest(Request request, final BaseCallback callback) {
        callback.onRequestBefore(request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call.request(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.OnResponse(response);
                if (response.isSuccessful()) {
                    String resultStr = response.body().string();
                    Log.i("wangluo","请求到的数据：" + resultStr);
                    if (callback.mType == String.class) {
                        callbackSuccess(callback, response, resultStr);
                    } else {
                        try {
                            Object object = mGson.fromJson(resultStr, callback.mType);
                            callbackSuccess(callback, response, object);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callbackError(callback, response, response.code(), e);
                        }
                    }

                } else {
                    callback.onError(response, response.code(), null);
                }
            }
        });
    }


    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);

        doRequest(request, callback);
    }

    public void post(String url, Map<String, String> params, BaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        doRequest(request, callback);
    }

    private Request buildRequest(String url, Map<String, String> params, HttpMethodType methodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {
            RequestBody body = buildFormData(params);
            builder.post(body);
        }

        return builder.build();
    }

    private RequestBody buildFormData(Map<String, String> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                body.add(entry.getKey(), entry.getValue());

                Log.i("wangluoqingqiu ","key::" + entry.getKey() + "--value::" + entry.getValue());
            }
        }
        return body.build();
    }

    enum HttpMethodType {
        GET, POST
    }

    private void callbackSuccess(final BaseCallback callback, final Response response, final Object object) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, object);
            }
        });

    }

    private void callbackError(final BaseCallback callback, final Response response, final int code, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, code, e);
            }
        });

    }
}
