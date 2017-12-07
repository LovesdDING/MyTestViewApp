package com.example.cz10000_001.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cz10000_001.mytestapp.view.AliPayView;

public class AliPayActivity extends AppCompatActivity implements View.OnClickListener {

    private AliPayView aliPayView ;
    private Button btnStart,btnEnd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay);

        aliPayView = (AliPayView) findViewById(R.id.alipay_view);
        aliPayView.setState(AliPayView.State.IDLE);
        btnStart = (Button) findViewById(R.id.starBtn);
        btnEnd = (Button) findViewById(R.id.endBtn);


        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.starBtn:
                    aliPayView.setOverPay(false);
                    aliPayView.setState(AliPayView.State.PROGRESS);
                break;
            case R.id.endBtn:
                    aliPayView.setOverPay(true);
                break;
        }
    }
}


