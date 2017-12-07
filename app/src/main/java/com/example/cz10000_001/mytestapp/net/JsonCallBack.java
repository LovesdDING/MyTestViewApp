package com.example.cz10000_001.mytestapp.net;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 对网络框架做的json解析封装
 *
 * @author 霍昌峰
 * @date 2017/10/17 0017
 */
public abstract class JsonCallBack<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override

    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (type != null) {
            data = gson.fromJson(jsonReader, type);
        }

        if (clazz != null) {
            data = gson.fromJson(jsonReader, clazz);
        }

        return data;
    }
}
