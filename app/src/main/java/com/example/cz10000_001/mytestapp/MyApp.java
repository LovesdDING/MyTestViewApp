package com.example.cz10000_001.mytestapp;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cz10000_001 on 2017/10/25.
 */

public class MyApp extends Application {

    private MyApp instance ;

    private static List<Class<Activity>> lists = new LinkedList<>() ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = new MyApp() ;

        initActivitys() ;
    }

    private void initActivitys() {

//        lists.add(((Activity)TestActivity1).class) ;
//        lists.add(TestActivity2.class) ;

//        lists.add(TestActivity1.);
//        lists.add(TestActivity2);
//        lists.add(TestActivity3) ;

    }

    public  MyApp getInstance(){
        return instance ;
    }


}
