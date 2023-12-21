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
    static String Text0 = "abcde";
    static String Text1 = "";
    static String Text2 = "";
    static int order = 0;
    static Object sync= new Object();
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
                Text0=binding.ed1.getText().toString();
                Text1=binding.ed2.getText().toString();
                Text2=binding.ed3.getText().toString();
                MyThread0 myThread0 = new MyThread0(Text0);
                MyThread1 myThread1 = new MyThread1(Text1);
                MyThread2 myThread2 = new MyThread2(Text2);
                myThread0.start();
                myThread1.start();
                myThread2.start();
            }
        });
    }

    class MyThread0 extends Thread {
        private char[] texttoview;
        private String text;
        //int order = 0;

        public MyThread0(String text) {
            this.text = text;
            //this.order=order;
            this.texttoview = new char[text.length()];
        }

        @Override
        public void run() {
            super.run();
            char[] textchars = text.toCharArray();
            for (int i = 0; i < textchars.length; i++) {
                synchronized (sync)
                {
                    while (order!=0){
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
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
                    if (ch == ' ') {
                        order = (order+1)%3;
                        sync.notify();
                    }
                }
            }
            Message msg = new Message();
            msg.obj = ' ';
            handler.sendMessage(msg);
            while (true) {
                synchronized (sync) {
                    order = (order + 1) % 3;
                    sync.notify();
                }
            }
        }
    }
    class MyThread1 extends Thread {
        private char[] texttoview;
        private String text;
        //int order = 0;

        public MyThread1(String text) {
            this.text = text;
            //this.order=order;
            this.texttoview = new char[text.length()];
        }

        @Override
        public void run() {
            super.run();
            char[] textchars = text.toCharArray();
            for (int i = 0; i < textchars.length; i++) {
                synchronized (sync)
                {
                    while (order!=1){
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
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
                    if (ch == ' ') {
                        order = (order+1)%3;
                        sync.notify();
                    }
                }
            }
            Message msg = new Message();
            msg.obj = ' ';
            handler.sendMessage(msg);
            while (true) {
                synchronized (sync) {
                    order = (order + 1) % 3;
                    sync.notify();
                }
            }
        }
    }
    class MyThread2 extends Thread {
        private char[] texttoview;
        private String text;
        //int order = 0;

        public MyThread2(String text) {
            this.text = text;
            //this.order=order;
            this.texttoview = new char[text.length()];
        }

        @Override
        public void run() {
            super.run();
            char[] textchars = text.toCharArray();
            for (int i = 0; i < textchars.length; i++) {
                synchronized (sync)
                {
                    while (order!=2){
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
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
                    if (ch == ' ') {
                        order = (order+1)%3;
                        sync.notify();
                    }
                }
            }
            Message msg = new Message();
            msg.obj = ' ';
            handler.sendMessage(msg);
            while (true) {
                synchronized (sync) {
                    order = (order + 1) % 3;
                    sync.notify();
                }
            }

        }
    }
}