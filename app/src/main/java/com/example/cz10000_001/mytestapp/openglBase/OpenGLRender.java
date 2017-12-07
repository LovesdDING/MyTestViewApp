package com.example.cz10000_001.mytestapp.openglBase;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by cz10000_001 on 2017/11/2.
 */

public class OpenGLRender implements GLSurfaceView.Renderer {

    Square square ;

    //初始化 一些不常变化的值
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0f); //背景黑色
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glClearDepthf(1.0f);  //depth  buffer  setup
        // Enables depth testing.
        gl10.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.
        // The type of depth testing to do.
        gl10.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.
        // Really nice perspective calculations.
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.
                GL10.GL_NICEST);
    }


    //  如果 设备支持横竖屏 变化时  横 纵向 变化时 重新绘制设置的比率
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        // Sets the current view port to the new size.
        gl10.glViewport(0, 0, width, height);// OpenGL docs.
        // Select the projection matrix
        gl10.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
        // Reset the projection matrix
        gl10.glLoadIdentity();// OpenGL docs.
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl10, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl10.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
        // Reset the modelview matrix
        gl10.glLoadIdentity();// OpenGL docs.
    }

    //实际的绘图操作
    @Override
    public void onDrawFrame(GL10 gl10) {
        // Clears the screen and depth buffer.
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | // OpenGL docs.
                GL10.GL_DEPTH_BUFFER_BIT);

        // Replace the current matrix with the identity matrix
        gl10.glLoadIdentity();

        // Translates 4 units into the screen.
        gl10.glTranslatef(0, 0, -4);
         // Draw our square.
         square.draw(gl10); // ( NEW )

    }
}
