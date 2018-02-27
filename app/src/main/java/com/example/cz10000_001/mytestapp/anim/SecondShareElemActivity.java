package com.example.cz10000_001.mytestapp.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;

public class SecondShareElemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_share_elem);
//        getSupportActionBar().setTitle("第二个Activity");
    }


    public void onClick(View view) {
        finish();
    }
}
