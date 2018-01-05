package com.example.cz10000_001.mytestapp.kt

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.cz10000_001.mytestapp.R
import com.example.cz10000_001.mytestapp.adapter.MyAdapter
import kotlinx.android.synthetic.main.pickerview_custom_time.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onSeekBarChangeListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 *  anko 库的引入   布局 测试
 *  @author lv
 *  creatd 2017/12/27
 */
class TestKt1Activity : AppCompatActivity() {

    companion object {
        val text1 = "感谢"
        val text2 = "使用"
        val text3 = "本公司"
        val text4 = "神秘"
        val text5 = "产品"
        val  text6 = "您将会"
        val  text7 = "拥有"
        val text8 = "不一样"
        val text9 = "的体验"
        val  text10="祝您"
        val text11 = "使用"
        val text12 = "愉快"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_kt1)
//        /**
//         *    定义了  一个 orientation为vertical的 linearlayout
//         */
//        verticalLayout {
//            val name = editText()
//            button("say hello"){  //  定义名字为  say hello 的button按钮   和  button的点击事件
//                onClick { toast("Hello ,${name.text}!") }
//            }
//        }

        //1.
//        MainActivityUI().setContentView(this)


        //2.
//        UI{
            //线性布局管理器
//            val container = verticalLayout {
//                val timeTextView = textView("Hello,World").lparams(wrapContent, wrapContent)
//                val btn = button{
//                    text="点击获取当前时间"
//                    onClick {
//                        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                        val dateStr = dateFormat.format(Date())  //Date  获取当前系统时间
//                        timeTextView.text= dateStr
//                    }
//
//                }.lparams(wrapContent, wrapContent)
//                val addElementBtn = button {
//                    text="点击添加一个view"
//                    onClick {
//                        val label = TextView(this@TestKt1Activity)
//                        label.text = "哈哈哈哈哈 这是一个新的textView"
//                        //动态的将新创建的TextView 装入linearlayout
//                        runOnUiThread { this@verticalLayout.addView(label) }
//                    }
//                }.lparams(wrapContent, wrapContent)
//
//            }
////        }
//          setContentView(container)  //给Activity 设置布局文件


        //3.
//          verticalLayout {
//              textView("switchBtn")
//              linearLayout {
//                  val switchOn = switch {
//                      isChecked = true
//                      backgroundColor = R.color.main_color
//                  }.lparams(wrapContent, wrapContent)
//                  val switchOff  =switch{
//                      isChecked = false
//                  }.lparams(wrapContent, wrapContent)
//              }
//
//              textView("toggle")
//              linearLayout {
//                  val toggleON = toggleButton {
//                      isChecked = true
//                  }.lparams(wrapContent, wrapContent)
//                  val toggleOff = toggleButton {
//                      isChecked=false
//                  }.lparams(wrapContent, wrapContent)
//              }
//              linearLayout {
//                  imageView(R.drawable.ic_launcher){
//                    scaleType = ImageView.ScaleType.CENTER
//                  }.lparams(wrapContent, wrapContent){
//                      gravity = Gravity.CENTER_HORIZONTAL
//                  }
//
//              }
//
//
//          }


        //4.  spinner
//        val mItems = resources.getStringArray(R.array.language)
//        val spinner = spinner(){
//            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
//
//            adapter = ArrayAdapter<String>(this@TestKt1Activity,android.R.layout.simple_spinner_item,mItems)
//            onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    toast("你点击了:"+mItems[position])
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            }
//        }


        //  5. progressbar
//        verticalLayout {
//            progressBar {
//                max = 100
//                progress = 20
//                secondaryProgress = 40
//                incrementProgressBy(10)
//                incrementSecondaryProgressBy(20)
//            }
//
//            horizontalProgressBar {
//                max =100
//               progress = 40
//                secondaryProgress = 70  // 次要进度条
//                incrementProgressBy(1)
//                incrementSecondaryProgressBy(25)
//            }
//        }

        // 6. seekBar
//        verticalLayout {
//            seekBar {
//                onSeekBarChangeListener {
//                    onProgressChanged{
//                        seekBar,progress,fromUser -> toast(seekBar?.progress.toString())
//                    }
//
//                    onStartTrackingTouch { _ -> }
//                    onStopTrackingTouch { _ -> }
//                }
//            }
//        }

        // 7.alertDialog
//        linearLayout {
//            button("触发dialog"){
//                onClick {
//                    alert{
//                        icon = resources.getDrawable(R.mipmap.ic_launcher)
//                        title = "AlertDialog"
//                        message = "这是一条kt的dialog"
//                        // 设置 确定 取消 和中立按钮
//                        positiveButton("好的"){
//                            toast("好的")
//                        }
//                        neutralPressed("再想想"){
//                            toast("再详细那个")
//                        }
//
//                        negativeButton("取消"){
//                            toast("取消")
//                        }
//
//
//                    }.show()  //必须要有 show方法
//
//                }
//            }
//        }

        // 8 .scrollView
//        verticalLayout {
//            backgroundColor = Color.parseColor("#ffffff")
//            relativeLayout {
//                textView("用户协议与隐私条款"){
//                    maxWidth = dip(300)
//                    singleLine = true
//                    textColor = Color.parseColor("#333333")
//                    textSize = 20f
//                }.lparams(wrapContent, wrapContent){
//                    centerInParent()
//                }
//
//            }.lparams(matchParent,height = dip(45))
//        }
//        scrollView {
//             matchParent
//             matchParent
//            verticalLayout {
//                textView(text1)
//                textView(text2).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text3).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text4).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text5).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text6).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text7).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text8).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text9).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text10).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text11).lparams{
//                    topMargin = dip(50)
//                }
//                textView(text12).lparams{
//                    topMargin = dip(50)
//                }
//            }.lparams(matchParent, matchParent){
//                topPadding = dip(10)
//                horizontalMargin = dip(10)
//                bottomPadding = dip(10)
//            }
//        }

        // 9 /listView
//        val  list:MutableList<String> = ArrayList<String>()
//        list.add("1")
//        list.add("2")
//        list.add("3")
//        list.add("4")
//        val listView:ListView = ListView(this)
//        setContentView(listView)
//        listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list)
        // 10. listView 2
//        val list:MutableList<String> = ArrayList<String>()
//        list.add("1")
//        list.add("2")
//        list.add("3")
//        list.add("4")
//        verticalLayout{
//            padding = dip(16)
//            val listView = listView(){
//                adapter = ArrayAdapter<String>(this@TestKt1Activity,android.R.layout.simple_list_item_1,list)
//                onItemClickListener = object :AdapterView.OnItemClickListener{
//                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////                        startActivity<TestKtActivity>()
//                        when(position){
//                            0 -> {startActivity<TestKtActivity>()}
//                            1 ->{toast(""+position)}
//                            2 ->{
//                                toast("哈哈哈  222")
//                            }
//                            3 ->{
//                                toast("点击无反应")
//                            }
//                        }
//                    }
//                }
//            }.lparams(matchParent,height = matchParent){}
//        }

        // 11.  RecyclerView

        verticalLayout {
//            toolbar {
//                backgroundColor = Color.DKGRAY
//                title = titleStr
//
//            }
            recyclerView {
                    val myLayoutManager = LinearLayoutManager(context)
                myLayoutManager.orientation = OrientationHelper.VERTICAL
                layoutManager = myLayoutManager
//                adapter = MyAdapter()
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(DividerItemDecoration(this@TestKt1Activity,LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()

            }.lparams(matchParent, matchParent)
        }
    }
}

/**
 *    界面布局 单独摘出来
 */
//class MainActivityUI:AnkoComponent<TestKt1Activity>{
//    override fun createView(ui: AnkoContext<TestKt1Activity>): View = with(ui) {
////        verticalLayout {
////            themedButton("OK",R.style.mybtn){
////                backgroundColor = Color.RED
////                onClick { toast("SB  kotlin") }
////            }
////            val title = textView("Hello，World")
//////            val name = editText()
////            button("say hello"){  //  定义名字为  say hello 的button按钮   和  button的点击事件
////                onClick { toast("Hello ,welCOme!") }
////            }.lparams(wrapContent, wrapContent)
////        }
//
//        val  id_ok  =1
//        relativeLayout {
//            button("ok"){
//                id= id_ok
//            }.lparams{alignParentTop()}
//            button("cancel").lparams{below(id_ok)
//            topMargin =dip(20)
//            }
//        }
//    }
//
//
//
//
//}



