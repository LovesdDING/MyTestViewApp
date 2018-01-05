package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.cz10000_001.mytestapp.R;
import com.orhanobut.logger.Logger;

/**
 * Created by cz10000_001 on 2018/1/3.
 */

public class SlideMenu extends HorizontalScrollView{
        /**
         * 屏幕宽度
         */
        private int mScreenWidth;
        /**
         * dp
         */
        private int mMenuRightPadding;
        /**
         * 菜单的宽度
         */
        private int mMenuWidth;

        private int mHalfMenuWidth;
        private ViewGroup mMenu = null;

        private boolean isOpen;

        private boolean once;
       private ViewGroup content = null;

    public SlideMenu(Context context, AttributeSet attrs)
        {
            this(context, attrs, 0);

        }

        public SlideMenu(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);

            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics() ;
            manager.getDefaultDisplay().getMetrics(displayMetrics);
            mScreenWidth = displayMetrics.widthPixels;

            mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics());

//            TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
//                    R.styleable.SlidMenu, defStyle, 0);
//            int n = a.getIndexCount();
//            for (int i = 0; i < n; i++)
//            {
//                int attr = a.getIndex(i);
//                switch (attr)
//                {
//                    case R.styleable.SlidMenu_CustomViewRightPadding:
//                        // 默认50
//                        mMenuRightPadding = a.getDimensionPixelSize(attr,
//                                (int) TypedValue.applyDimension(
//                                        TypedValue.COMPLEX_UNIT_DIP, 100f,
//                                        getResources().getDisplayMetrics()));// 默认为10DP
//                        break;
//                }
//            }
//            a.recycle();
        }

        public SlideMenu(Context context)
        {
            this(context, null, 0);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            /**
             * 显示的设置一个宽度
             */
            if (!once)
            {
                LinearLayout wrapper = (LinearLayout) getChildAt(0);
                mMenu = (ViewGroup) wrapper.getChildAt(0);
                content = (ViewGroup) wrapper.getChildAt(1);

                mMenuWidth = mScreenWidth - mMenuRightPadding;
                mHalfMenuWidth = mMenuWidth / 2;
                mMenu.getLayoutParams().width = mMenuWidth;
                content.getLayoutParams().width = mScreenWidth;
                once = true ;

            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b)
        {
            super.onLayout(changed, l, t, r, b);
            if (changed)
            {
                // 将菜单隐藏
                this.scrollTo(mMenuWidth, 0);
                once = true;
            }
        }


    // 取消滑动监听
    @Override
        public boolean onTouchEvent(MotionEvent ev)
        {
//            int action = ev.getAction();
//            switch (action)
//            {
//                // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
//                case MotionEvent.ACTION_UP:
//                    int scrollX = getScrollX();
//                    if (scrollX > mHalfMenuWidth)
//                    {
//                        this.smoothScrollTo(mMenuWidth, 0);
//                        isOpen = false;
//                        content.setAlpha(1f);  //布局打开
//
//                    } else
//                    {
//                        this.smoothScrollTo(0, 0);
//                        isOpen = true;
//                        content.setAlpha(0.5f);  //布局content 关闭
//                    }
//
//                    return true;
//            }
//            return super.onTouchEvent(ev);
            return false ;
        }


        /**
         * 打开菜单
         */
        public void openMenu()
        {
            if (isOpen)
                return;
            this.smoothScrollTo(0, 0);
            isOpen = true;
            content.setAlpha(0.5f);  //布局content 关闭
        }

        /**
         * 关闭菜单
         */
        public void closeMenu()
        {
            if (isOpen)
            {
                this.smoothScrollTo(mMenuWidth, 0);
                isOpen = false;
                content.setAlpha(1f);  //布局打开
            }
        }

        /**
         * 切换菜单状态
         */
        public void toggle()
        {
            if (isOpen)
            {
                closeMenu();
            } else
            {
                openMenu();
            }
        }




    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Logger.i("onscrollChanged");
        float scale = l * 1.0f / mMenuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        Logger.i("scale=="+scale+","+leftScale);
//        content.setAlpha(0.7f);  // 0.7 -1
//        mMenu.setAlpha(1 - 0.3f * scale);  // 0.7-1

//        content.setScaleX(leftScale);
//        ViewHelper.setScaleX(mMenu, leftScale);
//        ViewHelper.setScaleY(mMenu, leftScale);
//        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
//        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.6f);
//
//        ViewHelper.setPivotX(mContent, 0);
//        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
//        ViewHelper.setScaleX(mContent, rightScale);
//        ViewHelper.setScaleY(mContent, rightScale);
    }


}
