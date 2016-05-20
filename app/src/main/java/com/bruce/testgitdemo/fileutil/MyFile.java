package com.bruce.testgitdemo.fileutil;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by 欢大哥 on 2016/5/18.
 */
public class MyFile {

    public void writeFile(String adName, InputStream inputStream){
        if (isExternalStorageWritable()){
            File file = new File(getADPath(),"/DICM"+adName);
            if (!file.exists()){
                file.mkdirs();
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader is = new InputStreamReader(inputStream,"utf-8");
                OutputStreamWriter os = new OutputStreamWriter(fos,"utf-8");
                

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }else {
            Log.d("file","没有外部存储卡");
        }


    }

    public String getADPath() {
        return Environment.getExternalStorageDirectory() + "/DCIM/";
    }

    /**
     * 检查是否有外部存储设备
     * @return
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }


    }
}
