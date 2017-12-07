package com.example.cz10000_001.mytestapp.net;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * 网络请求接口参数处理工具类
 *
 * @author 霍昌峰
 * @date 2017-10-23 11:24
 */
public class HandlePara {

    private static final String TAG = "HandlePara";
    private LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

    private String secret = "ASSASSIN";
    private String source = "ANDROID";
    private StringBuilder sb = new StringBuilder(secret);

    private static HandlePara hp;

    public static HandlePara getInstance() {
        hp = new HandlePara();
        return hp;
    }

    /**
     * 获取接口参数需要的json
     */
    public String getParaData() {
        String data = GsonUtil.toJson(hashMap);
//        Logger.json(data);
        return data;

    }

    /**
     * 获取参数加密后的密文<br>
     * 所有参数添加完成后调用
     */
    public String getCipherText() {
        sb.append("&").append(source);
//        Logger.d(sb.toString(), "密文");
//        Logger.d(GetMD5.getMD5(sb.toString()));
        return GetMD5.getMD5(sb.toString());
    }

    /**
     * 添加参数
     */
    public HandlePara addPara(String key, String value) {
        hashMap.put(key, value);
        sb.append("&").append(key).append("=").append(value);
        return hp;
    }



    //  生成json对象
    public String toJsonObject(){


        String jsonResult = "" ;
        JSONObject jsonObject = new JSONObject() ;
        try {

            jsonObject.put("password",GetMD5.getMD5("123456")) ;
            jsonObject.put("username","admin") ;
            Log.e(TAG, "toJsonObject: "+GetMD5.getMD5("123456") );
            jsonResult = jsonObject.toString() ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "expertScoretoJson: "+jsonResult );
        return jsonResult ;
    }
}
