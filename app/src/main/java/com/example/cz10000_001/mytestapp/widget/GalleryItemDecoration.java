package com.example.cz10000_001.mytestapp.widget;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.amap.api.location.DPoint;
import com.example.cz10000_001.mytestapp.util.DpUtil;

/**
 * Created by cz10000_001 on 2018/1/10.
 */

public class GalleryItemDecoration extends RecyclerView.ItemDecoration{
    private final String TAG = "GalleryItemDecoration";

    public static int mPageMargin = 0;          // 每一个页面默认页边距
    public static int mLeftPageVisibleWidth = 50; // 中间页面左右两边的页面可见部分宽度

    public static int mItemComusemY = 0;
    public static int mItemComusemX = 0;

    private GalleryRecyclerView.OnItemClickListener onItemClickListener;

    public GalleryItemDecoration(){}

    @Override
    public void getItemOffsets(Rect outRect, final View view, final RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        Log.d(TAG, "getItemOffsets: position=> "+parent.getChildAdapterPosition(view));
        final int position = parent.getChildAdapterPosition(view) ;
        final int itemCount = parent.getAdapter().getItemCount() ;

        parent.post(new Runnable() {
            @Override
            public void run() {
                if (((GalleryRecyclerView) parent).getOrientation() == LinearLayoutManager.HORIZONTAL) {

                    onSetHoritiontalParams(parent, view, position, itemCount);
                } else {
                    onSetVerticalParams(parent, view, position, itemCount);
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    /**
     * 设置 垂直滚动的参数
     * @param parent
     * @param view
     * @param position
     * @param itemCount
     */
    private void onSetVerticalParams(RecyclerView parent, View view, int position, int itemCount) {
        int itemNewWidth = parent.getWidth() ;
        int itemNewHeight = parent.getHeight()- DpUtil.dpToPx(4*mPageMargin+2*mLeftPageVisibleWidth);

        mItemComusemY = itemNewHeight +DpUtil.dpToPx(2*mPageMargin) ;

        Log.d("TAG", "itemNewHeight=" + itemNewHeight);

        int topMargin = position==0?DpUtil.dpToPx(mLeftPageVisibleWidth+2*mPageMargin) :DpUtil.dpToPx(mPageMargin) ;
        int bottomMargin = position==itemCount-1?DpUtil.dpToPx(mLeftPageVisibleWidth+2*mPageMargin):DpUtil.dpToPx(mPageMargin) ;

        setLayoutParams(view, 0, topMargin, 0, bottomMargin, itemNewWidth, itemNewHeight);
    }

    /**
     * 设置 参数
     * @param view
     * @param itemNewWidth
     * @param itemNewHeight
     */
    private void setLayoutParams(View view, int left, int top, int right, int bottom, int itemNewWidth, int itemNewHeight) {

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        boolean  mMarginChange = false ;
        boolean mWidthChange = false ;
        boolean mHeightChange = false ;

        if(params.leftMargin!=left ||params.topMargin!=top||params.rightMargin!=right||params.bottomMargin!=bottom){
            params.setMargins(left, top, right, bottom);
            mMarginChange = true ;
        }

        if(params.height !=itemNewHeight){
            params.height = itemNewHeight ;
            mHeightChange = true ;
        }

        if(params.width!=itemNewWidth){
            params.width = itemNewWidth ;
            mWidthChange = true ;
        }

        if(mMarginChange || mHeightChange||mWidthChange){
            view.setLayoutParams(params);
        }

    }

    /**
     * 设置 水平滚动的参数
     * @param parent
     * @param view
     * @param position
     * @param itemCount
     */
    private void onSetHoritiontalParams(RecyclerView parent, View view, int position, int itemCount) {
        int itemNewWidth = parent.getWidth()-DpUtil.dpToPx(4*mPageMargin+2*mLeftPageVisibleWidth) ;
        int itemNewHeight = parent.getHeight() ;

        mItemComusemX = itemNewWidth +DpUtil.dpToPx(2*mPageMargin) ;

        Log.d(TAG, "onSetHoritiontalParams -->" + "parent.width=" + parent.getWidth() + ";mPageMargin=" + DpUtil.dpToPx(mPageMargin) + ";mLeftVis=" + DpUtil.dpToPx(mLeftPageVisibleWidth) + ";itemNewWidth=" + itemNewWidth);

        // 适配第0页和最后一页没有左页面和右页面，让他们保持左边距和右边距和其他项一样
        int leftMargin = position == 0 ? DpUtil.dpToPx(mLeftPageVisibleWidth + 2 * mPageMargin) : DpUtil.dpToPx(mPageMargin);
        int rightMargin = position == itemCount - 1 ? DpUtil.dpToPx(mLeftPageVisibleWidth + 2 * mPageMargin) : DpUtil.dpToPx(mPageMargin);

        setLayoutParams(view, leftMargin, 0, rightMargin, 0, itemNewWidth, itemNewHeight);
    }

    public void setOnItemClickListener(GalleryRecyclerView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




}
