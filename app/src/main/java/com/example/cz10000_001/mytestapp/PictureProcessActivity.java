package com.example.cz10000_001.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cz10000_001.mytestapp.view.ColorFilter;
import com.example.cz10000_001.mytestapp.view.ContrastColorFilter;
import com.example.cz10000_001.mytestapp.view.SGLView;

public class PictureProcessActivity extends AppCompatActivity implements View.OnClickListener {


    private SGLView sglView;
    private boolean isHalf = false ;  //true  处理一半  false  全部
    private Button btn;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_process);

        sglView = (SGLView) findViewById(R.id.glView);
        btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(this);
        btn1 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn3);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn4);
        btn3.setOnClickListener(this);

        btn4 = (Button) findViewById(R.id.btn5);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.btn6);
        btn5.setOnClickListener(this);

        btn6 = (Button) findViewById(R.id.btn7);
        btn6.setOnClickListener(this);


        sglView.getRender().refresh();
        sglView.getRender().getFilter().setHalf(true);
        sglView.requestRender();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1: //全部处理
                sglView.getRender().refresh();
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn2: //原图
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.NONE));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn3:  //黑白
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.GRAY));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn4: //模糊
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.BLUR));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn5: //冷色调
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.COOL));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn6: //暖色调
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.WARM));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
            case R.id.btn7: //放大镜
                sglView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.MAGN));
                sglView.getRender().getFilter().setHalf(false);
                sglView.requestRender();
                break;
        }
    }
}
