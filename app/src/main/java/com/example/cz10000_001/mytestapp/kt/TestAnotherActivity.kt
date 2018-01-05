package com.example.cz10000_001.mytestapp.kt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cz10000_001.mytestapp.R
import kotlinx.android.synthetic.main.activity_test_another.*
import org.jetbrains.anko.toast


/**
 *
 */
class TestAnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_another)
        val  name = intent.getStringExtra("name")
        val  pass = intent.getStringExtra("pass")
        val resultIntent = Intent()
        if(name.equals("123")&& pass.equals("456")){
            tvresult.setText("登陆成功")
            resultIntent.putExtra("result","登陆成功")
        }else{
            tvresult.setText("用户名或密码错误")
            resultIntent.putExtra("result","用户名或密码错误")
        }

        btnback.setOnClickListener({
            setResult(1,resultIntent)
            finish()
        })
    }



}
