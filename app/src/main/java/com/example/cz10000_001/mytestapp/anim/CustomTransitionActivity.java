package com.example.cz10000_001.mytestapp.anim;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;

public class CustomTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondCustomTransActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (this, v, "transition_morph_view");
        startActivity(intent, options.toBundle());
    }
}
