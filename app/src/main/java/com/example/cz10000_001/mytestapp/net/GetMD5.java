package com.example.cz10000_001.mytestapp.net;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：霍昌峰 on 2016/5/31 18:11<p>
 * 邮箱：553805864@qq.com<p>
 */
public class GetMD5 {

    public static String getMD5(String val) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            //加密
            byte[] m = md5.digest();
            return getString(m);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*private static String getString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            sb.append(aB);
        }
        return sb.toString();
    }*/

    private static String getString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            //把每个字节转换成16进制数
            int d = b & 0xff;//只保留后两位数
            String herString = Integer.toHexString(d);//把int类型数据转为16进制字符串表示
            //如果只有一位，则在前面补0.让其也是两位
            if (herString.length() == 1) {//字节高4位为0
                herString = "0" + herString;//拼接字符串，拼成两位表示
            }
            sb.append(herString);
        }
        return sb.toString();
    }
}
