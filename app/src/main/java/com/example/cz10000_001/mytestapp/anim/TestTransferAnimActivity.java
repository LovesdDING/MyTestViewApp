package com.example.cz10000_001.mytestapp.anim;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cz10000_001.mytestapp.MainActivity;
import com.example.cz10000_001.mytestapp.R;

/**
 *  Android  5.0 实现转场动画 以及对动画的兼容
 *  @author cz10000_001
 *  created 2018/2/28
 */

public class TestTransferAnimActivity extends AppCompatActivity {


    private Button mAutoTransitionBtn;
    private Button mManualTransitionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_transfer_anim);

        mAutoTransitionBtn = (Button) findViewById(R.id.ui_transition_btn);
        mManualTransitionBtn = (Button) findViewById(R.id.manual_transition_btn);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            mAutoTransitionBtn.setVisibility(View.GONE);
        }
        mAutoTransitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestTransferAnimActivity.this, ImageAnimActivity.class));
            }
        });

        mManualTransitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestTransferAnimActivity.this, AnimeManualActivity.class));
            }
        });

    }



}
