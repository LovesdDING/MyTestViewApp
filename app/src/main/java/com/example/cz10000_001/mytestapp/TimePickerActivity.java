package com.example.cz10000_001.mytestapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimePickerActivity extends AppCompatActivity {

    private TextView tvTime ;
    private TimePickerView pvCustomTime ;
    private OptionsPickerView pvNoLinkOptions ;
    private List<String> hours = new ArrayList<>() ;
    private List<String> minutes = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        tvTime = (TextView) findViewById(R.id.tvtime);


//        //时间选择器
//        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvTime.setText(getTime(date));
//            }
//        })
//                .build();
//        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
//        pvTime.show();


        initTime() ;
        initDataTime() ;
        initNoLinkOptionsPicker();


        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pvCustomTime.show(tvTime,true);
                pvNoLinkOptions.show(tvTime,true);
            }
        });


    }

    private void initDataTime() {
        for (int i = 0; i < 24; i++) {
            if(i<10){
                hours.add("0"+i) ;
            }else{
                hours.add(""+i) ;
            }
        }

        minutes.add("00") ;
        minutes.add("30") ;
    }

    private void initTime() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        selectedDate.set(2017,2,2,8,0);
        Calendar startDate = Calendar.getInstance();
//        startDate.set();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28,24,0);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(getTime(date));
            }
        })
//                .setType(TimePickerView.Type.All)//default is all
//                .setCancelText("Cancel")
//                .setSubmitText("Sure")
//                .setContentSize(18)
//                .setTitleSize(20)
//                .setTitleText("工作时间")
//                .setTitleColor(Color.BLACK)
                .isDialog(true)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
//                .gravity(Gravity.RIGHT)// default is center
                .setDate(selectedDate)
//                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择营业时间");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }


    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

//                String str = "food:" + food.get(options1)
//                        + "\nclothes:" + clothes.get(options2)
//                        + "\ncomputer:" + computer.get(options3);

                String str = hours.get(options1)+"时"+minutes.get(options2)+"分" ;
                tvTime.setText(str);
//                Toast.makeText(TimePickerActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(24)//设置滚轮文字大小
                .setDividerColor(getResources().getColor(R.color.color_e7e8e9))//设置分割线的颜色
                .setSelectOptions(0, 0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTextColorOut(getResources().getColor(R.color.color_82))
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(getResources().getColor(R.color.color_4f))
                .setCancelColor(getResources().getColor(R.color.color_4f))
                .setSubmitColor(getResources().getColor(R.color.color_4f))
                .isDialog(true)
                .setTextColorCenter(getResources().getColor(R.color.color_4f))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels( "时", "分","秒")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvNoLinkOptions.setNPicker(hours, minutes,null);
    }


}
