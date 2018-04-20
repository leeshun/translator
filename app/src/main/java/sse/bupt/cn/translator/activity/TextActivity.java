package sse.bupt.cn.translator.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.model.Text;
import sse.bupt.cn.translator.util.TextFileWriter;

public class TextActivity extends AppCompatActivity {
    private static final String TAG = "TextActivity";

    private List<Text> texts;

    private String path;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initialize();
    }


    public void initialize() {
        initializeHandler();
    }

    private void initializeHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int type = msg.what;
                switch (type) {

                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextFileWriter writer = new TextFileWriter(path,texts,handler);
        writer.start();
    }


}
