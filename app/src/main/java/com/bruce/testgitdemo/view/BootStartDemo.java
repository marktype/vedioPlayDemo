package com.bruce.testgitdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ajie.bootstartdemo.R;


public class BootStartDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        new Thread() {
            public void run() {
                try {
					/*  10秒后关闭页面*/
                    Log.d("tag","测试开机");
                    sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    finish(); // 关闭页面
                }
            }
        }.start();

    }
}
