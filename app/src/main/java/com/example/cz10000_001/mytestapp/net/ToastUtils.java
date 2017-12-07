package com.example.cz10000_001.mytestapp.net;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author  lv
 * @date 2017/10/17 0017 10:02
 */
public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String string) {
        if (toast == null) {
            toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        }
        toast.setText(string);
        toast.show();
    }
}
