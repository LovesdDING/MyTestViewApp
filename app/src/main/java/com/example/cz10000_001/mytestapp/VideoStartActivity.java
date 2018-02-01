package com.example.cz10000_001.mytestapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 *    实现启动页是 视频播放
 *
 *    一般  R文件 丢失的时候 都是因为资源文件 出错了
 *     一般从以下几个方面： 可能是xml文件有问题  ；或者 定义的color  引入的图片资源等出现问题
 *
 */
public class VideoStartActivity extends AppCompatActivity {

    private CustomVideoView customVideo;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_start);

        customVideo = (CustomVideoView) findViewById(R.id.cusVideo);
        btnStart = (Button) findViewById(R.id.btnGoStart);



        initView() ;

    }

    private void initView() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoStartActivity.this, "开启主页 进入APP", Toast.LENGTH_SHORT).show();
            }
        });


        //设置播放加载路径
        customVideo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.cat));
        customVideo.requestFocus() ;  //设置 videoview  抢占焦点 ；页面有button按钮 如果不设置 videoview 抢占焦点会出现视频播放黑屏

        //播放
        try {
            customVideo.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //循环播放
        customVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                customVideo.start();
            }
        });

    }


}
