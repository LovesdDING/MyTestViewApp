package com.example.cz10000_001.mytestapp.anim;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.cz10000_001.mytestapp.R;


public class AnimeDetailActivity extends AppCompatActivity {


    public static final String EXTRA_IMAGE = DetailActivity.class.getSimpleName() + ".IMAGE";
    public static final int DURATION = 300;
    private static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final String SCALE_WIDTH = "SCALE_WIDTH";
    private static final String SCALE_HEIGHT = "SCALE_HEIGHT";
    private static final String TRANSITION_X = "TRANSITION_X";
    private static final String TRANSITION_Y = "TRANSITION_Y";
    private ImageView mImageView;
    private FrameLayout mContainer;
    private Palette mImagePalette;

    // 屏幕宽度和高度
    private int mScreenWidth;
    private int mScreenHeight;

    private int mResourceId;  //图片资源id
    private Rect mRect;  //上一个界面的图片资源位置信息

    private int mOriginWidth;
    private int mOriginHeight;

    //存储图片缩放比例和位移距离
    private Bundle mScaleBundle = new Bundle();
    private Bundle mTransitionBundle = new Bundle() ;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        // 获得屏幕尺寸
        getScreenSize();

        // 初始化界面
        mImageView = (ImageView) findViewById(R.id.activity_anime_detail_img);
        mContainer = (FrameLayout) findViewById(R.id.activity_anime_detail_container);

        // 初始化场景
        initial();

        // 设置入场动画
        runEnterAnim();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runExitAnim();
            }
        });
        
    }

    /**
     * 模拟入场动画
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void runEnterAnim() {
        mImageView.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(mScaleBundle.getFloat(SCALE_WIDTH))
                .scaleY(mScaleBundle.getFloat(SCALE_HEIGHT))
                .translationX(mTransitionBundle.getFloat(TRANSITION_X))
                .translationY(mTransitionBundle.getFloat(TRANSITION_Y))
                .start();
        mImageView.setVisibility(View.VISIBLE);
    }


    /**
     *  模拟 退场动画
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void runExitAnim(){
        mImageView.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0,0);
                    }
                })
                .start();
    }

    /**
     * 初始化场景
     */
    private void initial() {
        //上一个界面传入的信息
        mRect =getIntent().getSourceBounds() ;
        mResourceId = getIntent().getExtras().getInt(EXTRA_IMAGE) ;
        //获取上一个界面中图片的宽度和高度
        mOriginWidth = mRect.right - mRect.left ;
        mOriginHeight = mRect.bottom-mRect.top ;

        // 设置 ImageView 的位置，使其和上一个界面中图片的位置重合
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mOriginWidth,mOriginHeight) ;
        layoutParams.setMargins(mRect.left,mRect.top-getStatusBarHeight(),mRect.right,mRect.bottom);
        mImageView.setLayoutParams(layoutParams);
        // 设置 ImageView 的图片和缩放类型
            mImageView.setImageResource(mResourceId);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // 根据上一个界面传入的图片资源 ID，获取图片的 Bitmap 对象。
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(mResourceId);
        Bitmap bitmap = bitmapDrawable.getBitmap() ;
        // 计算图片缩放比例和位移距离
            getBundleInfo(bitmap) ;
        // 创建一个 Pallette 对象
        mImagePalette = Palette.from(bitmap).generate() ;
        // 使用 Palette 设置背景颜色
        mContainer.setBackgroundColor(mImagePalette.getVibrantColor(ContextCompat.getColor(this,android.R.color.black)));

    }

    /**
     *   计算图片缩放比例 以及位移距离
     * @param bitmap
     */
    private void getBundleInfo(Bitmap bitmap) {
        // 计算图片缩放比例 并存储在bundle中
         if(bitmap.getWidth()>=bitmap.getHeight()){
             mScaleBundle.putFloat(SCALE_WIDTH, (float) mScreenWidth / mOriginWidth);
             mScaleBundle.putFloat(SCALE_HEIGHT, (float) bitmap.getHeight() / mOriginHeight);
         }else{
             mScaleBundle.putFloat(SCALE_WIDTH, (float) bitmap.getWidth() / mOriginWidth);
             mScaleBundle.putFloat(SCALE_HEIGHT, (float) mScreenHeight / mOriginHeight);

         }
        //计算图片位移距离  并存储在bundle中
        mTransitionBundle.putFloat(TRANSITION_X, mScreenWidth / 2 - (mRect.left + (mRect.right - mRect.left) / 2));
        mTransitionBundle.putFloat(TRANSITION_Y, mScreenHeight / 2 - (mRect.top + (mRect.bottom - mRect.top) / 2));



    }

    /**
     *  获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android") ;
        if(resourceId>0){
            return getResources().getDimensionPixelSize(resourceId) ;
        }
        return -1;
    }

    /**
     *  获取屏幕尺寸
     */
    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay() ;
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x ;
        mScreenHeight = size.y ;
    }


}
