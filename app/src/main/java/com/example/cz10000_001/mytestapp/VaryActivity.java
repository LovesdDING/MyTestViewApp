package com.example.cz10000_001.mytestapp;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cz10000_001.mytestapp.view.vary.VaryRender;

public class VaryActivity extends AppCompatActivity {

    private GLSurfaceView glsv;
    private VaryRender render ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vary);


        glsv = (GLSurfaceView) findViewById(R.id.glsurfaceView);
        glsv.setEGLContextClientVersion(2);
        glsv.setRenderer(render = new VaryRender(getResources()));
        glsv.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


    }


}
