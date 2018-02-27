package com.example.cz10000_001.mytestapp;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cz10000_001 on 2017/10/25.
 */

public class MyApp extends Application {

    private static MyApp instance ;
    private List<Intent> startedActivities = new ArrayList<>();
    private boolean checkActivities;

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

    public static MyApp getInstance(){
        return instance ;
    }



//    public static <T extends Activity> T setupActivity(Class<T> activityClass) {
//        return activityClass ;
//    }

    @Override
    public void startActivity(Intent intent) {
        verifyActivityInManifest(intent);
        startedActivities.add(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        verifyActivityInManifest(intent);
        startedActivities.add(intent);
    }

    public Intent getNextStartedActivity() {
        if (startedActivities.isEmpty()) {
            return null;
        } else {
            return startedActivities.remove(0);
        }
    }

    private void verifyActivityInManifest(Intent intent) {
        if (checkActivities && getPackageManager().resolveActivity(intent, -1) == null) {
            throw new ActivityNotFoundException(intent.getAction());
        }
    }

    public void checkActivities(boolean checkActivities) {
        this.checkActivities = checkActivities;
    }
}
