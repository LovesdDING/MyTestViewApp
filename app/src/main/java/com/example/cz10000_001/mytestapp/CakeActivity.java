package com.example.cz10000_001.mytestapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cz10000_001.mytestapp.model.CakeBean;
import com.example.cz10000_001.mytestapp.view.CakeView;

import java.util.ArrayList;
import java.util.List;

public class CakeActivity extends AppCompatActivity {

    private CakeView cakeView ;

    private List<CakeBean> beans;
    private String[] names = {"中华", "七匹狼", "云烟", "娇子", "黄金叶", "兰州", "小熊猫"};
    private float[] values = {2f, 2f, 3f, 4f, 5f, 6f, 7f};
    private int[] colArrs = {Color.RED, Color.parseColor("#4ebcd3"), Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.parseColor("#f68b2b"), Color.parseColor("#6fb30d")};//圆弧颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);

//        cakeView = (CakeView) findViewById(R.id.cakeView);

        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        beans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            CakeBean bean = new CakeBean();
            bean.name = names[i];
            bean.value = values[i];
            bean.mColor = colArrs[i];
            beans.add(bean);
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        CakeView cake_view = (CakeView) findViewById(R.id.cakeView);
        cake_view.setData(beans);
    }
}
