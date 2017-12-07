package com.example.cz10000_001.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.MapView;

public class TestNavi2Activity extends AppCompatActivity {

    private MapView mapView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_navi2);
        mapView = (MapView) findViewById(R.id.mapView2);


    }

    public void TestStartNavi(View view) {

    }
}
