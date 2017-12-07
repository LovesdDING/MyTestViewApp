package com.example.cz10000_001.mytestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cz10000_001.mytestapp.view.fixedfloatwindow.FFWindow;
import com.example.cz10000_001.mytestapp.view.fixedfloatwindow.FilterType;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

//        Button button = new Button(getApplicationContext());
//        button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
//        button.setText(" 悬浮按钮 ");

//        FFWindow fFWindow = new FFWindow(getApplicationContext())
//                .setView(button)
//                .setGravity(Gravity.END | Gravity.TOP, 100, 100)
//                .setFilter(FilterType.SHOW, BActivity.class, CActivity.class)
//                .show();
    }

    public void change(View view) {
        startActivity(new Intent(this,BActivity.class));
    }
}
