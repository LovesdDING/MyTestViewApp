package com.example.cz10000_001.mytestapp.glshape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *
 * 三角形
 * 实现renderer  接口 renderer接口里三个方法
 * onSUrfaceCreated  onDrawFrame  onSUrfaceChanged
 * Created by cz10000_001 on 2018/1/19.
 */

public class Triangle extends Shape {

    private FloatBuffer vertexBuffer ;

    //顶点着色器
       private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = vPosition;" +
            "}";
    //片元着色器
        private final String fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

        private int mProgram ;


        private int mMatrixHandler ;

        //设置颜色值 依次为 红绿蓝和 透明通道值
        float []colors = {1.0f,1.0f,1.0f,1.0f} ;

    static final int COORDS_PER_VERTEX = 3;//每个顶点以三个值 声明坐标位置
    static float triangleCoords[] = {  //顶点集  四个顶点  以0，0，0位坐标原点
//            -0.5f,0.5f,0.0f,  //top left
//            0.5f,  0.5f, 0.0f, // top right
//            -0.5f, -0.5f, 0.0f, // bottom left
//            0.5f, -0.5f, 0.0f  // bottom right
            -1.0f,0.0f,0.0f,
            -0.5f,-0.8f,0.0f,
            0.5f,-0.8f,0.0f,
            1f,0.0f,0.0f,
            0.5f,0.8f,0.0f,
            -0.5f,0.8f,0.0f,
    };

    private int mPositionHandle;
    private int mColorHandle;

    private float[] mViewMatrix=new float[16];

    //顶点个数
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    //顶点之间的偏移量
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 每个顶点四个字节



    public Triangle(View mView){
        super(mView);

        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4) ;
        bb.order(ByteOrder.nativeOrder()) ;

        vertexBuffer = bb.asFloatBuffer() ;
        vertexBuffer.put(triangleCoords) ;
        vertexBuffer.position(0) ;

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram();
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader);
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader);
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram);

    }





    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);

        //获取顶点着色器的vPosition成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, colors, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
