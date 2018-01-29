package com.example.cz10000_001.mytestapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;


/**
 * 自定义 RecyclerView  实现 gallery效果
 * Created by cz10000_001 on 2018/1/10.
 */

public class GalleryRecyclerView extends RecyclerView {

    private  int FLING_SLEEP = 1000 ; //滑动速度
    public static final int LinearySnapHelper = 0;
    public static final int PagerSnapHelper = 1;

    private GalleryItemDecoration itemDecoration ;
    private ScrollManager mScrollManager ;

    public GalleryRecyclerView(Context context) {
        this(context,null);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.gallery_recyclerview);
        int helper = ta.getInteger(R.styleable.gallery_recyclerview_helper, LinearySnapHelper);
        ta.recycle();


        attachDecoration() ;

        attachToRecyclerHelper(helper);
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if(getAdapter().getItemCount()<=0){
            return ;
        }
        if(mScrollManager != null){
            mScrollManager.updateConsum();
        }

        //获得焦点后 滑动到 第0项
        smoothScrollToPosition(0) ;
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = balancelocity(velocityX) ;
        velocityY = balancelocity(velocityY) ;
        return super.fling(velocityX, velocityY);

    }

    //  返回滑动速度值
    private int balancelocity(int x) {

        if(x>0){
            return Math.min(x,FLING_SLEEP) ;
        }else{
            return Math.max(x,-FLING_SLEEP) ;
        }
    }

    /**
     * 连接 recyclerHelper
     * @param helper
     */
    private void attachToRecyclerHelper(int helper) {
        mScrollManager = new ScrollManager(this) ;
        mScrollManager.initSnapHelper(helper);
        mScrollManager.initScroller();
    }

    private void attachDecoration() {
        itemDecoration = new GalleryItemDecoration() ;
        addItemDecoration(itemDecoration);
    }

    public int getOrientation() {

        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
            } else {
                return ((LinearLayoutManager) getLayoutManager()).getOrientation();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }

    public LinearLayoutManager getLinearLayoutManager() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");

            } else {
                return (LinearLayoutManager) getLayoutManager();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }

    /**
     * 设置滑动速度（像素/s）
     *
     * @param speed
     * @return
     */
    public GalleryRecyclerView initFlingSpeed(int speed) {
        this.FLING_SLEEP = speed;
        return this;
    }

    /**
     * 设置动画因子
     *
     * @param factor
     * @return
     */
    public GalleryRecyclerView setAnimFactor(float factor) {
        AnimManager.getInstance().setmAnimFactor(factor);
        return this;
    }

    /**
     * 设置动画类型
     *
     * @param type
     * @return
     */
    public GalleryRecyclerView setAnimType(int type) {
        AnimManager.getInstance().setmAnimType(type);
        return this;
    }

    /**
     * 设置 页面参数
     */
    public GalleryRecyclerView initPageParams(int pageMargin,int leftPageVisibleWidth){
        GalleryItemDecoration.mPageMargin = pageMargin ;
        GalleryItemDecoration.mLeftPageVisibleWidth = leftPageVisibleWidth ;
        return this ;
    }


    public GalleryRecyclerView setOnItemListener(OnItemClickListener listener){
        if(itemDecoration!=null){
            itemDecoration.setOnItemClickListener(listener);
        }
        return this ;
    }


    public int getScrolledPosition() {
        if (mScrollManager == null) {
            return 0;
        } else {
            return mScrollManager.getPosition();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
