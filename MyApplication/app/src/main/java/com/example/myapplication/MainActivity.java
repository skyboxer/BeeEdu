package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.http.WebStocket;
import com.example.myapplication.record.AudioRecorder;
import com.example.myapplication.util.FileUtils;

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
                audioRecorder.createDefaultAudio("test.pcm");
                audioRecorder.startRecord();
                FileUtils fileUtils = new FileUtils();
                AudioRecorder.Status sat = audioRecorder.getStatus();
                while (sat == AudioRecorder.Status.STATUS_START ){

                    try {
                        System.out.println("录制10秒");
                        Thread.sleep(10000);
                        audioRecorder.stopRecord();
                        System.out.println("开始转写");
                        webStocket.WebStocketConnect(fileUtils.getPcmFileAbsolutePath()+"/test.pcm");
                        Thread.sleep(20000);
                        System.out.println("转写结束开始录音");
                        audioRecorder.createDefaultAudio("test.pcm");
                        audioRecorder.startRecord();
                    }catch (Exception e){

                    }
                }
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.stopRecord();

            }
        });
    }

}
