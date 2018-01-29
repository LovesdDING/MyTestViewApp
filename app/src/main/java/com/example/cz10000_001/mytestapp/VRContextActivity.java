package com.example.cz10000_001.mytestapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cz10000_001.mytestapp.view.vr.SkySphere;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * VR 球体 Activity
 */
public class VRContextActivity extends AppCompatActivity implements GLSurfaceView.Renderer ,SensorEventListener{


    private GLSurfaceView glsv;
    private SensorManager sensorManager ;
    private Sensor mRotaSensor;

    private float[] matrix =new float[16] ;
    private SkySphere mSkySphere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrcontext);

        sensorManager  = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL) ; //sensor  传感器列表

        //判断是否存在rotation vector sensor
        mRotaSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);



        
        glsv = (GLSurfaceView) findViewById(R.id.vrglsv);
        glsv.setEGLContextClientVersion(2);
        glsv.setRenderer(this);
        glsv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);


        mSkySphere=new SkySphere(this.getApplicationContext(),"vr/360sp.jpg");

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mRotaSensor,SensorManager.SENSOR_DELAY_GAME) ;
        glsv.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        glsv.onPause();
    }



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mSkySphere.create();
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_FRONT);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mSkySphere.setSize(width, height);
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(1,1,1,1);
        mSkySphere.draw();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorManager.getRotationMatrixFromVector(matrix,event.values);
        mSkySphere.setMatrix(matrix);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
