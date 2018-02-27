package com.example.cz10000_001.mytestapp.anim;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.example.cz10000_001.mytestapp.R;

public class SecondCustomTransActivity extends AppCompatActivity {
    private ViewGroup container;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_custom_trans);

        container = (ViewGroup) findViewById(R.id.container);

//        getSupportActionBar().setTitle("第二个Activity");


        //定义ArcMotion
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);

        //插值器，控制速度
        Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);

        //实例化自定义的ChangeBounds
        CustomChangeBounds changeBounds = new CustomChangeBounds();

        changeBounds.setPathMotion(arcMotion);
        changeBounds.setInterpolator(interpolator);
        changeBounds.addTarget(container);

        //将切换动画应用到当前的Activity的进入和返回
        getWindow().setSharedElementEnterTransition(changeBounds);
        getWindow().setSharedElementReturnTransition(changeBounds);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void dismiss(View view) {
        finishAfterTransition();
    }
}
