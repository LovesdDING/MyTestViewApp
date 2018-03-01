package com.example.cz10000_001.mytestapp.anim;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;

/**
 *  4.X  手动实现转场动画效果
 *  实现原理：  在第一个Activity 中获取到要共享的元素  然后将这个元素 传递到下一个页面
 *  在第二个页面获取到 该元素对象 进行缩放 平移操作
 *
 *
 */
public class AnimeManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_manual);

        setSupportActionBar((Toolbar) findViewById(R.id.activity_anime_toolbar));
    }


    public void  imageClick(View view){
        Intent intent = new Intent(this,AnimeDetailActivity.class) ;
        // 创建一个 rect 对象来存储共享元素位置信息
        Rect rect = new Rect() ;
        // 获取元素位置信息
        view.getGlobalVisibleRect(rect) ;
        // 将位置信息附加到 intent 上
        intent.setSourceBounds(rect);

        CustomImage customImage = (CustomImage) view;
        intent.putExtra(AnimeDetailActivity.EXTRA_IMAGE,customImage.getImageId())  ;
        startActivity(intent);

        // 屏蔽 Activity 默认转场效果
        overridePendingTransition(0,0);

    }
}
