package com.bruce.testgitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ajie.bootstartdemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
    //这是一个测试版的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试一下,再来
        getHttpResp("http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4");
    }

    /**
     * 测试方法
     */
    public void tes() {
        String name = "杨杰_方俊";
        //这是一个测试方法
        //这又是测试
    }


    public void testTwo() {
        //这是第二个测试方法
    }

    public void getHttpResp(final String url){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
////创建一个Request
//        final Request request = new Request.Builder()
//                .url("https://github.com/hongyangAndroid")
//                .build();
//        RequestBody requestBody = new RequestBody() {
//            @Override
//            public MediaType contentType() {
//                return null;
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//
//            }
//        };
//
//        RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"name");
//
//        Request request = new Request.Builder().build();
////                 .url("https://api.github.com/markdown/raw")
////             .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
////                     .build();
//            request.newBuilder().url("https://api.github.com/markdown/raw").addHeader("","").addHeader("","");
////new call

//        Request request = new Request.Builder()
//              .url("https://api.github.com/repos/square/okhttp/issues")
//              .header("User-Agent", "OkHttp Headers.java")
//               .addHeader("Accept", "application/json; q=0.5")
//               .addHeader("Accept", "application/vnd.github.v3+json")
//               .build();

//        RequestBody requestBody = new MultipartBuilder()
//                .type(MultipartBuilder.FORM)
//                .addPart(Headers.of(
//                        "Content-Disposition",
//                        "form-data; name=\"username\""),
//                        RequestBody.create(null, "张鸿洋"));

//        final String url = "http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4";
        Request request = new Request.Builder().url(url).build();

        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mp4Url = url.replace("http://","").replace(".","").replace("/","");
                Log.e("tag","url---------"+mp4Url+"-----Environment.getExternalStorageDirectory()+mp4Url----"+getFilesDir()+"/"+mp4Url);
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(getFilesDir()+"/"+mp4Url));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    Log.i("wangshu", "IOException");
                    e.printStackTrace();
                }

                Log.d("wangshu", "文件下载成功");
            }



        });

    }




}
