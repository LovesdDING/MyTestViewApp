package com.example.cz10000_001.mytestapp.kt

import android.util.Log


/**
 * 创建 Personclass类
 * Created by cz10000_001 on 2017/11/7.
 */

class Person(age:Int){
    var  age:Int = age
    var isAdult:Boolean = false
        private set
    var addAge:Int  =0
    set(value) {
        age += value
        if (age >= 18){
            isAdult = true
        }
    }

}


fun main(args: Array<String>) {
    var person = Person(26)
    Log.e("kttest",""+person.isAdult)
    person.age = 20
    Log.e("kttest",""+person.isAdult)
    person.addAge=10
    Log.e("ketest",""+person.isAdult)

}