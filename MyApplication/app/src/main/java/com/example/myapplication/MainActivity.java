package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.http.WebStocket;
import com.example.myapplication.record.AudioRecorder;
import com.example.myapplication.util.FileUtils;

import static android.app.ProgressDialog.STYLE_SPINNER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startRecord = findViewById(R.id.startRecord);
        Button stopRecord = findViewById(R.id.stopRecord);
        final AudioRecorder audioRecorder = new AudioRecorder();
        final WebStocket webStocket = new WebStocket();
        startRecord.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                /*ProgressDialog waitDialog = new ProgressDialog(MainActivity.this);
                waitDialog.setIcon(R.mipmap.ic_launcher_round);
                waitDialog.setTitle("正在为您转接中...");
                waitDialog.setMessage("请稍等");
                waitDialog.setProgressStyle(STYLE_SPINNER);
                waitDialog.setCancelable(false);
                waitDialog.show();*/
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("正在为您转接中...");
                builder.setMessage("请稍等");
                builder.setCancelable(true);
                final AlertDialog dlg = builder.create();
                dlg.show();
                WebStocket.MyWebSocketClient connectStatus = webStocket.startConnect();
                if(connectStatus!= null){
                    //waitDialog.hide();
                    dlg.dismiss();
                    audioRecorder.createDefaultAudio("test.pcm");
                    audioRecorder.startRecord();
                    AudioRecorder.Status sat = audioRecorder.getStatus();
                    FileUtils fileUtils ;
                    while (sat == AudioRecorder.Status.STATUS_START ){
                        try {
                            fileUtils= new FileUtils();
                            System.out.println("录制10秒");
                            Thread.sleep(10000);
                            audioRecorder.stopRecord();
                            System.out.println("录制完成，开始转写");
                            webStocket.uploadRecorder(fileUtils.getPcmFileAbsolutePath()+"/test.pcm",connectStatus);
                            Thread.sleep(10000);
                            System.out.println("转写结束开始录音");
                            audioRecorder.createDefaultAudio("test.pcm");
                            audioRecorder.startRecord();
                        }catch (Exception e){

                        }
                    }
                }

            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               audioRecorder.stopRecord();
               webStocket.stopConnent();
            }
        });
    }

}
