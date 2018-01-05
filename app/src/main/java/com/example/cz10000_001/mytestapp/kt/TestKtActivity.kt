package com.example.cz10000_001.mytestapp.kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.R.id.wrap
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.cz10000_001.mytestapp.R
import kotlinx.android.synthetic.main.activity_test_kt.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *    kotlin  实现界面 测试
 */
class TestKtActivity : AppCompatActivity() {



//    var timetv:TextView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_kt)

//        timetv  = findViewById(R.id.timetv) as TextView
//        val label = TextView(this)
//        label.text = "愿为佳人 倾国倾城"
////        container.addView(label)
//        addContentView(label, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT))
//
//


        val act = this   //上下文对象
        val layout = LinearLayout(act)
        this.setContentView(layout)
        val timeLabel = TextView(act)
        val button = Button(act)
        timeLabel.text = "hello，world"
        layout.orientation = LinearLayout.VERTICAL
        button.text = "点击获取当前时间"
        //  FIll_parent  -1  match_parent -1  wrap_content -2
        button.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        timeLabel.layoutParams = ViewGroup.LayoutParams(-2, -2)

        button.setOnClickListener {
            val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val str = dataFormat.format(Date())
            timeLabel.text = str
        }

        layout.addView(button)
        layout.addView(timeLabel)

        val ID_ok =1
        
    }


//    fun showNow(view:View){
//        val dataFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")   //设置 日期格式
//        val str = dataFormat.format(Date())
//        timetv.text = str
//    }
}
