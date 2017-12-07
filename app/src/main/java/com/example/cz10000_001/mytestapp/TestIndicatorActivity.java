package com.example.cz10000_001.mytestapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.TickType;

public class TestIndicatorActivity extends AppCompatActivity {

    private IndicatorSeekBar indicatorSeekBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_indicator);

//        initLayout() ;
    }

    private void initLayout() {

//        indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.testIndicator);


        IndicatorSeekBar indicatorSeekBar1 = new IndicatorSeekBar.Builder(this)
                                            .setMax(100)
                                            .setMin(0)
                                            .setProgress(20)
                                            .setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS)
                                            .setTickType(TickType.OVAL)
                                            .setTickColor(Color.parseColor("#0000ff"))
                                            .setTickSize(8)
                                            .setTickNum(8)
                                            .setBackgroundTrackSize(2)
                                            .setBackgroundTrackColor(Color.parseColor("#666666"))
                                            .setProgressTrackSize(3)
                                            .setProgressTrackColor(Color.parseColor("#0000ff"))
                                            .showIndicator(true)
                                            .setIndicatorType(IndicatorType.SQUARE_CORNERS)
                                            .setIndicatorColor(Color.parseColor("#0000ff"))
                                            .build() ;



        indicatorSeekBar1.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                Logger.i("onProgressChanged",progress);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
                Logger.i("onSectionChanged",tickBelowText);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });



    }

}
