package com.example.a3edtextin1withhandwriting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.example.a3edtextin1withhandwriting.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    static String Text1 = "abcde";
    static String Text2 = "";
    static String Text3 = "";

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                char chars = (char) msg.obj;
                String str = binding.TV.getText()+ String.valueOf(chars);
                binding.TV.setText(str);
            }
        };
        binding.butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread(Text1,1);
                myThread.start();
            }
        });
    }

    class MyThread extends Thread {
        private char[] texttoview;
        private String text;
        int order = 0;

        public MyThread(String text, int order) {
            this.text = text;
            this.order=order;
            this.texttoview = new char[text.length()];
        }

        @Override
        public void run() {
            super.run();
            char[] textchars = text.toCharArray();
            for (int i = 0; i < textchars.length; i++) {
                char ch = textchars[i];
                texttoview[i] = ch;
                Message msg = new Message();
                msg.obj = texttoview[i];
                handler.sendMessage(msg);
                try {
                    if (ch == '.' || ch == '!' || ch == '?') {
                        Thread.sleep(1000);
                    } else {
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}