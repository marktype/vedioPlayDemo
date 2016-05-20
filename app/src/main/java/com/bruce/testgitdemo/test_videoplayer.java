package com.bruce.testgitdemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import com.ajie.bootstartdemo.R;
import com.bruce.testgitdemo.fileutil.MyDataBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class test_videoplayer extends Activity {
    private SurfaceView surfaceView;
    private Button btnPause, btnPlayUrl, btnStop;
    private SeekBar skbProgress;
    private Player player;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView1);

        btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());

        btnPause = (Button) this.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new ClickEvent());

        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new ClickEvent());

        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(surfaceView, skbProgress);

    }

    class ClickEvent implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPause) {
                player.pause();
            } else if (arg0 == btnPlayUrl) {
//                String url="http://daily3gp.com/vids/family_guy_penis_car.3gp";
//                String url="http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4";
                String url="http://main.gslb.ku6.com/s1/4Sh--twBV2Og66na/1192554456000/e138aa506c8ee8d8784b81a5fa34871b/1463705398974/v141/252/121/OH9jSmSESdu7SYcnHjz63A.mp4?rate=180";
//                Log.e("tag","player------"+url);
                String mp4Url = url.replace("http://","").replace(".","").replace("/","");
                ArrayList<String> list =  MyDataBase.getInstance(getApplication()).query();
                if (list.contains(mp4Url)){
                    File file = new File(getFilesDir()+"/"+mp4Url);
//                    Uri uri = Uri.fromFile(new File(getFilesDir()+"/"+mp4Url));
                    Log.e("tag","file--------"+file.getAbsolutePath());
                    player.playUrl(file.getAbsolutePath());
                }else {

                    getHttpResp(url);
                    MyDataBase.getInstance(getApplication()).add(mp4Url,"");
                    player.playUrl(url);
                    Log.e("tag","url--else----"+url);
                }

            } else if (arg0 == btnStop) {
                player.stop();
            }

        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }

    /**
     * 下载保存到文件夹
     * @param url
     */
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