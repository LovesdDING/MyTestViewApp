package com.example.cz10000_001.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cz10000_001.mytestapp.view.CollapseView;

public class BActivity extends AppCompatActivity {

    private CollapseView collapseView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);


        collapseView = (CollapseView) findViewById(R.id.collav);


        collapseView.setNumber("1");
//        collapseView.setContent("夜空中最亮的星照亮我前行");
        collapseView.setTitle("夜空中最亮的星");
        collapseView.setContent(R.layout.content);
    }

//    public void changeC(View view) {
//        startActivity(new Intent(this,CActivity.class));
//    }
}
