package com.example.cz10000_001.mytestapp;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

public class TestDpActivity extends AppCompatActivity {

    private CollapsingToolbarLayout ctLayout ;
    private Toolbar tb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dp);

        initLayout() ;
    }

    private void initLayout() {

        tb = (Toolbar) findViewById(R.id.tb);
        ctLayout = (CollapsingToolbarLayout) findViewById(R.id.ctlayout);

        tb.setTitle("HD  Test");
        tb.setNavigationIcon(R.drawable.back_2);
        setSupportActionBar(tb);
        //显示左上角的返回按钮
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ctLayout.setTitle("");
//        tb.setTitle("美女APP");
        //不使用下方的大标题
//        ctLayout.setTitleEnabled(false);
        ctLayout.setTitle("哈哈哈");
        ctLayout.setCollapsedTitleTextColor(Color.WHITE);
        ctLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
//        ctLayout.setExpandedTitleGravity(Gravity.CENTER|Gravity.TOP);
    }
}

