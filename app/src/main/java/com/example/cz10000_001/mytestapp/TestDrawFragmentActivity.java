package com.example.cz10000_001.mytestapp;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.cz10000_001.mytestapp.view.SlideMenu;

import java.util.logging.Logger;

/**
 *   drawerlayout
 *
 *   右边布局 阴影效果
 */
public class TestDrawFragmentActivity extends AppCompatActivity {

//    private DrawerLayout drawerlayout ;
    private SlideMenu slideMenu ;
    private ImageView ivBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_draw_fragment);

//        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//
//
        slideMenu = (SlideMenu) findViewById(R.id.slide);
        ivBack = (ImageView) findViewById(R.id.iv);
//
//        drawerlayout.setScrimColor(Color.TRANSPARENT);
//        com.orhanobut.logger.Logger.i("drawer ::"+drawerlayout.isDrawerOpen(Gravity.START));
//
//
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slideMenu.toggle();  //  切换 drawerlayout 开启 关闭

            }
        });
    }



}
