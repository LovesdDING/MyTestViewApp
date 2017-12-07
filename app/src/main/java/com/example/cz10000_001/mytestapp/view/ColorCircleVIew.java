package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;


/**
 *   自定义 单个圆环 View
 *
 * Created by cz10000_001 on 2017/11/16.
 */

public class ColorCircleVIew extends View {

    private Paint mPaint ;
    private int cirlceColor ;  //圆环颜色
    private int stokeWidth ;  //圆环宽度

    public ColorCircleVIew(Context context) {
        this(context,null) ;
    }

    public ColorCircleVIew(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0) ;
    }

    public ColorCircleVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs) ;
    }

    private void init(Context context, AttributeSet attrs) {
        //获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorCircleView);
        cirlceColor = ta.getColor(R.styleable.ColorCircleView_circle_color, Color.RED);
        stokeWidth = ta.getInt(R.styleable.ColorCircleView_stroke_width, 10);
        ta.recycle();
        //初始化 画笔
        mPaint = new Paint() ;
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(cirlceColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(stokeWidth);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radius = (Math.min(getMeasuredWidth(),getMeasuredHeight()-mPaint.getStrokeWidth()))/2 ;
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,radius,mPaint);
    }
}
