package com.example.cz10000_001.mytestapp;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.VideoView;

import com.orhanobut.logger.Logger;

/**
 *
 * 一个  通用的 VIdeoView的封装  ；
 * 适配VIdeoview   因为视频资源本身问题 如果不对VIdeoView 进行尺寸处理 ，播放效果会不尽人意
 *
 * 测试 无效果。。。
 * Created by cz10000_001 on 2018/2/1.
 */

public class CustomVideoView2 extends VideoView {
    //最终的视频资源宽度
    private int mVideoWidth=480;
    //最终视频资源高度
    private int mVideoHeight=480;
    //视频资源原始宽度
    private int videoRealW=1;
    //视频资源原始高度
    private int videoRealH=1;

    public CustomVideoView2(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomVideoView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomVideoView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void setVideoPath(String path) {
        super.setVideoPath(path);

        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(path);
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
        try {
            videoRealH=Integer.parseInt(height);
            videoRealW=Integer.parseInt(width);
        } catch (NumberFormatException e) {
            Log.e("----->" + "VideoView", "setVideoPath:" + e.toString());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        if(height>width){
            //竖屏
            if(videoRealH>videoRealW){
                Logger.i("VIdeo H>w ：h");
                //如果视频资源是竖屏
                //占满屏幕
                mVideoHeight=height;
                mVideoWidth=width;
            }else {
                Logger.i("VIdeo H>w ： w");
                //如果视频资源是横屏
                //宽度占满，高度保存比例
                mVideoWidth=width;
                float r=videoRealH/(float)videoRealW;
                mVideoHeight= (int) (mVideoWidth*r);
            }
        }else {
            //竖屏
            if(videoRealH>videoRealW){
                Logger.i("VIdeo w>h h");
                //如果视频资源是竖屏
                //宽度占满，高度保存比例
                mVideoHeight=height;
                float r=videoRealW/(float)videoRealH;
                mVideoWidth= (int) (mVideoHeight*r);
            }else {

                Logger.i("VIdeo w>h w");
                //如果视频资源是横屏
                //占满屏幕
                mVideoHeight=height;
                mVideoWidth=width;
            }
        }
        if(videoRealH==videoRealW&&videoRealH==1){
            //没能获取到视频真实的宽高，自适应就可以了，什么也不用做
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }else {
            setMeasuredDimension(mVideoWidth, mVideoHeight);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //屏蔽触摸点击事件
        return true;
    }
}
