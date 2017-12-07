package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cz10000_001.mytestapp.model.CakeBean;
import com.example.cz10000_001.mytestapp.util.DpUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 自定义 饼状图 动态添加 饼状图数据
 * Created by cz10000_001 on 2017/11/16.
 */

public class CakeView extends View {

    //装载的饼状圆数据
    private List<CakeBean> beanList;
    //画圆的矩形
    private RectF mRectF;
    //右边的小矩形
    private RectF iRectF;
    private Paint mPaint;
    private int mRWidth, mRHeight;
    private float rotateDegree;//每个圆弧的起始角度
    private float sumValue = 0;//所有值的和
    private float diameter;//圆的直径
    private float textY;//绘制文字的Y坐标
    private float mRectHeight = 40;//矩形高度
    private float mRectWidth = 80;//矩形宽度
    private float mMargin = 40;//矩形和圆的距离
    private Context mContext;


    public CakeView(Context context) {
        this(context,null) ;
    }

    public CakeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0) ;
    }

    public CakeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
        init() ;
    }

    private void init() {
            beanList = new ArrayList<>() ;
            mRectF = new RectF() ;
            mPaint = new Paint() ;
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //MeasureSpec封装了父View传递给子View的布局要求

        int wMode = MeasureSpec.getMode(widthMeasureSpec) ;  //宽度测量模式
        int wSize = MeasureSpec.getSize(widthMeasureSpec) ;   //宽度测量值

        int hMode = MeasureSpec.getMode(heightMeasureSpec) ;
        int hSize = MeasureSpec.getSize(heightMeasureSpec) ;

        switch (wMode){
            case MeasureSpec.EXACTLY:
//         相当于match_parent或者一个具体值
                mRWidth = wSize ;
                break;
            case MeasureSpec.AT_MOST:
// 相当于wrap_content ，需要手动测量大小，这里先写死大小
                mRWidth = (int) DpUtil.dp2px(mContext, 400f);
                break;
            case MeasureSpec.UNSPECIFIED:
                //很少用到
                break;
            default:
                break;
        }


        switch (hMode){
            case MeasureSpec.EXACTLY:
                mRHeight = hSize ;
                break;
            case MeasureSpec.AT_MOST:
                mRHeight = (int) DpUtil.dp2px(mContext, 200f);

                break;
            case MeasureSpec.UNSPECIFIED:

                break;
            default:
                break;
        }

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        diameter = Math.min(mRWidth,mRHeight) ;

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectF.set(0,0,diameter,diameter);
        //画布中心X坐标向右移动（控件宽度-圆直径）之差的八分之一的距离
        //画布中心Y坐标向下移动（控件宽度-圆直径）之差的二分之一的距离

        canvas.translate((mRWidth-diameter)/8,(mRWidth-diameter)/2);
        if(beanList.size()>0 && Float.compare(sumValue,0.0f)!=0){
            for (int i = 0; i < beanList.size(); i++) {
                CakeBean bean = beanList.get(i) ;
                //画圆弧
                mPaint.setColor(bean.mColor);
                canvas.drawArc(mRectF,rotateDegree,bean.degree,true,mPaint);
                rotateDegree += bean.degree ;
                //画矩形 和文字
                drawRectAndText(canvas,bean) ;


            }
        }
    }

    private void drawRectAndText(Canvas canvas, CakeBean bean) {
            iRectF = new RectF()  ;
            float left = diameter+mMargin ;
            float right = diameter+mMargin+mRectWidth ;
            float bottom = textY+mRectHeight ;

            iRectF.set(left,textY,right,bottom);
            canvas.drawRect(iRectF,mPaint);
            //设置颜色
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(30);
            canvas.drawText(bean.name + "(" + new DecimalFormat(".00").format(bean.value / sumValue * 100) + "%)", right + 10, textY + 30, mPaint);
            textY+=mRectHeight ;


    }


    /**
     * 饼状图添加数据
     *
     * @param beans CakeBean数据
     */
    public void setData(List<CakeBean> beans) {
        if (beans == null || beans.size() <= 0) return;
        for (int i = 0; i < beans.size(); i++) {
            CakeBean bean = beans.get(i);
            sumValue += bean.value;
        }
        for (int i = 0; i < beans.size(); i++) {
            CakeBean bean = beans.get(i);
            bean.degree = bean.value / sumValue * 360;
            beanList.add(bean);
        }
        invalidate();
    }

    /**
     * @param startDegree 设置起始角度
     */    public void setStartDegree(float startDegree) {
        this.rotateDegree = startDegree;
        invalidate();
    }

}




