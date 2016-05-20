package com.bruce.testgitdemo.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class ServiceCrack extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("TAG2","test service");
    }

//    public IBinder onBind(Intent arg0){  //这是Service必须要实现的方法，目前这里面什么都没有做
////只是在onCreate()方法中打印了一个log便于测试
//        return null;
//    }
//
//    public void onCreate(){
//        super.onCreate();
//        Log.d("TAG2","test service");
//    }
}
