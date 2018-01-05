package com.example.cz10000_001.mytestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.example.cz10000_001.mytestapp.R
import com.example.cz10000_001.mytestapp.bean.Course
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * 自定义  spinner 的adapter
 * Created by cz10000_001 on 2017/12/28.
 */
class MyAdapter(private val mContext:Context,private val mList:List<Course>):BaseAdapter(),SpinnerAdapter{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val layoutInflater = LayoutInflater.from(mContext)
        val convertView = layoutInflater.inflate(R.layout.spinner_item,null)
        if(convertView!=null){
            val textView1 = convertView.findViewById(R.id.tv1) as TextView
            val textView2 = convertView.findViewById(R.id.tv2) as TextView
            textView1.setText(mList[position].title)
            textView2.setText(mList[position].content)
        }
        return convertView

    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position.toLong()
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mList.size
    }



}