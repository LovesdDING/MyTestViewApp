package com.example.cz10000_001.mytestapp.glshape;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 自定义shape  基类  实现renderer
 *
 * Created by cz10000_001 on 2018/1/22.
 */

public abstract class Shape implements GLSurfaceView.Renderer {

    protected View mView ;

    public Shape(View mView){
        this.mView = mView ;
    }

    /**
     *  加载shader  片元着色器 或者 顶点着色器
     * @param type
     * @param shaderCode
     * @return
     */
    public int loadShader(int type,String shaderCode){
        //根据type创建顶点着色器或者片元着色器
        int shader = GLES20.glCreateShader(type);
        //将资源加入到着色器中，并编译
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }



}
