package com.example.cz10000_001.mytestapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cz10000_001.mytestapp.base.Person;
import com.example.cz10000_001.mytestapp.constpool.Api;
import com.example.cz10000_001.mytestapp.net.JsonCallBack;
import com.example.cz10000_001.mytestapp.util.BaseCallback;
import com.example.cz10000_001.mytestapp.util.DataUtil;
import com.example.cz10000_001.mytestapp.util.GetMD5;
import com.example.cz10000_001.mytestapp.util.OkHttpHelper;
import com.example.cz10000_001.mytestapp.util.PreferenceUtil;
import com.example.cz10000_001.mytestapp.util.ToastUtil;
import com.lzy.okgo.OkGo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *   测试 APP进程杀死 保存当前数据
 *   再次进入时   能直接获取到当前数据
 */
public class TestActivity1 extends AppCompatActivity {

    private static final String TAG = TestActivity1.class.getSimpleName();
    private Context mContext ;
    private final static String PackageName = "com.example.cz10000_001.mytestapp" ;
    String acName = "TestActivity2" ;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private TextView tvShow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        mContext = getApplicationContext() ;

        Bmob.initialize(this, Api.BOMBID);  //初始化 Bmob

        tvShow = (TextView) findViewById(R.id.tvshow);



    }

    /**
     * 初始化 添加数据
     */
    private void initData() {
        Person p2 = new Person(4,"上官") ;
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    ToastUtil.show(mContext,"添加数据成功，返回objectId为："+s);
                }else{
                    ToastUtil.show(mContext,"创建数据失败：" + e.getMessage());
                }
            }
        }) ;

    }

    private void testGo() {
        String className = PackageName+".TestActivity3" ;
        Intent intent = new Intent() ;
        try {
            Log.e(TAG, "testGo: "+className );
            intent.setClass(this,Class.forName(className)) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    private void initActivity() {
        boolean isExitError = PreferenceUtil.getBoolean(mContext,"isExitError",false) ;
        if(isExitError){
            DataUtil.PageNum = PreferenceUtil.getInt(mContext,"pageNum") ;
            if(DataUtil.PageNum == 3){
                startActivity(new Intent(this,TestActivity3.class));
            }else if(DataUtil.PageNum == 2){
                startActivity(new Intent(this,TestActivity2.class));
            }else{
                startActivity(new Intent(this,TestActivity1.class));
            }
        }else{

        }

    }

    public void goNext1(View view) {

        initData();

//        startActivity(new Intent(this,TestActivity2.class));
//        testLogin() ;
    }






    // source=ios&version=1.1&data={"type":1,"size":4,"phone":18170851024,"smsCode":"SMS_105030002"}&ciphertext
    private void testLogin() {
//        Map<String,String> map = new HashMap<>() ;
//        map.put("source","android") ;
//        map.put("version","1.1") ;
//        map.put("data",toJsonObject()) ;
//        map.put("ciphertext","") ;


//        OkHttpHelper.getInstance()
//                .post("http://192.168.1.107:8080/jlsService/aliyun/sms/getSmsCode", map, new BaseCallback<String>() {
//            @Override
//            public void onRequestBefore(Request request) {
//
//            }
//
//            @Override
//            public void onFailure(Request request, IOException e) {
////                Log.e(TAG, "onFailure: "+e.printStackTrace());
//            }
//
//            @Override
//            public void onSuccess(Response response, String s) {
//                Log.e(TAG, "onSuccess: "+s );
//            }
//
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//                Log.e(TAG, "onError: "+response.toString() );
//            }
//
//            @Override
//            public void OnResponse(Response response) {
//
//            }
//        });






//        RequestBody body = RequestBody.create(JSON,toJsonObject2()) ;
//
//
//        OkHttpClient client = new OkHttpClient() ;
//        Request request = new Request.Builder()
//                .url("http://192.168.1.107:8080/jlsService/aliyun/sms/getSmsCode")
////                            .addHeader("content-type","application/json;charset:utf-8")
//                            .post(body)
//                            .build() ;
//
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure: "+e.getMessage() );
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse:  success"+response.body().toString()+"-"+response.message() );
//            }
//
//
//        });

        toJsonInfo() ;
        Log.e(TAG, "testLogin: Jsoninfo-"+toJsonInfo() );
        String signStr = "userid=5"+"czactivity"+"czadmin2017"  ;
        Log.e(TAG, "testLogin: signStr--"+signStr );
        String sign = GetMD5.getMD5(signStr) ;
        Log.e(TAG, "testLogin: sign--"+sign );

    }


    public String toJsonInfo(){

        String jsonResult = "" ;
        JSONObject jsonObject = new JSONObject() ;
        try {

            jsonObject.put("userid","5") ;
            jsonResult = jsonObject.toString() ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "toJsonInfo: "+jsonResult );
        return jsonResult ;
    }


    //  生成json对象
    public String toJsonObject(){


        String jsonResult = "" ;
        JSONObject jsonObject = new JSONObject() ;
        try {

            jsonObject.put("type","1") ;
            jsonObject.put("size","4") ;
            jsonObject.put("phone","15617326123") ;
            jsonObject.put("smsCode","SMS_105030002") ;
            jsonResult = jsonObject.toString() ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "expertScoretoJson: "+jsonResult );
        return jsonResult ;
    }


    //在包裹一个json对象
    public String toJsonObject2(){
        String jsonResult = "" ;
        JSONObject jsonObject = new JSONObject() ;
        try {

            jsonObject.put("source","android") ;
            jsonObject.put("version","1.1") ;
            jsonObject.put("data",toJsonObject()) ;
            jsonObject.put("ciphertext","") ;
            jsonResult = jsonObject.toString() ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "expertScoretoJson2: "+jsonResult );
        return jsonResult ;
    }

    public void getData(View view) { //从数据库 拿到一个数据
        BmobQuery<Person> bmobQuery = new BmobQuery<>() ;
        bmobQuery.getObject("c8a55cfjkd6", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                    if(e == null){
                        ToastUtil.show(mContext,"查询成功,用户："+person.getName());
                    }else{
//                        ToastUtil.show(mContext,"查询失败");
                        Log.e(TAG, "done: 查询失败:" +e.getMessage());
                    }
            }
        }) ;
    }

    //更新数据库数据
    public void updateData(View view) {
        final Person p = new Person() ;
        p.setName("双飞燕");
        p.update("7f2138e358", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.show(mContext,"更新成功"+p.getUpdatedAt());
                }else{
                    Log.e(TAG, "done: 更新失败:"+e.getMessage() );
                }
            }
        }) ;
    }

    //删除一行 数据库数据
    public void deleteData(View view) {
        final Person p = new Person() ;
        p.setObjectId("c8a55cfed6");
        p.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.show(mContext,"删除成功"+p.getUpdatedAt());
                }else{

                    Log.e(TAG, "done: 删除失败:"+e.getMessage() );
                }
            }
        }) ;
    }


    //
//    loginType	登录类型	int	Y	0-游客登录，1-短信登录	N
//    userToken	用户令牌	String	Y	有就传，没有就传null	N
//    versionNo	版本号	String	Y		N
//    loginIp	登录IP	String	Y		Y
//    registerPhoneType	手机型号	String	Y		N
//    registerDeviceMac	Mac地址	String	Y		Y
//    registerSource	注册源	String	Y	全大写	N
//    userPhone	用户手机	String	N	非游客外登录必填	Y
//    verifyCode

    //  POST /user/login
    public void dorequest(View view) {
        OkGo.<String>post("")
                .execute(new JsonCallBack<String>(String.class) {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
