package com.example.cz10000_001.mytestapp.rsblur;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsic;
import android.renderscript.ScriptIntrinsicBlur;

/**
 *   renderscript 模糊图片处理
 *      renderscript  渲染效率高
 *      但是 如果想要实现动态模糊  ，即便使用renderscript这种高效方式 但是 在进行实时模糊的时候 仍然很是消耗时间
 *      耗cpu 。所以 想要实现 动态实时模糊效果  可以折中的方法：
 *      先将图片进行最大程度的模糊  ，再将原图放置在模糊之后的图片上 通过不断改变原图的透明度来实现实时模糊的效果
 *
 *
 *
 * Created by cz10000_001 on 2018/2/27.
 */

public class BlurBitmap {
    /**
     * 图片缩放比例
     */
    private static final float BITMAP_SCALE = 0.4f;
    /**
     * 最大模糊度(在0.0到25.0之间)
     */
    private static final float BLUR_RADIUS = 25f;

    /**
     * 模糊图片的具体方法
     * @param context
     * @param image
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap image){
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth()*BITMAP_SCALE) ;
        int height = Math.round(image.getHeight()*BITMAP_SCALE) ;


        // 将缩小后的图片做为预渲染的图片。

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image,width,height,false) ;
        // 创建一张渲染后的输出图片。
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap) ;
        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context) ;
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript =ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)) ;
        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs,inputBitmap);
        Allocation tmpOut= Allocation.createFromBitmap(rs,outputBitmap) ;
        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(BLUR_RADIUS);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);


        return outputBitmap ;




    }
}
