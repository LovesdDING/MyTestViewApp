package com.example.cz10000_001.mytestapp.wallpager;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 *  桌面壁纸service
 *  可以进行自定义的壁纸设置
 *
 * Created by cz10000_001 on 2018/1/18.
 */

public class VideoWallPager extends WallpaperService {

    public  static  final String VIDEO_PARAMS_CONTROL_ACTION = "com.example.cz10000_001.mytestapp.wallpager";
    public static final  String KEY_ACTION = "action";
    public  static final int ACTION_VOICE_SILENCE = 110;
    public  static final int ACTION_VOICE_NORMAL = 111;


    @Override
    public Engine onCreateEngine() {
        return new VideoEngin();
    }



    public static void voiceSilence(Context context){
        Intent intent = new Intent(VideoWallPager.VIDEO_PARAMS_CONTROL_ACTION);
        intent.putExtra(VideoWallPager.KEY_ACTION, VideoWallPager.ACTION_VOICE_SILENCE);
        context.sendBroadcast(intent);
    }

    public static void voiceNormal(Context context){
        Intent intent = new Intent(VideoWallPager.VIDEO_PARAMS_CONTROL_ACTION);
        intent.putExtra(VideoWallPager.KEY_ACTION, VideoWallPager.ACTION_VOICE_NORMAL);
        context.sendBroadcast(intent);
    }

    public static void setToWallPaper(Context context) {
        final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                new ComponentName(context, VideoWallPager.class));
        context.startActivity(intent);
    }

    class VideoEngin extends Engine{

        private MediaPlayer mMediaPlayer ;
        private BroadcastReceiver broadcastReceiver ;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            Logger.i("videoEngin oncreated");
            super.onCreate(surfaceHolder);
            IntentFilter intentFilter = new IntentFilter(VIDEO_PARAMS_CONTROL_ACTION) ;
            registerReceiver(broadcastReceiver =new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Logger.i("videoEngin regist");
                    int action = intent.getIntExtra(KEY_ACTION,-1) ;
                    switch (action){
                        case ACTION_VOICE_NORMAL:
                            mMediaPlayer.setVolume(1.0f,1.0f);
                            break;
                        case ACTION_VOICE_SILENCE:
                            mMediaPlayer.setVolume(0.0f,0.0f);
                            break;
                    }

                }
            },intentFilter) ;


        }


        @Override
        public void onDestroy() {
            unregisterReceiver(broadcastReceiver);
            super.onDestroy();

        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            Logger.i("visi:"+visible);
            if(visible){
                mMediaPlayer.start();
            }else{
                mMediaPlayer.pause();
            }
        }


        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            Logger.i("onSurfaceCreated");
            super.onSurfaceCreated(holder);
            mMediaPlayer = new MediaPlayer() ;
            mMediaPlayer.setSurface(holder.getSurface());
            try {
                AssetManager assetManager = getApplicationContext().getAssets() ;
                AssetFileDescriptor asfile = assetManager.openFd("test1.mp4");;
                mMediaPlayer.setDataSource(asfile.getFileDescriptor(),asfile.getStartOffset(),asfile.getLength());
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setVolume(0,0);
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Logger.i("onSurfaceChanged");
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            Logger.i("onSurfaceDestroyed");
            super.onSurfaceDestroyed(holder);
            mMediaPlayer.release();
            mMediaPlayer = null ;
        }

    }


}
