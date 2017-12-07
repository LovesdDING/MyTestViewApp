package com.example.cz10000_001.mytestapp.net;

import android.content.Context;
import android.text.TextUtils;

import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 网络异常提示
 *
 * @author lv
 * @date 2017/10/17 0017 10:21
 */
public class NetExceptionToast {

    /**
     * 网络连接异常时的Toast提示
     *
     * @param exception 异常
     * @param context   上下文
     * @param msg       显示的Toast
     */
    public static void netExceptionToast(Throwable exception, Context context, String msg) {
        if (exception != null) {
            exception.printStackTrace();
            Logger.e(exception, "net");
        } else {
            return;
        }

        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            ToastUtils.showToast(context, "网络连接失败");
        } else if (exception instanceof SocketTimeoutException) {
            ToastUtils.showToast(context, "网络连接超时");
        } else if (exception instanceof HttpException) {
            ToastUtils.showToast(context, "服务器连接异常");
        } else if (exception instanceof StorageException) {
            ToastUtils.showToast(context, "SD卡不存在或者没有权限");
        } else if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showToast(context, msg);
        } else {
            ToastUtils.showToast(context, "未知错误");
        }
    }

    /**
     * 网络连接异常时的Toast提示
     *
     * @param exception 异常
     * @param context   上下文
     */
    public static void netExceptionToast(Throwable exception, Context context) {
        netExceptionToast(exception, context, "");
    }
}
