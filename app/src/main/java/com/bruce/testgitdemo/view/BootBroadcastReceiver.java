package com.bruce.testgitdemo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String action_boot="android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("tag","测试开机广播");
//        if (intent.getAction().equals(action_boot)){
//            Intent ootStartIntent=new Intent(context,BootStartDemo.class);
//            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(ootStartIntent);
//        }

        Intent service = new Intent(context,ServiceCrack.class);
        context.startService(service);
        Log.d("TAG1","开机自启动服务");

    }

}