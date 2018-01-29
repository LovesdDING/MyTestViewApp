package com.example.cz10000_001.mytestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cz10000_001.mytestapp.glshape.Ball;
import com.example.cz10000_001.mytestapp.glshape.BallWithLight;
import com.example.cz10000_001.mytestapp.glshape.Cone;
import com.example.cz10000_001.mytestapp.glshape.Cube;
import com.example.cz10000_001.mytestapp.glshape.Cylinder;
import com.example.cz10000_001.mytestapp.glshape.FGLView;
import com.example.cz10000_001.mytestapp.glshape.Oval;
import com.example.cz10000_001.mytestapp.glshape.Square;
import com.example.cz10000_001.mytestapp.glshape.Triangle;
import com.example.cz10000_001.mytestapp.glshape.TriangleColorFull;
import com.example.cz10000_001.mytestapp.glshape.TriangleWithCamera;
import com.orhanobut.logger.Logger;

/**
 *   test  opengl   基于opengl ES 2.0
 */
public class TestGlActivity extends AppCompatActivity{

    private FGLView mGlView;
    private Button btn1;
    private Button btn2;
    private TextView tv1;
    private FGLView mGlView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gl);

        mGlView = (FGLView) findViewById(R.id.mGLView) ;
        mGlView1 = (FGLView) findViewById(R.id.mGLView1);


        mGlView.setShape(Cube.class);
        mGlView1.setShape(Triangle.class);

    }


}
