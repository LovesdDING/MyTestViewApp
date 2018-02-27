package com.example.cz10000_001.mytestapp.anim;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.example.cz10000_001.mytestapp.R;

/**
 * explode  爆炸效果
 * fade  渐变效果
 * slide 平移效果
 *    设置  Activity 进入时  退出时 重新进入时的动画效果
 */
public class WindowTransitionsActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);  //这一句 加不加 都不影响动画效果的实现
        setContentView(R.layout.activity_window_transitions);


        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(explode);
        getWindow().setReenterTransition(explode);
        getWindow().setEnterTransition(explode);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCellClick(View view) {
        Intent intent = new Intent(this, SecondWindowTransActivity.class);

        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
