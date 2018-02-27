package com.example.cz10000_001.mytestapp.anim;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.example.cz10000_001.mytestapp.R;

public class SecondWindowTransActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_window_trans);
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
    }


}
