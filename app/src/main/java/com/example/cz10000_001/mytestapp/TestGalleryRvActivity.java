package com.example.cz10000_001.mytestapp;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cz10000_001.mytestapp.adapter.RecyclerAdapter;
import com.example.cz10000_001.mytestapp.util.BitmapUtil;
import com.example.cz10000_001.mytestapp.util.ToastUtil;
import com.example.cz10000_001.mytestapp.widget.AnimManager;
import com.example.cz10000_001.mytestapp.widget.GalleryRecyclerView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 测试 使用 GalleryRecycler
 */
public class TestGalleryRvActivity extends AppCompatActivity implements GalleryRecyclerView.OnItemClickListener {


    private GalleryRecyclerView gallerv;
    private RelativeLayout layoutRoot;

    private Map<String, Drawable> mTSDraCacheMap = new HashMap<>();
    private static final String KEY_PRE_DRAW = "key_pre_draw";
    private static final String TAG = TestGalleryRvActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gallery_rv);

        gallerv = (GalleryRecyclerView) findViewById(R.id.garv);
        layoutRoot = (RelativeLayout) findViewById(R.id.rootLayout);

        RecyclerAdapter adapter = new RecyclerAdapter(this, getData());
        gallerv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        gallerv.setAdapter(adapter);
        gallerv.initFlingSpeed(9000)
                .setAnimFactor(0.15f) //设置切换动画的因子
                .initPageParams(0, 60) //设置页边距 和左右图片的可见宽度
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
                .setOnItemListener(this);//设置点击事件


        // 背景高斯模糊&淡入淡出
        gallerv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onScrollStateChanged:进入高斯模糊");
                    setBlurImg();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

//        setBlurImg();

    }

    /**
     * 设置  背景高斯模糊
     */
    private void setBlurImg() {
        Logger.i("高斯模糊中。。");
        Log.i(TAG, "setBlurIMg:进入高斯模糊");
        RecyclerAdapter adapter = (RecyclerAdapter) gallerv.getAdapter();
        if (adapter == null || gallerv == null) {
            return;
        }

        Logger.i("准备进入post");
        // 图片的UI 县线程   重绘页面
        gallerv.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "rv post");
                //获取当前位置的图片资源
                int resourceId = ((RecyclerAdapter) gallerv.getAdapter()).getResId(gallerv.getScrolledPosition());
                Log.i(TAG, "resourceId::" + resourceId);
                //将该资源图片转为Bitmap
                Bitmap resBmp = BitmapFactory.decodeResource(getResources(), resourceId);
                //将该bitmap  高斯模糊后返回到 resBlurBmp
                Bitmap resBlurBmp = BitmapUtil.blurBitmap(gallerv.getContext(), resBmp, 15f);
                //再将resBlurBmp 转为Drawable
                Drawable blurDrawable = new BitmapDrawable(resBlurBmp);
                Log.i(TAG, "run: pre："+mTSDraCacheMap.get(KEY_PRE_DRAW));
                //获取前一页的Drawable
                Drawable preblurDrawable = mTSDraCacheMap.get(KEY_PRE_DRAW) == null ? blurDrawable : mTSDraCacheMap.get(KEY_PRE_DRAW);
                //淡入淡出效果
                Drawable[] drawArr = {preblurDrawable,blurDrawable};
                TransitionDrawable transitionDrawable = new TransitionDrawable(drawArr);
                layoutRoot.setBackgroundDrawable(transitionDrawable);
                transitionDrawable.startTransition(500);
                //  存到内存中  cache
                mTSDraCacheMap.put(KEY_PRE_DRAW, blurDrawable);
                Log.i(TAG, "run: cun：" + mTSDraCacheMap.put(KEY_PRE_DRAW, blurDrawable));
            }
        });

    }

    private List<Integer> getData() {
        TypedArray ar = getResources().obtainTypedArray(R.array.test_arr);
        final int[] resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        List<Integer> tDatas = new ArrayList<>();
        for (int resId : resIds) {
            tDatas.add(resId);
        }
        return tDatas;
    }

    @Override
    public void onItemClick(View view, int position) {
        ToastUtil.show(this, "position=" + position);
    }
}
