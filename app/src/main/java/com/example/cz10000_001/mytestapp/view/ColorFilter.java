package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by cz10000_001 on 2018/1/29.
 */

public class ColorFilter extends AFilter{
    private Filter filter;

    private int hChangeType;
    private int hChangeColor;

    public ColorFilter(Context context, Filter filter) {
        super(context, "filter/default_vertex.sh", "filter/color_fragment.sh");
        this.filter=filter;
    }

    @Override
    public void onDrawSet() {
        GLES20.glUniform1i(hChangeType,filter.getType());
        GLES20.glUniform3fv(hChangeColor,1,filter.data(),0);
    }

    @Override
    public void onDrawCreatedSet(int mProgram) {
        hChangeType=GLES20.glGetUniformLocation(mProgram,"vChangeType");
        hChangeColor=GLES20.glGetUniformLocation(mProgram,"vChangeColor");
    }

    public enum Filter{

        NONE(0,new float[]{0.0f,0.0f,0.0f}),
        GRAY(1,new float[]{0.299f,0.587f,0.114f}),
        COOL(2,new float[]{0.0f,0.0f,0.1f}),  //冷色调 但以增加蓝通道的值
        WARM(2,new float[]{0.1f,0.1f,0.0f}),  //暖色调 增加红绿通道的值
        BLUR(3,new float[]{0.006f,0.004f,0.002f}),//模糊
        MAGN(4,new float[]{0.0f,0.0f,0.6f});  //  第一个参数 相对右移  第二个参数 相对上移 第三个参数  控制放大范围


        private int vChangeType;
        private float[] data;

        Filter(int vChangeType,float[] data){
            this.vChangeType=vChangeType;
            this.data=data;
        }

        public int getType(){
            return vChangeType;
        }

        public float[] data(){
            return data;
        }

    }

}
