package com.example.cz10000_001.mytestapp.util;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 加载提示
 * Created by Administrator on 2017/5/4.
 */

public abstract class BaseCallback<T> {
    Type mType;
    public BaseCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }



    static Type getSuperclassTypeParameter(Class<?> subclass) {

        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;

        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    public abstract void onRequestBefore(Request request);

    public abstract void onFailure(Request request, IOException e);

    public abstract void onSuccess(Response response, T t);

    public abstract void onError(Response response, int code, Exception e);

    public abstract void OnResponse(Response response);
}
