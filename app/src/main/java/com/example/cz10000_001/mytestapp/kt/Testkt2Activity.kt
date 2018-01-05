package com.example.cz10000_001.mytestapp.kt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cz10000_001.mytestapp.R
import kotlinx.android.synthetic.main.activity_testkt2.*


/**
 *   test kotlin  activity  生命周期
 *
 *
 */
class Testkt2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testkt2)
        Log.i("Testkt2Activity","onCreate")
        val intent = Intent(this,TestAnotherActivity::class.java)  //intent  变量
        intent.putExtra("name",etName.text.toString())
        intent.putExtra("pass",etPass.text.toString())
        btnGo.setOnClickListener({
            startActivityForResult(intent,1)
        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1&& resultCode==1&&data!=null){
            Toast.makeText(this,data.getStringExtra("result"),Toast.LENGTH_LONG).show()

        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }




}
