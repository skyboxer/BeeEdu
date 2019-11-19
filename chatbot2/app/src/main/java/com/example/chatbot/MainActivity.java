package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.WebSocket.READYSTATE;
import com.example.chatbot.RTASRTest.MyWebSocketClient;

public class MainActivity extends AppCompatActivity {
    private Config config = new Config();
    private RTASRTest rtaxTest = new RTASRTest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "匿名内部方式", Toast.LENGTH_SHORT).show();
                    URI url = null;
                    try {
                        url = new URI(config.getBaseUrl() + rtaxTest.getHandShakeParams(config.getAPPID(), config.getAPIKey()));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    DraftWithOrigin draft = new DraftWithOrigin(config.getOrigin());
                    CountDownLatch handshakeSuccess = new CountDownLatch(1);
                    CountDownLatch connectClose = new CountDownLatch(1);
                    MyWebSocketClient client = new MyWebSocketClient(url, draft, handshakeSuccess, connectClose);

                    client.connect();

                    while (!client.getReadyState().equals(READYSTATE.OPEN)) {
                        System.out.println(rtaxTest.getCurrentTimeStr() + "\t连接中");
                        Toast.makeText(MainActivity.this, rtaxTest.getCurrentTimeStr() + "\t连接中", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 等待握手成功
                    try {
                        handshakeSuccess.await();
                        Toast.makeText(MainActivity.this, "握手成功", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*System.out.println(rtaxTest.sdf.format(new Date()) + " 开始发送音频数据");
                    Toast.makeText(MainActivity.this,rtaxTest.sdf.format(new Date()) + " 开始发送音频数据",Toast.LENGTH_SHORT).show();*/
                    // 发送音频
                   /*try {
                       Thread.sleep(5000);
                        // 发送结束标识
                        rtaxTest.send(client,"{\"end\": true}".getBytes());
                        System.out.println(rtaxTest.getCurrentTimeStr() + "\t发送结束标识完成");
                       Toast.makeText(MainActivity.this,rtaxTest.getCurrentTimeStr() + "\t发送结束标识完成",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    // 等待连接关闭
                    try {
                        Thread.sleep(5000);
                        connectClose.await();
                        Toast.makeText(MainActivity.this, "关闭连接", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
    }
}
