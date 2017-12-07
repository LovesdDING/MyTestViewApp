package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by cz10000_001 on 2017/11/17.
 */

public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context) {
        this(context,null) ;
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0) ;
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
