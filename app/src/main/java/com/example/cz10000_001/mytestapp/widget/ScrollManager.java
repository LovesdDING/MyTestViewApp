package com.example.cz10000_001.mytestapp.widget;

import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.cz10000_001.mytestapp.util.DpUtil;

/**
 * Created by cz10000_001 on 2018/1/11.
 */

public class ScrollManager {
    private GalleryRecyclerView galleryRecyclerView ;
    private LinearSnapHelper linearSnapHelper ;
    private PagerSnapHelper pagerSnapHelper ;

    private int position = 0 ;

    private int mConsumX = 0 ;
    private int mConsumY =0 ;


    // 滑动方向
    private int slideDirct = SLIDE_RIGHT;

    private static final int SLIDE_LEFT = 1;    // 左滑
    private static final int SLIDE_RIGHT = 2;   // 右滑
    private static final int SLIDE_TOP = 3;     // 上滑
    private static final int SLIDE_BOTTOM = 4;  // 下滑



    public ScrollManager(GalleryRecyclerView galleryRecyclerView){
        this.galleryRecyclerView = galleryRecyclerView ;
    }

    public void initSnapHelper(int helper) {
        switch (helper) {
            case GalleryRecyclerView.LinearySnapHelper:
                linearSnapHelper = new LinearSnapHelper();
                linearSnapHelper.attachToRecyclerView(galleryRecyclerView);
                break;
            case GalleryRecyclerView.PagerSnapHelper:
                pagerSnapHelper = new PagerSnapHelper();
                pagerSnapHelper.attachToRecyclerView(galleryRecyclerView);
                break;


        }
    }


        // 监听 RecyclerView的 滑动

    public void initScroller() {
        GalleryScrollerListener listener = new GalleryScrollerListener() ;
        galleryRecyclerView.addOnScrollListener(listener);

    }

    //更新 consumX  消耗的x和Y
    public void updateConsum(){
        mConsumX += DpUtil.dpToPx(GalleryItemDecoration.mLeftPageVisibleWidth+GalleryItemDecoration.mPageMargin*2) ;
        mConsumY += DpUtil.dpToPx(GalleryItemDecoration.mLeftPageVisibleWidth+GalleryItemDecoration.mPageMargin*2) ;
    }


    class GalleryScrollerListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(galleryRecyclerView.getOrientation() == RecyclerView.HORIZONTAL){
                    onHorizontalScroll(recyclerView,dx) ;
            }else{
                    onVerticalScroll(recyclerView,dy) ;
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }
    }

    /**
     * 垂直滑动
     * @param recyclerView
     * @param dy
     */
    private void onVerticalScroll(final RecyclerView recyclerView, int dy) {
        mConsumY += dy ;

        if(dy>0){
            slideDirct = SLIDE_BOTTOM ;
        }else{
            slideDirct = SLIDE_TOP ;
        }

        //让RecyclerView测绘完成后 在调用
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumY =GalleryItemDecoration.mItemComusemY ;
                int position = getPosition(mConsumY,shouldConsumY) ;
                //位置浮点值
                float  offset = (float) mConsumY/(float) shouldConsumY ;

                if(offset >= galleryRecyclerView.getLinearLayoutManager().findFirstVisibleItemPosition()+1 && slideDirct ==SLIDE_BOTTOM){
                    return ;
                }
                //获取当前页移动的百分值
                float percent = offset - ((int)offset) ;

                Log.d("TAG", "offset=" + offset + "; mConsumeY=" + mConsumY + "; shouldConsumeY=" + shouldConsumY);


                // 设置动画变化
                AnimManager.getInstance().setAnimation(recyclerView, position, percent);
            }

        }) ;
    }


    /**
     *   获取 当前位置
     * @param mConsumY
     * @param shouldConsumY
     * @return
     */
    private int getPosition(int mConsumY, int shouldConsumY) {
        float offset = (float)mConsumY/(float)shouldConsumY ;
        return Math.round(offset);
    }

    private void onHorizontalScroll(final RecyclerView recyclerView, int dx) {
        mConsumX += dx;

        if (dx > 0) {
            // 右滑
            slideDirct = SLIDE_RIGHT;
        } else {
            // 左滑
            slideDirct = SLIDE_LEFT;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemWidth的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeX = GalleryItemDecoration.mItemComusemX;
                // 获取当前的位置
                int position = getPosition(mConsumX, shouldConsumeX);

                float offset = (float) mConsumX / (float) shouldConsumeX;     // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                Log.d("TAG", "offset=" + offset + "; mConsumeX=" + mConsumX + "; shouldConsumeX=" + shouldConsumeX);

                // 避免offset值取整时进一，从而影响了percent值
                if (offset >= galleryRecyclerView.getLinearLayoutManager().findFirstVisibleItemPosition() + 1 && slideDirct == SLIDE_RIGHT) {
                    return;
                }

                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);



                // 设置动画变化
                AnimManager.getInstance().setAnimation(recyclerView, position, percent);
            }
        });
    }

    public int getPosition(){
        return position ;
    }




}
