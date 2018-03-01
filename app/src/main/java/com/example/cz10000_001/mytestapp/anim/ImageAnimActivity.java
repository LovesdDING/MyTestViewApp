package com.example.cz10000_001.mytestapp.anim;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cz10000_001.mytestapp.R;

/**
 *  5.0+ 转场动画  使用ActivityOptionsCompat  实现共享元素
 *
 */
public class ImageAnimActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void imageClick(View view){
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,view,getString(R.string.image_transition_name)) ;

        CustomImage image = (CustomImage) view;
        Intent intent = new Intent(this,DetailActivity.class) ;
        intent.putExtra(DetailActivity.EXTRA_IMAGE,image.getImageId()) ;
        startActivity(intent,activityOptions.toBundle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_anim);
        setSupportActionBar((Toolbar) findViewById(R.id.activity_img_toolbar));

    }
}
