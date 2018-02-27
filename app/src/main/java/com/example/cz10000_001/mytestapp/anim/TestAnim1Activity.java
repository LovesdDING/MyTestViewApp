package com.example.cz10000_001.mytestapp.anim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cz10000_001.mytestapp.R;


/**
 *  @author lv
 *  created 2018/2/23
 *  api  21后 系统内置了  Activity之间的切换动画
 *  效果更酷
 *
 *  切换动画效果实现 
 *
 */
public class TestAnim1Activity extends AppCompatActivity {

    private Button circularRevealBtn;
    private Button windowTransitionsActivity;
    private Button sharedElement;
    private Button custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anim1);

        circularRevealBtn = (Button) findViewById(R.id.circularRevealBtn);
        windowTransitionsActivity = (Button) findViewById(R.id.windowTransitionsActivity);
        sharedElement = (Button) findViewById(R.id.sharedElement);
        custom = (Button) findViewById(R.id.custom);
    }


    public void onClick(View v) {
        if (v == circularRevealBtn) {
            Intent intent = new Intent(this, CircularRevealActivity.class);
            startActivity(intent);
        } else if (v == windowTransitionsActivity) {
            Intent intent = new Intent(this, WindowTransitionsActivity.class);
            startActivity(intent);
        } else if (v == sharedElement) {
            Intent intent = new Intent(this, SharedElementActivity.class);
            startActivity(intent);
        } else if (v == custom) {
            Intent intent = new Intent(this, CustomTransitionActivity.class);
            startActivity(intent);
        }
    }
}
