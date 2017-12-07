package com.example.cz10000_001.mytestapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cz10000_001.mytestapp.view.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

public class TestZDYSpinerActivity extends AppCompatActivity implements View.OnClickListener {

    private SpinerPopWindow mSpinerPopWindow  ;
    private TextView tv1 ;
    private List<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_zdyspiner);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        initData();

        mSpinerPopWindow = new SpinerPopWindow<String>(this, list,itemClickListener);
        mSpinerPopWindow.setOnDismissListener(dismissListener);
    }

    /**
     * 监听popupwindow取消
     */
    private PopupWindow.OnDismissListener dismissListener=new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setTextImage(R.mipmap.icon_down);
        }
    };

    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinerPopWindow.dismiss();
            tv1.setText(list.get(position));
            Toast.makeText(TestZDYSpinerActivity.this, "点击了:" + list.get(position),Toast.LENGTH_LONG).show();
        }
    };


    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("test:" + i);
        }
    }

    /**
     * 给TextView右边设置图片
     * @param resId
     */
    private void setTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        tv1.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                mSpinerPopWindow.setWidth(tv1.getWidth());
                mSpinerPopWindow.showAsDropDown(tv1);
                setTextImage(R.mipmap.icon_up);
                break;
        }
    }
}
